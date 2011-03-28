package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class BinaryCapturingPredicate<T1, T2> implements BinaryPredicate<T1, T2> {

    public final Box<T1> first = new Box<T1>();
    public final Box<T2> second = new Box<T2>();
    public final BinaryPredicate<T1, T2> nested;

    public BinaryCapturingPredicate(BinaryPredicate<T1, T2> nested) {
        dbc.precondition(nested != null, "cannot capture from a null predicate");
        this.nested = nested;
    }

    @Override
    public boolean accept(T1 former, T2 latter) {
        this.first.setContent(former);
        this.second.setContent(latter);
        return nested.accept(former, latter);
    }
}
