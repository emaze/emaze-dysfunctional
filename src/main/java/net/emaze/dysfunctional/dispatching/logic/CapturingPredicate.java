package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class CapturingPredicate<T> implements Predicate<T> {

    private final Box<T> box;
    private final Predicate<T> nested;

    public CapturingPredicate(Predicate<T> nested, Box<T> box) {
        dbc.precondition(nested != null, "cannot capture from a null predicate");
        dbc.precondition(box != null, "cannot capture with a null box");
        this.nested = nested;
        this.box = box;
    }

    @Override
    public boolean accept(T value) {
        box.setContent(value);
        return nested.accept(value);
    }
}
