package com.jal.service;

import com.jal.domain.Action;
import com.jal.domain.ActionPerformed;
import com.jal.domain.SpecialAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;

/**
 * Created by lucasmorano on 9/20/15.
 */
@Component
public class ActionPerformedService {

    @Autowired
    private DocumentService documentService;


    public ActionPerformed broadcast(Action action) {
        if(action.getKeyCode() == 8){
            ActionPerformed actionPerformed = new ActionPerformed(action.getCaretPosition(), action.getUserName(), null, SpecialAction.BACKSPACE);
            documentService.appendToDocument(action.getCaretPosition(), SpecialAction.BACKSPACE);
            return actionPerformed;
        }
        if(action.getKeyCode() == 13){
            ActionPerformed actionPerformed = new ActionPerformed(action.getCaretPosition(), action.getUserName(), "\n");
            documentService.appendToDocument(action.getCaretPosition(), actionPerformed.getResult());
            return actionPerformed;
        }
        if(action.getKeyCode() == 32){
            ActionPerformed actionPerformed = new ActionPerformed(action.getCaretPosition(), action.getUserName(), " ");
            documentService.appendToDocument(action.getCaretPosition(), actionPerformed.getResult());
            return actionPerformed;
        }
        String keyText = KeyEvent.getKeyText(action.getKeyCode());
        if(action.getShiftKey() || action.getCapsLockStatus()){
            ActionPerformed actionPerformed = new ActionPerformed(action.getCaretPosition(), action.getUserName(), keyText);
            documentService.appendToDocument(action.getCaretPosition(), actionPerformed.getResult());
            return actionPerformed;
        }
        ActionPerformed actionPerformed = new ActionPerformed(action.getCaretPosition(), action.getUserName(), keyText.toLowerCase());
        documentService.appendToDocument(action.getCaretPosition(), actionPerformed.getResult());
        return actionPerformed;
    }
}
