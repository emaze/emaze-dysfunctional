package net.emaze.dysfunctional.collections;

import java.util.TreeSet;

/**
 *
 * @author rferranti
 */
public class TreeSetFactory<E> implements CollectionFactory<TreeSet<E>, E> {

    @Override
    public TreeSet<E> create() {
        return new TreeSet<E>();
    }
}
