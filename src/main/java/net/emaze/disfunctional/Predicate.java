package net.emaze.disfunctional;

/**
 *
 * @author rferranti
 */
public interface Predicate<E> {
    public boolean match(E element);
}
