package com.itzap.common;

import org.apache.commons.lang3.StringUtils;

public interface Named {
    String BLANK_NAME = StringUtils.EMPTY;

    String getName();
}
