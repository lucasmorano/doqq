package com.jal.domain;

/**
 * Created by lucasmorano on 9/20/15.
 */
public class Document {

    private String userName;
    private String currentDocument;

    public Document() {
    }


    public Document(String userName, String currentDocument) {
        this.userName = userName;
        this.currentDocument = currentDocument;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(String currentDocument) {
        this.currentDocument = currentDocument;
    }
}
