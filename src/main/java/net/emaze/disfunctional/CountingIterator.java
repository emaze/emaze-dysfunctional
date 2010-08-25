package net.emaze.disfunctional;

import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class CountingIterator implements Iterator<Integer> {
    private int counter;

    public CountingIterator() {
        this.counter = 0;
    }

    public CountingIterator(int counter) {
        this.counter = counter;
    }

    public boolean hasNext() {
        return counter != Integer.MAX_VALUE;
    }

    public Integer next() {
        return ++counter;
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
