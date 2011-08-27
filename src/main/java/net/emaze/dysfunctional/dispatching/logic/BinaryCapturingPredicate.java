package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class BinaryCapturingPredicate<T1, T2> implements BinaryPredicate<T1, T2> {

    private final Box<T1> first;
    private final Box<T2> second;
    private final BinaryPredicate<T1, T2> nested;

    public BinaryCapturingPredicate(BinaryPredicate<T1, T2> nested, Box<T1> first, Box<T2> second) {
        dbc.precondition(nested != null, "cannot capture from a null predicate");
        dbc.precondition(first != null, "cannot capture with a null first box");
        dbc.precondition(second != null, "cannot capture with a null second box");
        this.nested = nested;
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean accept(T1 former, T2 latter) {
        first.setContent(former);
        second.setContent(latter);
        return nested.accept(former, latter);
    }
}
