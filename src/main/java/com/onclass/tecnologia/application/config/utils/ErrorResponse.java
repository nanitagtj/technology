package com.onclass.tecnologia.application.config.utils;

import static com.onclass.tecnologia.application.config.Constants.ERROR;

public class ErrorResponse {
    public static String createErrorMessage(Throwable ex) {
        return ERROR + ex.getMessage();
    }
}

