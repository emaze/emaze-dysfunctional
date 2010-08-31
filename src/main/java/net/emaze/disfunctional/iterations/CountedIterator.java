package net.emaze.disfunctional.iterations;

import net.emaze.disfunctional.tuples.Pair;
import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class CountedIterator<E> implements Iterator<Pair<Integer, E>> {

    private final ZipShortestIterator<Integer, E> zipped;

    public CountedIterator(Iterator<E> iterator, int from, int upTo) {
        zipped = new ZipShortestIterator<Integer, E>(new RangeIterator<Integer>(SequencingPolicy.INTEGER_POLICY,from, upTo), iterator);
    }

    public boolean hasNext() {
        return zipped.hasNext();
    }

    public Pair<Integer, E> next() {
        return zipped.next();
    }

    public void remove() {
        zipped.remove();
    }
}
