package com.jal.controller;

import com.jal.domain.Action;
import com.jal.domain.ActionPerformed;
import com.jal.domain.Document;
import com.jal.domain.User;
import com.jal.service.ActionPerformedService;
import com.jal.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class    DocumentController {

    @Autowired
    private ActionPerformedService actionPerformedService;

    @Autowired
    private DocumentService documentService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Document greeting(User login) throws Exception {
        return new Document(login.getUserName(), documentService.getCurrentDocument());
    }

    @MessageMapping("/action")
    @SendTo("/topic/actions")
    public ActionPerformed action(Action action) throws Exception {
        return actionPerformedService.broadcast(action);
    }

}
