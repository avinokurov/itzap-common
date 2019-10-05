package com.itzap.common.utils;

import com.google.common.base.Joiner;
import com.itzap.common.Named;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class NamedUtils {
    private static final List<String> EMPTY_LIST = Collections.unmodifiableList(new ArrayList<>());

    private NamedUtils() {}

    public static List<String> getNames(List<Named> objects) {
        if (objects == null || objects.isEmpty()) {
            return EMPTY_LIST;
        }

        return objects.stream()
                .map(Named::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getNames(Named ... objects) {
        if (objects == null || objects.length == 0) {
            return EMPTY_LIST;
        }

        List<String> result = new ArrayList<>(objects.length);
        for (Named object : objects) {
            result.add(object.getName());
        }

        return result;
    }

    public static String getNameString(Named ... fields) {
        if (fields == null || fields.length == 0) {
            return Named.BLANK_NAME;
        }

        return Joiner.on(",").join(Stream.of(fields)
                .map(Named::getName)
                .collect(Collectors.toList()));
    }

    public static <T extends Named> List<T> filterNames(List<T> objects, Predicate<T> predicate) {
        if (objects == null || objects.isEmpty()) {
            return Collections.unmodifiableList(new ArrayList<>());
        }

        return objects.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static <T extends Named> List<T> filterNames(T[] objects, Predicate<T> predicate) {
        if (objects == null || objects.length == 0) {
            return Collections.unmodifiableList(new ArrayList<>());
        }

        return Arrays.stream(objects)
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
