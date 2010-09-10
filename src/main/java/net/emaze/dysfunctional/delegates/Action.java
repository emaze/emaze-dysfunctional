package net.emaze.dysfunctional.delegates;

/**
 * A unary functor with no return value
 * @author rferranti
 */
public interface Action<T> {
    public void perform(T t);
}
