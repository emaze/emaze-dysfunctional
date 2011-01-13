package net.emaze.dysfunctional.collections;

import java.util.Collection;

/**
 *
 * @param <C>
 * @param <E>
 * @author rferranti
 */
public interface CollectionFactory<C extends Collection<E>, E> {
    public C create();
}
