package com.multiwarehouse.app.domain.event;

public interface DomainEvent<T> {
    public void fire();
}
