package net.emaze.dysfunctional.collections;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Creates an empty ArrayList.
 * @param <E> the ArrayList element type parameter
 * @author rferranti
 */
public class ArrayListFactory<E> implements Supplier<ArrayList<E>> {

    @Override
    public ArrayList<E> get() {
        return new ArrayList<E>();
    }
}
