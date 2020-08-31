package com.webapp.app.exception;

public class NotFoundException extends BaseException {
    public NotFoundException() {
        statusCode = 601;
        detail = "Not found";
    }
}
