package net.emaze.dysfunctional.collections;

import java.util.TreeSet;
import java.util.function.Supplier;

/**
 * Creates an empty TreeSet.
 * @param <E> the TreeSet element type parameter
 * @author rferranti
 */
public class TreeSetFactory<E> implements Supplier<TreeSet<E>> {

    @Override
    public TreeSet<E> get() {
        return new TreeSet<E>();
    }
}
