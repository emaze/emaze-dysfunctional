package net.emaze.disfunctional;

import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class CountedIterator<E> implements Iterator<Pair<Integer, E>> {

    private final ZipShortestIterator<Integer, E> zipped;

    public CountedIterator(Iterator<E> iterator, int counter) {
        zipped = new ZipShortestIterator<Integer, E>(new CountingIterator(counter), iterator);
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
