package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Composes an iterator of endodelegates.
 *
 * @author rferranti
 * @param <T> the delegate parameter type, the delegate result type
 */
public class EndoDelegatesComposer<T> implements Function<Iterator<Function<T, T>>, Function<T, T>> {

    @Override
    public Function<T, T> apply(Iterator<Function<T, T>> endoDelegates) {
        dbc.precondition(endoDelegates != null, "cannot compose a null iterator of endoDelegates");
        Function<T, T> current = UnaryOperator.identity();
        while (endoDelegates.hasNext()) {
            current = new Composer<T, T, T>(current, endoDelegates.next());
        }
        return current;
    }
}
