package com.jal.service;

import com.jal.domain.SpecialAction;
import org.springframework.stereotype.Component;

/**
 * Created by lucasmorano on 9/21/15.
 */
@Component
public class DocumentService {

    private String currentDocument;

    public synchronized String getCurrentDocument() {
        if (currentDocument == null) {
            currentDocument = new String();
        }
        return currentDocument;
    }


    public void appendToDocument(Integer caretPosition, String result) {
        String currentDocument = getCurrentDocument();
        if (caretPosition > currentDocument.length()) {
            this.currentDocument = currentDocument.concat(result);
        } else {
            this.currentDocument = new StringBuilder(currentDocument).insert(caretPosition, result).toString();
        }
    }

    public void appendToDocument(Integer caretPosition, SpecialAction backspace) {
        String currentDocument = getCurrentDocument();
        this.currentDocument = new String(currentDocument.substring(0, caretPosition -1) + currentDocument.substring(caretPosition));
    }
}
