package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Adapts a unary action handling triples to a ternary action.
 * @param <R> the result type parameter
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class UnaryToTernaryAction<T1, T2, T3> implements TernaryAction<T1, T2, T3> {

    private final Consumer< Triple<T1, T2, T3>> action;

    public UnaryToTernaryAction(Consumer<Triple<T1, T2, T3>> action) {
        dbc.precondition(action != null, "cannot create a UnaryToTernaryAction with a null Action");
        this.action = action;
    }

    @Override
    public void perform(T1 first, T2 second, T3 third) {
        action.accept(Triple.of(first, second, third));
    }
}
