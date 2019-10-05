package com.itzap.common;

public interface ErrorCode {
    default String getErrorCode() {
        return "application.error";
    }

    default String getMessage() {
        return "application error";
    }
}
