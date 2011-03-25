package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.consumers.Consumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 * Reduces the Iterator<E> to R
 * @param <E> 
 * @author rferranti
 */
public class Reductor<R, E> implements Consumer<R, Iterator<E>> {

    private final BinaryDelegate<R, R, E> delegate;
    private final R init;

    public Reductor(BinaryDelegate<R, R, E> delegate, R init) {
        dbc.precondition(delegate != null, "cannot create a Reductor with a null delegate");
        this.delegate = delegate;
        this.init = init;
    }

    @Override
    public R consume(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "consuming a null iterator");
        R current = init;
        while (iterator.hasNext()) {
            current = delegate.perform(current, iterator.next());
        }
        return current;
    }
}
