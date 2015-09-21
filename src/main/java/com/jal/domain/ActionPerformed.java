package com.jal.domain;

/**
 * Created by lucasmorano on 9/20/15.
 */
public class ActionPerformed  {

    private Integer caretPosition;
    private String userName;

    private String result;

    public ActionPerformed(Integer caretPosition, String userName, String result) {
        this.caretPosition = caretPosition;
        this.userName = userName;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCaretPosition() {
        return caretPosition;
    }

    public void setCaretPosition(Integer caretPosition) {
        this.caretPosition = caretPosition;
    }
}
