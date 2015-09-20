package com.jal.domain;

/**
 * Created by lucasmorano on 9/20/15.
 */
public class Document {

    private String content;

    public Document() {
    }

    public Document(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
