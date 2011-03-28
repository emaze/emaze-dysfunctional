package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class CapturingAction<T> implements Action<T> {

    public final Box<T> first = new Box<T>();
    public final Action<T> nested;

    public CapturingAction(Action<T> nested) {
        dbc.precondition(nested != null, "cannot capture from a null action");
        this.nested = nested;
    }

    @Override
    public void perform(T value) {
        this.first.setContent(value);
        nested.perform(value);
    }
}
