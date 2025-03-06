package me.frep.vulcan.spigot.util;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.Collection;

public final class StreamUtil
{
    public static <T> boolean anyMatch(final Collection<T> collection, final Predicate<T> condition) {
        if (condition == null) {
            return false;
        }
        for (final T object : collection) {
            if (condition.test(object)) {
                return true;
            }
        }
        return false;
    }
    
    public static <T> boolean allMatch(final Collection<T> collection, final Predicate<T> condition) {
        if (condition == null) {
            return false;
        }
        for (final T object : collection) {
            if (!condition.test(object)) {
                return false;
            }
        }
        return true;
    }
    
    private StreamUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
