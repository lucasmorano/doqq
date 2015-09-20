package com.jal.controller;

import com.jal.domain.Action;
import com.jal.domain.ActionPerformed;
import com.jal.domain.Document;
import com.jal.domain.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.awt.event.KeyEvent;

@Controller
public class DocumentController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Document greeting(User login) throws Exception {
        return new Document("Bem vindo, " + login.getUserName() + "!");
    }

    @MessageMapping("/action")
    @SendTo("/topic/actions")
    public Action action(Action action) throws Exception {
        return new ActionPerformed(action.getUserName(), KeyEvent.getKeyText(action.getKeyCode()));
    }

}
