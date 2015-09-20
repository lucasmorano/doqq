var stompClient = null;
var loggedUser = null;
var capsLock;

function setConnected(connected, user) {
    loggedUser = user;
    document.getElementById('textDocument').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('textDocument').addEventListener('keydown', function (e) {
        sendKey(e);
    }, false);
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
    text.value += action.result;
    //TODO Tratar o local do cursor;
}

function connect(user) {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true, user);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            //TODO Mostrar que um usuario entrou e dar boas vindas
        });
        stompClient.subscribe('/topic/actions', function (action) {
            mergeAction(JSON.parse(action.body));
        });
        closeDialog();
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
    stompClient.send("/app/hello", {}, JSON.stringify({'userName': user}));
}

function sendKey(e) {
    var keyCode = e.keyCode;
    if ((keyCode <= 90 && keyCode >= 65 ) || (keyCode == 13 || keyCode == 32 || keyCode == 8)) {
        stompClient.send("/app/action", {}, JSON.stringify({
            'keyCode': keyCode,
            'shiftKey': e.shiftKey,
            'capsLockStatus': capsLock.status,
            'userName': loggedUser
        }));
    }
}