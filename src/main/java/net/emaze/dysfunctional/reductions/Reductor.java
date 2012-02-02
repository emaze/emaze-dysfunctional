package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Reduces the Iterator<E> to R
 * @param <E> 
 * @author rferranti
 */
public class Reductor<R, E> implements Delegate<R, Iterator<E>> {

    private final BinaryDelegate<R, R, E> delegate;
    private final R init;

    public Reductor(BinaryDelegate<R, R, E> delegate, R init) {
        dbc.precondition(delegate != null, "cannot create a Reductor with a null delegate");
        this.delegate = delegate;
        this.init = init;
    }

    @Override
    public R perform(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "consuming a null iterator");
        R current = init;
        while (iterator.hasNext()) {
            current = delegate.perform(current, iterator.next());
        }
        return current;
    }
}
