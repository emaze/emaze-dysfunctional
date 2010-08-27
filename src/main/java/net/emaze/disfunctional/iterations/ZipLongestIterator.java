package net.emaze.disfunctional.iterations;

import net.emaze.disfunctional.tuples.Pair;
import java.util.Iterator;
import net.emaze.disfunctional.Maybe;

/**
 *
 * @author rferranti
 */
public class ZipLongestIterator<E1,E2> implements Iterator<Pair<Maybe<E1>,Maybe<E2>>>{

    private final MaybeIterator<E1> former;
    private final MaybeIterator<E2> latter;

    public ZipLongestIterator(Iterator<E1> former, Iterator<E2> latter) {
        this.former = new MaybeIterator(former);
        this.latter = new MaybeIterator(latter);
    }

    public boolean hasNext() {
        return former.hasNext() || latter.hasNext();
    }

    /**
     * iterating over the longest iterator gives a Pair of Maybe.nothing indefinitely
     * "no matter how many times you try, you can't shoot the dog"
     * @return
     */
    public Pair<Maybe<E1>,Maybe<E2>> next() {
        return new Pair(former.next(), latter.next());
    }

    public void remove() {
        former.remove();
        latter.remove();
    }

}
