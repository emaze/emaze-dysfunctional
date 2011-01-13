package net.emaze.dysfunctional.collections;

import java.util.ArrayList;

/**
 *
 * @author rferranti
 */
public class ArrayListFactory<E> implements CollectionFactory<ArrayList<E>, E> {

    @Override
    public ArrayList<E> create() {
        return new ArrayList<E>();
    }
}
