package com.stokmate.backend.sm.api.model;

/**
 * Created by amdesai on 2/23/15.
 */
public class SMResponse {
    private int status;
    private String message;

    public SMResponse(){

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
