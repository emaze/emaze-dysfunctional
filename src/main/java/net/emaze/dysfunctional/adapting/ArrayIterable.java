package net.emaze.dysfunctional.adapting;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class ArrayIterable<T> implements Iterable<T> {

    private final T[] values;

    public ArrayIterable(T[] values) {
        dbc.precondition(values != null, "trying to create an ArrayToIterableAdapter<T> from a null array");
        this.values = values;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(values);
    }
}
