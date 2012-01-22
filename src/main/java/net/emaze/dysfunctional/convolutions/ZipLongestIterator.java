package net.emaze.dysfunctional.convolutions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * Adapts two iterators yielding their longest convolution (via a Pair<Maybe<E1>,Maybe<E2>>)
 * @param <E1>
 * @param <E2>
 * @author rferranti
 */
public class ZipLongestIterator<E1, E2> extends ReadOnlyIterator<Pair<Maybe<E1>, Maybe<E2>>> {

    private final MaybeIterator<E1> former;
    private final MaybeIterator<E2> latter;

    public ZipLongestIterator(Iterator<E1> former, Iterator<E2> latter) {
        dbc.precondition(former != null, "trying to create a ZipLongestIterator from a null iterator (former)");
        dbc.precondition(latter != null, "trying to create a ZipLongestIterator from a null iterator (latter)");
        this.former = new MaybeIterator<E1>(former);
        this.latter = new MaybeIterator<E2>(latter);
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
    public Pair<Maybe<E1>, Maybe<E2>> next() {
        return Pair.of(former.next(), latter.next());
    }
}
