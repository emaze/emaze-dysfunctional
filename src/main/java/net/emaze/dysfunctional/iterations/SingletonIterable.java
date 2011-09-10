package net.emaze.dysfunctional.iterations;

import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class SingletonIterable<T> implements Iterable<T> {

    private final T element;

    public SingletonIterable(T element) {
        this.element = element;
    }

    @Override
    public Iterator<T> iterator() {
        return new SingletonIterator<T>(element);
    }
}
