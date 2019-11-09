package com.itzap.common;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public interface AnyConfig {
    default int getInt(Property key) {
        return Integer.parseInt(getString(key));
    }

    default boolean getBool(Property key) {
        return BooleanUtils.toBoolean(getString(key));
    }

    String getString(Property key);

    boolean hasProperty(Property key);

    void load();

    AnyConfig getConfig(String name);

    default AnyConfig getConfig(Property property) {
        return getConfig(property.getName());
    }

    AnyConfig withFallback(AnyConfig fallback);

    default List<String> getList(Property name) {
        String val = getString(name);
        if (StringUtils.isBlank(val)) {
            return ImmutableList.of();
        }

        return Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(getString(name));
    }
}
