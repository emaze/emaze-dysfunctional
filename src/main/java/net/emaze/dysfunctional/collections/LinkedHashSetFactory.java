package net.emaze.dysfunctional.collections;

import java.util.LinkedHashSet;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Creates an empty LinkedHashSet.
 *
 * @param <E> the LinkedHashSet element type parameter
 * @author rferranti
 */
public class LinkedHashSetFactory<E> implements Provider<LinkedHashSet<E>> {

    @Override
    public LinkedHashSet<E> provide() {
        return new LinkedHashSet<E>();
    }
}
