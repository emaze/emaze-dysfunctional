package net.emaze.dysfunctional.collections;

import java.util.ArrayList;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Creates an empty ArrayList.
 * @param <E> the ArrayList element type parameter
 * @author rferranti
 */
public class ArrayListFactory<E> implements Provider<ArrayList<E>> {

    @Override
    public ArrayList<E> provide() {
        return new ArrayList<E>();
    }
}
