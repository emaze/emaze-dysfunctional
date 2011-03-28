package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class BinaryCapturingAction<T1, T2> implements BinaryAction<T1, T2> {

    public final Box<T1> first = new Box<T1>();
    public final Box<T2> second = new Box<T2>();
    public final BinaryAction<T1, T2> nested;

    public BinaryCapturingAction(BinaryAction<T1, T2> nested) {
        dbc.precondition(nested != null, "cannot capture from a null action");
        this.nested = nested;
    }

    @Override
    public void perform(T1 former, T2 latter) {
        this.first.setContent(former);
        this.second.setContent(latter);
        nested.perform(former, latter);
    }
}
