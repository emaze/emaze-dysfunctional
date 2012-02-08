package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A unary delegate yielding plucking an iterator from an iterable.
 *
 * @param <E> the iterable element type
 * @param <I> the iterable type
 * @author rferranti
 */
public class IteratorPlucker<E, I extends Iterable<E>> implements Delegate<Iterator<E>, I> {

    @Override
    public Iterator<E> perform(I iterable) {
        dbc.precondition(iterable != null, "iterable cannot be null");
        return iterable.iterator();
    }
}
