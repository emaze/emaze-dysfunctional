package net.emaze.dysfunctional.interceptions;

/**
 *
 * @param <T>
 * @author rferranti
 */
public interface Interceptor<T> {
    public void before(T value);
    public void after(T value);
}
