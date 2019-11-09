package com.itzap.common;

public interface Immutable {
    interface State {
    }

    interface Builder<T> {
        T build();
    }
}
