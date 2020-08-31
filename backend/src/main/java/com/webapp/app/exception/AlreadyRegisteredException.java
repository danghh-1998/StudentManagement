package com.webapp.app.exception;

public class AlreadyRegisteredException extends BaseException {
    public AlreadyRegisteredException(String studentName, String subjectName) {
        statusCode = 603;
        detail = String.format("Student %s has already registered for %s", studentName, subjectName);
    }
}
