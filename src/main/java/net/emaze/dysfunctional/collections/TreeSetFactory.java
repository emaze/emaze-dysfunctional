package net.emaze.dysfunctional.collections;

import java.util.TreeSet;

/**
 * Creates an empty TreeSet.
 * @param <E> the TreeSet element type parameter
 * @author rferranti
 */
public class TreeSetFactory<E> implements CollectionFactory<TreeSet<E>, E> {

    @Override
    public TreeSet<E> create() {
        return new TreeSet<E>();
    }
}
