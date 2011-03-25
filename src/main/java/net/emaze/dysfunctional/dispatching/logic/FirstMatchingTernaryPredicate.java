package net.emaze.dysfunctional.dispatching.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite ternary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 * @param <E1>
 * @param <E2>
 * @param <E3>
 * @author rferranti
 */
public class FirstMatchingTernaryPredicate<E1, E2, E3> implements CompositeTernaryPredicate<E1, E2, E3> {

    private final List<TernaryPredicate<E1, E2, E3>> predicates = new ArrayList<TernaryPredicate<E1, E2, E3>>();

    @Override
    public boolean accept(E1 first, E2 second, E3 third) {
        for (TernaryPredicate<E1, E2, E3> predicate : predicates) {
            if (predicate.accept(first, second, third)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(TernaryPredicate<E1, E2, E3> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to add a null predicate");
        predicates.add(aPredicate);
    }

    @Override
    public boolean remove(TernaryPredicate<E1, E2, E3> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to remove a null predicate");
        return predicates.remove(aPredicate);
    }

    @Override
    public void setFunctors(Collection<TernaryPredicate<E1, E2, E3>> functors) {
        dbc.precondition(functors != null, "functors cannot be null");
        this.predicates.clear();
        this.predicates.addAll(functors);
    }
}
