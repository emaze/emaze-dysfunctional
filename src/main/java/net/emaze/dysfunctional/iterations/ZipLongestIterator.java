package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.tuples.Pair;
import java.util.Iterator;
import net.emaze.dysfunctional.Maybe;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class ZipLongestIterator<E1,E2> implements Iterator<Pair<Maybe<E1>,Maybe<E2>>>{

    private final MaybeIterator<E1> former;
    private final MaybeIterator<E2> latter;

    public ZipLongestIterator(Iterator<E1> former, Iterator<E2> latter) {
        dbc.precondition(former != null, "trying to create a ZipLongestIterator from a null iterator (former)");
        dbc.precondition(latter != null, "trying to create a ZipLongestIterator from a null iterator (latter)");
        this.former = new MaybeIterator(former);
        this.latter = new MaybeIterator(latter);
    }

    @Override
    public boolean hasNext() {
        return former.hasNext() || latter.hasNext();
    }

    /**
     * iterating over the longest iterator gives a Pair of Maybe.nothing indefinitely
     * "no matter how many times you try, you can't shoot the dog"
     * @return
     */
    @Override
    public Pair<Maybe<E1>,Maybe<E2>> next() {
        return new Pair(former.next(), latter.next());
    }

    @Override
    public void remove() {
        former.remove();
        latter.remove();
    }

}
