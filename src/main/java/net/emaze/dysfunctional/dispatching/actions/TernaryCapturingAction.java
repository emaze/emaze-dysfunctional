package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class TernaryCapturingAction<T1, T2, T3> implements TernaryAction<T1, T2, T3> {

    public final Box<T1> first = new Box<T1>();
    public final Box<T2> second = new Box<T2>();
    public final Box<T3> third = new Box<T3>();
    public final TernaryAction<T1, T2, T3> nested;

    public TernaryCapturingAction(TernaryAction<T1, T2, T3> nested) {
        dbc.precondition(nested != null, "cannot capture from a null action");
        this.nested = nested;
    }

    @Override
    public void perform(T1 first, T2 second, T3 third) {
        this.first.setContent(first);
        this.second.setContent(second);
        this.third.setContent(third);
        nested.perform(first, second, third);
    }
}
