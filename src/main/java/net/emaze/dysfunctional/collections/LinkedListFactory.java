package net.emaze.dysfunctional.collections;

import java.util.LinkedList;
import net.emaze.dysfunctional.delegates.Provider;

/**
 * Creates an empty LinkedList.
 * @param <E> the LinkedList element type parameter
 * @author rferranti
 */
public class LinkedListFactory<E> implements Provider<LinkedList<E>> {

    @Override
    public LinkedList<E> provide() {
        return new LinkedList<E>();
    }
}
