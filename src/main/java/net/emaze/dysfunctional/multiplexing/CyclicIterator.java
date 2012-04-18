package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.consumers.ConsumeIntoCollection;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 * A cyclic iterator. The given iterator is iterated indefinitely.
 *
 * @param <E> the element type
 * @author mcodella, asturman
 */
public class CyclicIterator<E> extends ReadOnlyIterator<E> {

    private final Iterable<E> matrix;
    private Iterator<E> current;
    
    public CyclicIterator(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(iterator.hasNext(), "iterator cannot be empty");
        this.matrix = new ConsumeIntoCollection<ArrayList<E>, E>(new ArrayListFactory<E>()).perform(iterator);
        this.current = matrix.iterator();
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public E next() {
        if(current.hasNext() == false) {
            current = matrix.iterator();
        }
        return current.next();
    }
}
