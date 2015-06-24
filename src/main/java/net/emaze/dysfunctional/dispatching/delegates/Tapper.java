package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Applies an consumer to an element and returns the (possibly modified) element.
 *
 * @author rferranti
 * @param <T> the parameter type
 */
public class Tapper<T> implements UnaryOperator<T> {

    private final Consumer<T> consumer;

    public Tapper(Consumer<T> consumer) {
        dbc.precondition(consumer != null, "cannot create a Tapper with a null consumer");
        this.consumer = consumer;
    }

    @Override
    public T apply(T t) {
        consumer.accept(t);
        return t;
    }
}
