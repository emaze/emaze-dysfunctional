package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Composes an iterator of unary operators.
 *
 * @author rferranti
 * @param <T> the function parameter type, the function result type
 */
public class UnaryOperatorsComposer<T> implements Function<Iterator<Function<T, T>>, UnaryOperator<T>> {

    @Override
    public UnaryOperator<T> apply(Iterator<Function<T, T>> endoDelegates) {
        dbc.precondition(endoDelegates != null, "cannot compose a null iterator of endoDelegates");
        Function<T, T> current = Function.identity();
        while (endoDelegates.hasNext()) {
            current = new Composer<T, T, T>(current, endoDelegates.next());
        }
        return current::apply;
    }
}
