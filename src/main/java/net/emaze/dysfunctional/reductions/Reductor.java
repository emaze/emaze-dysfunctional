package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A unary delegate reducing the {@literal Iterator<E>} to {@literal R}.
 *
 * @param <E> the iterator element type
 * @param <R> the result element type
 * @author rferranti
 */
public class Reductor<E, R> implements Function<Iterator<E>, R> {

    private final BiFunction<R, E, R> delegate;
    private final R init;

    public Reductor(BiFunction<R, E, R> delegate, R init) {
        dbc.precondition(delegate != null, "cannot create a Reductor with a null delegate");
        this.delegate = delegate;
        this.init = init;
    }

    @Override
    public R apply(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "consuming a null iterator");
        R current = init;
        while (iterator.hasNext()) {
            current = delegate.apply(current, iterator.next());
        }
        return current;
    }
}
