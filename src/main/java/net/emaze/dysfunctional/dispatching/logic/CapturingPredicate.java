package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.options.Box;

public class CapturingPredicate<T> implements Predicate<T> {

    public final Box<T> first = new Box<T>();
    public final Predicate<T> nested;

    public CapturingPredicate(Predicate<T> nested) {
        dbc.precondition(nested != null, "cannot capture from a null predicate");
        this.nested = nested;
    }

    @Override
    public boolean accept(T value) {
        this.first.setContent(value);
        return nested.accept(value);
    }
}
