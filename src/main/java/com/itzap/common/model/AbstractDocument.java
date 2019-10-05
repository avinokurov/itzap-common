package com.itzap.common.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDocument {
    private final Map<String, Object> attributes = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes.putAll(attributes);
    }

    @JsonAnySetter
    public void putAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }
}

