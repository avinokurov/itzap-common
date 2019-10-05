package com.itzap.common;

import org.apache.commons.lang3.StringUtils;

public interface Property extends Named {
    default String getDefault() {
        return StringUtils.EMPTY;
    }

    default String getDescription() {
        return getName();
    }
}
