package com.stokmate.backend;

/**
 * Created by amdesai on 2/23/15.
 */
public class Response {
    private int status;
    private String message;

    public Response(){

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
