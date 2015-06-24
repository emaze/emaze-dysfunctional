package net.emaze.dysfunctional.collections;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

/**
 * Creates an empty LinkedHashSet.
 *
 * @param <E> the LinkedHashSet element type parameter
 * @author rferranti
 */
public class LinkedHashSetFactory<E> implements Supplier<LinkedHashSet<E>> {

    @Override
    public LinkedHashSet<E> get() {
        return new LinkedHashSet<E>();
    }
}
