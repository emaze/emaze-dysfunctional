package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class TernaryCapturingPredicate<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    public final Box<T1> first = new Box<T1>();
    public final Box<T2> second = new Box<T2>();
    public final Box<T3> third = new Box<T3>();
    public final TernaryPredicate<T1, T2, T3> nested;

    public TernaryCapturingPredicate(TernaryPredicate<T1, T2, T3> nested) {
        dbc.precondition(nested != null, "cannot capture from a null predicate");
        this.nested = nested;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        this.first.setContent(first);
        this.second.setContent(second);
        this.third.setContent(third);
        return nested.accept(first, second, third);
    }
}
