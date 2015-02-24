package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;

/**
 * Unary to nullary consumer adapter. Adapting is performed by currying the
 * parameter of the adapted consumer.
 *
 * @param <T> the adapted consumer parameter type
 * @author rferranti
 */
public class ConsumerBinder<T> implements Runnable {

    private final Consumer<T> adapted;
    private final T only;

    public ConsumerBinder(Consumer<T> adaptee, T only) {
        dbc.precondition(adaptee != null, "cannot bind parameter of a null consumer");
        this.adapted = adaptee;
        this.only = only;
    }

    @Override
    public void run() {
        adapted.accept(only);
    }
}
