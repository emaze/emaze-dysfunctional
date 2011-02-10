package net.emaze.dysfunctional.collections;

import java.util.TreeSet;
import net.emaze.dysfunctional.delegates.Provider;

/**
 * Creates an empty TreeSet.
 * @param <E> the TreeSet element type parameter
 * @author rferranti
 */
public class TreeSetFactory<E> implements Provider<TreeSet<E>> {

    @Override
    public TreeSet<E> provide() {
        return new TreeSet<E>();
    }
}
