package net.emaze.disfunctional.iterations;

import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class CountingIterator implements Iterator<Integer> {
    private int counter;
    private int upTo = Integer.MAX_VALUE;
    
    public CountingIterator(int from, int upTo) {
        this.counter = from;
        this.upTo = upTo;
    }

    public boolean hasNext() {
        return counter != upTo;
    }

    public Integer next() {
        return ++counter;
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
