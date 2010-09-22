package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.tuples.Pair;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.ranges.Range;

/**
 * Decorates an iterator used to count iterated element via yielding a Pair<Integer,E>
 * @author rferranti
 */
public class CountedIterator<T,E> implements Iterator<Pair<T, E>> {

    private final ZipShortestIterator<T, E> zipped;

    public CountedIterator(Iterator<E> iterator, Range<T> range ) {
        dbc.precondition(iterator != null, "trying to create a CountedIterator from a null iterator");
        zipped = new ZipShortestIterator<T, E>( range.iterator() , iterator);
    }

    @Override
    public boolean hasNext() {
        return zipped.hasNext();
    }

    @Override
    public Pair<T, E> next() {
        return zipped.next();
    }

    @Override
    public void remove() {
        zipped.remove();
    }
}
