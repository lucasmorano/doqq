var stompClient = null;
var loggedUser = null;
var capsLock;

function setConnected(connected, user) {
    loggedUser = user;
    document.getElementById('textDocument').style.visibility = connected ? 'visible' : 'hidden';
}

function closeDialog() {
    $("#window").removeClass("show");
    $(".modal-backdrop").remove();
    $("#window").hide();
    document.getElementById('textDocument').focus();
}

function mergeAction(action) {
    if (action.userName == loggedUser) {
        return;
    }
    var text = document.getElementById('textDocument');
    var caretPosition = action.caretPosition;
    if(action.specialAction != null){
        text.value = text.value.substr(0, caretPosition - 1) + text.value.substr(caretPosition);
        return;
    }
    if(caretPosition > text.length) {
        text.value += action.result;
    } else {
        text.value = text.value.substr(0, caretPosition) + action.result + text.value.substr(caretPosition);
    }

    //TODO Tratar o local do cursor;
}

function greet(greeting) {
    var entity = JSON.parse(greeting.body);
    if (entity.userName == loggedUser) {
        var text = document.getElementById('textDocument');
        text.value = entity.currentDocument;
        return;
    }
    $.notify(entity.userName + " entrou!", "info");

}
function connect(user) {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    $('#textDocument').keydown(function (e) {
        sendKey(e);
    });
    stompClient.connect({}, function (frame) {
        setConnected(true, user);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            greet(greeting);
        });
        stompClient.subscribe('/topic/actions', function (action) {
            mergeAction(JSON.parse(action.body));
        });
        closeDialog();
        stompClient.send("/app/hello", {}, JSON.stringify({'userName': user}));
    });
}

function showConnectDialog() {
    $('#window').modal('show');
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    showConnectDialog();
}

function sendLogin() {
    var user = document.getElementById('user').setAttribute('disabled', 'true');
    var user = document.getElementById('user').value;
    connect(user);
}

function sendKey(e) {
    var keyCode = e.keyCode;
    if ((keyCode <= 90 && keyCode >= 65 ) || (keyCode == 13 || keyCode == 32 || keyCode == 8)) {
        var pos = $(textDocument).caret();
        stompClient.send("/app/action", {}, JSON.stringify({
            'keyCode': keyCode,
            'shiftKey': e.shiftKey,
            'capsLockStatus': capsLock.status,
            'userName': loggedUser,
            'caretPosition': pos
        }));
    }
}