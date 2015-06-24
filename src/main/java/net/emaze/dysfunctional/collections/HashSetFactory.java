package net.emaze.dysfunctional.collections;

import java.util.HashSet;
import java.util.function.Supplier;

/**
 * Creates an empty HashSet.
 * @param <E> the HashSet element type parameter
 * @author rferranti
 */
public class HashSetFactory<E> implements Supplier<HashSet<E>> {

    @Override
    public HashSet<E> get() {
        return new HashSet<E>();
    }
}
