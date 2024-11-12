package dev.coldservices.util.registry;

public interface Provider<T> {
    T get();
}