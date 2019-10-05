package com.itzap.common;

public interface ErrorDescriptor {
    ErrorCode getErrorCode();

    String getErrorMessage();

    ErrorDescriptor checkDescriptor(String checkMessage);
}
