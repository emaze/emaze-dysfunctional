package net.emaze.dysfunctional.dispatching.spying;

import java.util.function.BiConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a binary consumer capturing parameters.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class BinaryCapturingConsumer<T1, T2> implements BiConsumer<T1, T2> {

    private final BiConsumer<T1, T2> nested;
    private final Box<T1> param1;
    private final Box<T2> param2;

    public BinaryCapturingConsumer(BiConsumer<T1, T2> nested, Box<T1> param1, Box<T2> param2) {
        dbc.precondition(nested != null, "cannot capture from a null consumer");
        dbc.precondition(param1 != null, "cannot capture with a null param1 box");
        dbc.precondition(param2 != null, "cannot capture from a null param2 box");
        this.nested = nested;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public void accept(T1 former, T2 latter) {
        param1.setContent(former);
        param2.setContent(latter);
        nested.accept(former, latter);
    }
}
