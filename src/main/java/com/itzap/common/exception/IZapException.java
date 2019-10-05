package com.itzap.common.exception;

import com.itzap.common.ErrorCode;
import com.itzap.common.ErrorDescriptor;
import org.apache.commons.lang3.StringUtils;

public class IZapException extends RuntimeException implements ErrorDescriptor {
    private com.itzap.common.ErrorDescriptor descriptor;

    public IZapException(String message) {
        this(message, IZapErrorCodes.APPLICATION_ERROR, null);
    }

    public IZapException(String message, Throwable cause) {
        this(message, IZapErrorCodes.APPLICATION_ERROR, cause);
    }

    public IZapException(ErrorCode errorCode) {
        this(StringUtils.EMPTY, errorCode, null);
    }

    public IZapException(ErrorCode errorCode, Throwable cause) {
        this(StringUtils.EMPTY, errorCode, cause);
    }

    public IZapException(String msg, ErrorCode errorCode) {
        super(msg);
        this.descriptor = descriptor(msg, errorCode);
    }

    public IZapException(String msg, ErrorCode errorCode, Throwable cause) {
        super(msg, cause);
        this.descriptor = descriptor(errorCode);
    }

    public IZapException(com.itzap.common.ErrorDescriptor descriptor) {
        super(descriptor.getErrorMessage());
        this.descriptor = descriptor;
    }

    public IZapException(com.itzap.common.ErrorDescriptor descriptor, Throwable cause) {
        super(descriptor.getErrorMessage(), cause);
        this.descriptor = descriptor;
    }

    public ErrorCode getErrorCode() {
        return descriptor.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return this.descriptor.getErrorMessage();
    }

    @Override
    public com.itzap.common.ErrorDescriptor checkDescriptor(String checkMessage) {
        return new IZapException(descriptor.checkDescriptor(checkMessage), this.getCause());
    }

    public static class ErrorDescriptor implements com.itzap.common.ErrorDescriptor {
        private final ErrorCode errorCode;
        private final String errorMessage;

        private ErrorDescriptor(String errorMessage, ErrorCode errorCode) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        @Override
        public ErrorCode getErrorCode() {
            return errorCode;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }

        @Override
        public com.itzap.common.ErrorDescriptor checkDescriptor(String checkMessage) {
            return new ErrorDescriptor(this.errorMessage + " - Error: " + checkMessage, this.errorCode);
        }
    }

    public static com.itzap.common.ErrorDescriptor descriptor(String errorMessage, ErrorCode errorCode) {
        return new ErrorDescriptor(errorMessage, errorCode);
    }

    public static com.itzap.common.ErrorDescriptor descriptor(String errorMessage) {
        return new ErrorDescriptor(errorMessage, IZapErrorCodes.APPLICATION_ERROR);
    }

    public static com.itzap.common.ErrorDescriptor descriptor(ErrorCode errorCode) {
        return new ErrorDescriptor(errorCode.getMessage(), errorCode);
    }

    public static com.itzap.common.ErrorDescriptor descriptor() {
        return new ErrorDescriptor(IZapErrorCodes.APPLICATION_ERROR.getMessage(),
                IZapErrorCodes.APPLICATION_ERROR);
    }

    public static com.itzap.common.ErrorDescriptor descriptor(ErrorCode errorCode, String fmt, Object ... params) {
        return new ErrorDescriptor(String.format(fmt, params), errorCode);
    }
}
