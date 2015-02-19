package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies an action capturing parameter.
 *
 * @author rferranti
 * @param <T> the parameter type
 */
public class CapturingAction<T> implements Consumer<T> {

    private final Consumer<T> nested;
    private final Box<T> param;

    public CapturingAction(Consumer<T> nested, Box<T> param) {
        dbc.precondition(nested != null, "cannot capture from a null action");
        dbc.precondition(param != null, "cannot capture with a null param box");
        this.nested = nested;
        this.param = param;
    }

    @Override
    public void accept(T value) {
        param.setContent(value);
        nested.accept(value);
    }
}
