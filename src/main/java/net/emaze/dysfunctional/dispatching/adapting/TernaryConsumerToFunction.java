package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a ternary consumer to a ternary function. Adapter result type is Void
 * and always yields null.
 *
 * @param <T1> the adapted consumer first parameter type
 * @param <T2> the adapted consumer second parameter type
 * @param <T3> the adapted consumer third parameter type
 * @author rferranti
 */
public class TernaryConsumerToFunction<T1, T2, T3> implements TriFunction<T1, T2, T3, Void> {

    private final TriConsumer<T1, T2, T3> adapted;

    public TernaryConsumerToFunction(TriConsumer<T1, T2, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null ternary consumer to ternary function");
        this.adapted = adaptee;
    }

    @Override
    public Void apply(T1 first, T2 second, T3 third) {
        adapted.accept(first, second, third);
        return null;
    }
}
