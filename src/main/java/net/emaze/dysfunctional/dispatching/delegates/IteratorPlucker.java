package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A unary delegate yielding plucking an iterator from an iterable.
 *
 * @param <I> the iterable type
 * @param <E> the iterable element type
 * @author rferranti
 */
public class IteratorPlucker<I extends Iterable<E>, E> implements Function<I, Iterator<E>> {

    @Override
    public Iterator<E> apply(I iterable) {
        dbc.precondition(iterable != null, "iterable cannot be null");
        return iterable.iterator();
    }
}
