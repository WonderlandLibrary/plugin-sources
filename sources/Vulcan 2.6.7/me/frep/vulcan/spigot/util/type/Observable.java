package me.frep.vulcan.spigot.util.type;

import java.util.HashSet;
import java.util.Set;

public final class Observable<T>
{
    private final Set<ChangeObserver<T>> observers;
    private T value;
    
    public Observable(final T initValue) {
        this.observers = new HashSet<ChangeObserver<T>>();
        this.value = initValue;
    }
    
    public T get() {
        return this.value;
    }
    
    public void set(final T value) {
        final T oldValue = this.value;
        this.value = value;
        this.observers.forEach(it -> it.handle(oldValue, value));
    }
    
    public ChangeObserver<T> observe(final ChangeObserver<T> onChange) {
        this.observers.add(onChange);
        return onChange;
    }
    
    public void unobserve(final ChangeObserver<T> onChange) {
        this.observers.remove(onChange);
    }
    
    @FunctionalInterface
    public interface ChangeObserver<T>
    {
        void handle(final T p0, final T p1);
    }
}
