package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a ternary function to a ternary consumer. Adapting is performed by
 * ignoring the adapted function result.
 *
 * @author rferranti
 * @param <R> the adapted function result type
 * @param <T1> the adapted function first parameter type
 * @param <T2> the adapted function second parameter type
 * @param <T3> the adapted function third parameter type
 */
public class TernaryFunctionToConsumer<T1, T2, T3, R> implements TriConsumer<T1, T2, T3> {

    private final TriFunction<T1, T2, T3, R> adapted;

    public TernaryFunctionToConsumer(TriFunction<T1, T2, T3, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null ternary function to ternary consumer");
        this.adapted = adaptee;
    }

    @Override
    public void accept(T1 first, T2 second, T3 third) {
        adapted.apply(first, second, third);
    }
}
