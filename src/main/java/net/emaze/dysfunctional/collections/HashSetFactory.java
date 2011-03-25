package net.emaze.dysfunctional.collections;

import java.util.HashSet;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Creates an empty HashSet.
 * @param <E> the HashSet element type parameter
 * @author rferranti
 */
public class HashSetFactory<E> implements Provider<HashSet<E>> {

    @Override
    public HashSet<E> provide() {
        return new HashSet<E>();
    }
}
