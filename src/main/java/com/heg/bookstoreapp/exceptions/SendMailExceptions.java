package com.heg.bookstoreapp.exceptions;

public class SendMailExceptions extends RuntimeException{
    public SendMailExceptions(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SendMailExceptions(String exMessage) {
        super(exMessage);
    }
}
