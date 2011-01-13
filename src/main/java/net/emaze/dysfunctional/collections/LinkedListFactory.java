package net.emaze.dysfunctional.collections;

import java.util.LinkedList;

/**
 *
 * @author rferranti
 */
public class LinkedListFactory<E> implements CollectionFactory<LinkedList<E>, E> {

    @Override
    public LinkedList<E> create() {
        return new LinkedList<E>();
    }
}
