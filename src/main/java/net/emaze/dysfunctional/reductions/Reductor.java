package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A unary function reducing the {@literal Iterator<E>} to {@literal R}.
 *
 * @param <E> the iterator element type
 * @param <R> the result element type
 * @author rferranti
 */
public class Reductor<E, R> implements Function<Iterator<E>, R> {

    private final BiFunction<R, E, R> function;
    private final R init;

    public Reductor(BiFunction<R, E, R> function, R init) {
        dbc.precondition(function != null, "cannot create a Reductor with a null function");
        this.function = function;
        this.init = init;
    }

    @Override
    public R apply(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "consuming a null iterator");
        R current = init;
        while (iterator.hasNext()) {
            current = function.apply(current, iterator.next());
        }
        return current;
    }
}
