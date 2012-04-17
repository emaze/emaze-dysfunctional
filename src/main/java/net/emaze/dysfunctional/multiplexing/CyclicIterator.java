package net.emaze.dysfunctional.multiplexing;

import java.util.Iterator;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

public class CyclicIterator<T> extends ReadOnlyIterator<T> {

    private final Iterable<T> matrix;
    private Iterator<T> current;
    
    public CyclicIterator(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(iterator.hasNext(), "iterator cannot be empty");
        this.matrix = Consumers.all(iterator);
        this.current = matrix.iterator();
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        if(current.hasNext() == false) {
            current = matrix.iterator();
        }
        return current.next();
    }
}
