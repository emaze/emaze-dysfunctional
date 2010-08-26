package net.emaze.disfunctional.delegates;

/**
 *
 * @author rferranti
 */
public interface Predicate<E> {
    public boolean call(E element);
}
