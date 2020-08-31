package com.webapp.app.exception;

public class DuplicatedException extends BaseException {
    public DuplicatedException(String entry, String value) {
        statusCode = 600;
        detail = String.format("Duplicate entry %s for key %s", entry, value);
    }
}
