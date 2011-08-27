package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class TernaryCapturingPredicate<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final Box<T1> first;
    private final Box<T2> second;
    private final Box<T3> third;
    private final TernaryPredicate<T1, T2, T3> nested;

    public TernaryCapturingPredicate(TernaryPredicate<T1, T2, T3> nested, Box<T1> first, Box<T2> second, Box<T3> third) {
        dbc.precondition(nested != null, "cannot capture from a null predicate");
        dbc.precondition(first != null, "cannot capture with a null first box");
        dbc.precondition(second != null, "cannot capture with a null second box");
        dbc.precondition(third != null, "cannot capture with a null third box");
        this.nested = nested;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        this.first.setContent(first);
        this.second.setContent(second);
        this.third.setContent(third);
        return nested.accept(first, second, third);
    }
}
