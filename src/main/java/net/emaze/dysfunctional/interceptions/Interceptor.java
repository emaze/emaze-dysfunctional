package net.emaze.dysfunctional.interceptions;

/**
 *
 * @param <T>
 * @author rferranti
 */
public interface Interceptor<T> {
    void before(T value);
    void after(T value);
}
