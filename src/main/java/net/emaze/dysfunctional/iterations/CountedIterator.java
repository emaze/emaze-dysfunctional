package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.tuples.Pair;
import java.util.Iterator;
import net.emaze.dysfunctional.iterations.sequencing.IntegerSequencingPolicy;

/**
 *
 * @author rferranti
 */
public class CountedIterator<E> implements Iterator<Pair<Integer, E>> {

    private final ZipShortestIterator<Integer, E> zipped;

    public CountedIterator(Iterator<E> iterator, int from, int upTo) {
        zipped = new ZipShortestIterator<Integer, E>(new RangeIterator<Integer>(new IntegerSequencingPolicy(),from, upTo), iterator);
    }

    @Override
    public boolean hasNext() {
        return zipped.hasNext();
    }

    @Override
    public Pair<Integer, E> next() {
        return zipped.next();
    }

    @Override
    public void remove() {
        zipped.remove();
    }
}
