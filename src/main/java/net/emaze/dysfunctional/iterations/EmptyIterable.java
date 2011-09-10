package net.emaze.dysfunctional.iterations;

import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class EmptyIterable<T> implements Iterable<T> {
    
    private final Iterator<T> iterator = new EmptyIterator<T>();
    
    @Override
    public Iterator<T> iterator() {
        return iterator;
    }
    
}
