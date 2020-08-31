package com.webapp.app.exception;

public class NotregisteredException extends BaseException {
    public NotregisteredException(String studentName, String subjectName) {
        statusCode = 602;
        detail = String.format("Student %s has not yet registered for %s", studentName, subjectName);
    }
}
