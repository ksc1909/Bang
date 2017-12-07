package com.exceptions;

public class ServerExistException extends Exception
{
    public ServerExistException() {
    }

    public ServerExistException(String message) {
        super(message);
    }

    public ServerExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerExistException(Throwable cause) {
        super(cause);
    }
}
