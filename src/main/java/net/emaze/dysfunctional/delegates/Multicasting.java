package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public interface Multicasting<F> {
    public void add(F functor);
    public void remove(F functor);
}
