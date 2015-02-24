package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a ternary consumer capturing parameters.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 */
public class TernaryCapturingConsumer<T1, T2, T3> implements TriConsumer<T1, T2, T3> {

    private final TriConsumer<T1, T2, T3> nested;
    private final Box<T1> param1;
    private final Box<T2> param2;
    private final Box<T3> param3;

    public TernaryCapturingConsumer(TriConsumer<T1, T2, T3> nested, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        dbc.precondition(nested != null, "cannot capture from a null consumer");
        dbc.precondition(param1 != null, "cannot capture with a null param1 box");
        dbc.precondition(param2 != null, "cannot capture from a null param2 box");
        dbc.precondition(param3 != null, "cannot capture from a null param3 box");
        this.nested = nested;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    @Override
    public void accept(T1 first, T2 second, T3 third) {
        param1.setContent(first);
        param2.setContent(second);
        param3.setContent(third);
        nested.accept(first, second, third);
    }
}
