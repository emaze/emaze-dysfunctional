package net.emaze.disfunctional.delegates;

/**
 *
 * @author rferranti
 */
public interface Action<T> {
    public void perform(T t);
}
