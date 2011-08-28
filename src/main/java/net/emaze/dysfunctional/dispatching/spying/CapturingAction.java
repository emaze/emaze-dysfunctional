package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.options.Box;

public class CapturingAction<T> implements Action<T> {

    private final Action<T> nested;
    private final Box<T> param;

    public CapturingAction(Action<T> nested, Box<T> param) {
        dbc.precondition(nested != null, "cannot capture from a null action");
        dbc.precondition(param != null, "cannot capture with a null param box");
        this.nested = nested;
        this.param = param;
    }

    @Override
    public void perform(T value) {
        param.setContent(value);
        nested.perform(value);
    }
}
