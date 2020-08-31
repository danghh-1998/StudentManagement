package com.webapp.app.exception;

import java.util.function.Supplier;

public abstract class BaseException extends RuntimeException {
    public static int statusCode = 0;
    public static String detail = null;

    public BaseException() {
        super(detail);
    }
}
