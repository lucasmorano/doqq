package com.jal.domain;

/**
 * Created by lucasmorano on 9/20/15.
 */
public class Action {

    private int keyCode;

    private Boolean shiftKey;

    private Boolean capsLockStatus;

    private String userName;

    public Action() {
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public Boolean getShiftKey() {
        return shiftKey;
    }

    public void setShiftKey(Boolean shiftKey) {
        this.shiftKey = shiftKey;
    }

    public Boolean getCapsLockStatus() {
        return capsLockStatus;
    }

    public void setCapsLockStatus(Boolean capsLockStatus) {
        this.capsLockStatus = capsLockStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
