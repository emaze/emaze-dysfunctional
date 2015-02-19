package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

/**
 * Adapts a unary action handling pairs to a binary action.
 *
 * @param <R> the result type parameter
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class UnaryToBinaryAction<T1, T2> implements BiConsumer<T1, T2> {

    private final Consumer<Pair<T1, T2>> action;

    public UnaryToBinaryAction(Consumer<Pair<T1, T2>> action) {
        dbc.precondition(action != null, "cannot create a UnaryToBinaryAction with a null Action");
        this.action = action;
    }

    @Override
    public void accept(T1 first, T2 second) {
        action.accept(Pair.of(first, second));
    }
}
