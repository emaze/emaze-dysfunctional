package net.emaze.dysfunctional.convolutions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import java.util.Optional;
import net.emaze.dysfunctional.options.OptionalIterator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * Adapts two iterators yielding their longest convolution (via a
 * Pair<Optional<E1>,Optional<E2>>)
 *
 * @param <E1> the first iterator element type
 * @param <E2> the second iterator element type
 * @author rferranti
 */
public class ZipLongestIterator<E1, E2> extends ReadOnlyIterator<Pair<Optional<E1>, Optional<E2>>> {

    private final OptionalIterator<E1> former;
    private final OptionalIterator<E2> latter;

    public ZipLongestIterator(Iterator<E1> former, Iterator<E2> latter) {
        dbc.precondition(former != null, "trying to create a ZipLongestIterator from a null iterator (former)");
        dbc.precondition(latter != null, "trying to create a ZipLongestIterator from a null iterator (latter)");
        this.former = new OptionalIterator<E1>(former);
        this.latter = new OptionalIterator<E2>(latter);
    }

    @Override
    public boolean hasNext() {
        return former.hasNext() || latter.hasNext();
    }

    /**
     * iterating over the longest iterator gives a Pair of Optional.nothing
 indefinitely "no matter how many times you try, you can't shoot the dog"
     *
     * @return
     */
    @Override
    public Pair<Optional<E1>, Optional<E2>> next() {
        return Pair.of(former.next(), latter.next());
    }
}
