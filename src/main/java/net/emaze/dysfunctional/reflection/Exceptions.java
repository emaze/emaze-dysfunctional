package net.emaze.dysfunctional.reflection;

/**
 * ripped from jmanner, please keep in sync
 * @author rferranti
 */
public abstract class Exceptions {

    public static Throwable unwrap(Throwable source, Class<?> removee){
        Throwable t = source;
        while (t != null && removee.isAssignableFrom(t.getClass())) {
            t = t.getCause();
        }
        return t;
    }

    public static <T> T findInThrowable(Throwable source, Class<T> needle) {
        Throwable current = source;
        while (current != null) {
            if (needle.isAssignableFrom(current.getClass())) {
                return (T) current;
            }
            current = current.getCause();
        }
        return null;
    }
}
