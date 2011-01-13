package net.emaze.dysfunctional.collections;

import java.util.HashSet;

/**
 * Creates an empty HashSet.
 * @param <E> the HashSet element type parameter
 * @author rferranti
 */
public class HashSetFactory<E> implements CollectionFactory<HashSet<E>, E> {

    @Override
    public HashSet<E> create() {
        return new HashSet<E>();
    }
}
