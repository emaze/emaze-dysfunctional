package net.emaze.disfunctional.iterations;

import net.emaze.disfunctional.tuples.Pair;
import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class ZipShortestIterator<E1,E2> implements Iterator<Pair<E1,E2>>{

    private final Iterator<E1> former;
    private final Iterator<E2> latter;

    public ZipShortestIterator(Iterator<E1> former, Iterator<E2> latter) {
        this.former = former;
        this.latter = latter;
    }

    public boolean hasNext() {
        return former.hasNext() && latter.hasNext();
    }

    public Pair<E1,E2> next() {
        return new Pair(former.next(), latter.next());
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
