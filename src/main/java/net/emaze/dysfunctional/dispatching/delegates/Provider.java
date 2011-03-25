package net.emaze.dysfunctional.dispatching.delegates;

/**
 * Provides an instance of T.
 * @param <T>
 * @author rferranti
 */
public interface Provider<T> {
    public T provide();
}
