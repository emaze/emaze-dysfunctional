package net.emaze.dysfunctional.collections;

import java.util.Collection;

/**
 *
 * @author rferranti
 */
public interface CollectionFactory<C extends Collection<E>, E> {
    public C create();
}
