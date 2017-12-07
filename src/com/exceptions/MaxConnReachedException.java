package com.exceptions;

public class MaxConnReachedException extends Exception {
    public MaxConnReachedException() {
        super();
    }

    public MaxConnReachedException(String message) {
        super(message);
    }

    public MaxConnReachedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaxConnReachedException(Throwable cause) {
        super(cause);
    }
}
