package com.itzap.common;

public abstract class AbstractNamed implements Named {
    private final String name;

    protected AbstractNamed(AbstractBuilder builder) {
        this.name = builder.name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static abstract class AbstractBuilder<T, B> implements BuilderInterface<T> {
        private String name;

        protected abstract B getThis();

        public B setName(String name) {
            this.name = name;
            return getThis();
        }
    }
}
