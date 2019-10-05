package com.itzap.common.exception;

import com.itzap.common.ErrorCode;

public enum IZapErrorCodes implements ErrorCode {
    APPLICATION_ERROR("itzap.error", "application error"),
    NOT_FOUND("itzap.not_found", "Not Found");

    private final String errorCode;
    private final String message;

    IZapErrorCodes(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
