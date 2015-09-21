package com.jal.domain;

/**
 * Created by lucasmorano on 9/20/15.
 */
public class ActionPerformed  {

    private Integer caretPosition;
    private String userName;

    private String result;
    private SpecialAction specialAction;

    public ActionPerformed(Integer caretPosition, String userName, String result) {
        this.caretPosition = caretPosition;
        this.userName = userName;
        this.result = result;
    }

    public ActionPerformed(Integer caretPosition, String userName, String result, SpecialAction specialAction) {
        this.caretPosition = caretPosition;
        this.userName = userName;
        this.result = result;
        this.specialAction = specialAction;
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

    public SpecialAction getSpecialAction() {
        return specialAction;
    }

    public void setSpecialAction(SpecialAction specialAction) {
        this.specialAction = specialAction;
    }
}
