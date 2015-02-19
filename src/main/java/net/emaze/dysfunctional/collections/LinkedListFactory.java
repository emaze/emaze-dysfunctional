package net.emaze.dysfunctional.collections;

import java.util.LinkedList;
import java.util.function.Supplier;

/**
 * Creates an empty LinkedList.
 *
 * @param <E> the LinkedList element type parameter
 * @author rferranti
 */
public class LinkedListFactory<E> implements Supplier<LinkedList<E>> {

    @Override
    public LinkedList<E> get() {
        return new LinkedList<E>();
    }
}
