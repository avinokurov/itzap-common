package com.itzap.common.utils;

import com.itzap.common.ErrorCode;
import com.itzap.common.exception.IZapErrorCodes;
import com.itzap.common.exception.IZapException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Optional;

public final class IZapExceptionUtils {
    private IZapExceptionUtils() {}

    public static ErrorCode getErrorCode(Throwable ex) {
        return propagate(ex).getErrorCode();
    }

    public static IZapException propagate(Throwable ex) {
        if (ex instanceof IZapException) {
            return (IZapException) ex;
        }

        Optional<IZapException> causes = ExceptionUtils.getThrowableList(ex)
                .stream()
                .filter(t -> t instanceof IZapException)
                .map(t -> (IZapException)t)
                .findFirst();

        return causes.orElseGet(() -> new IZapException(IZapErrorCodes.APPLICATION_ERROR, ex));
    }

    public static String getMessage(IZapException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        if (errorCode.getMessage().equalsIgnoreCase(ex.getLocalizedMessage())) {
            return String.format("Error Code: %s. Message: %s",
                    errorCode.getErrorCode(), errorCode.getMessage());
        } else {
            return String.format("Code: %s. Message: %s, Message: %s",
                    errorCode.getErrorCode(), errorCode.getMessage(), ex.getLocalizedMessage());
        }
    }
}
