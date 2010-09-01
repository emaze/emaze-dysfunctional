package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public interface Action<T> {
    public void perform(T t);
}
