package net.emaze.dysfunctional.interceptions;

/**
 *
 * @param <T1>
 * @param <T2>
 * @author rferranti
 */
public interface BinaryInterceptor<T1, T2> {
    public void before(T1 first, T2 second);
    public void after(T1 first, T2 second);
}
