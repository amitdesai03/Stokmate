package com.stokmate.backend.db;

/**
 * Created by amdesai on 3/4/15.
 */
public class SMException extends Exception{
    String message;
    Exception e;

    public SMException(String message,Exception e){
        this.message = message;
        this.e = e;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
