package com.itzap.common.utils;

import com.itzap.common.ErrorDescriptor;
import com.itzap.common.exception.IZapException;

import java.io.File;
import java.util.Collection;

public final class PreconditionUtils {
    private PreconditionUtils() {}

    public static <T> T checkNotNull(T object, ErrorDescriptor descriptor) {
        if (object == null) {
            throw new IZapException(descriptor);
        }

        return object;
    }

    public static <T extends Collection> T checkCollection(T collection,
                                                         ErrorDescriptor descriptor) {
        checkNotNull(collection, descriptor
                .checkDescriptor("Collection Cannot be null"));

        if (collection.isEmpty()) {
            throw new IZapException(descriptor);
        }

        return collection;
    }

    public static File checkFileExists(File file, ErrorDescriptor descriptor) {
        checkNotNull(file, descriptor.checkDescriptor("Cannot be null"));

        if (!file.exists()) {
            throw new IZapException(descriptor);
        }

        return file;
    }
}
