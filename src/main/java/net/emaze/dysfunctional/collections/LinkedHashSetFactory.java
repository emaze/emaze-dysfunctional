package net.emaze.dysfunctional.collections;

import java.util.LinkedHashSet;

/**
 * Creates an empty LinkedHashSet.
 * @param <E> the LinkedHashSet element type parameter
 * @author rferranti
 */
public class LinkedHashSetFactory<E> implements CollectionFactory<LinkedHashSet<E>, E> {

    @Override
    public LinkedHashSet<E> create() {
        return new LinkedHashSet<E>();
    }
}
