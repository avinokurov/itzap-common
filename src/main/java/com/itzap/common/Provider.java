package com.itzap.common;

import io.reactivex.Observable;

public interface Provider<T> {
    Observable<T> get();
}
