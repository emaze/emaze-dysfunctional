package net.emaze.dysfunctional.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite binary predicate yielding true when every predicate match
 * (no further predicate is evaluated beyond the first returning false)
 * @param <E1>
 * @param <E2>
 * @author rferranti
 */
public class AllMatchingBinaryPredicate<E1, E2> implements CompositeBinaryPredicate<E1, E2> {

    private final List<BinaryPredicate<E1, E2>> predicates = new ArrayList<BinaryPredicate<E1, E2>>();

    @Override
    public boolean test(E1 former, E2 latter) {
        for (BinaryPredicate<E1, E2> predicate : predicates) {
            if (!predicate.test(former, latter)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void add(BinaryPredicate<E1, E2> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to add a null predicate");
        predicates.add(aPredicate);
    }

    @Override
    public boolean remove(BinaryPredicate<E1, E2> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to remove a null predicate");
        return predicates.remove(aPredicate);
    }

    @Override
    public void setFunctors(Collection<BinaryPredicate<E1, E2>> functors) {
        dbc.precondition(functors != null, "functors cannot be null");
        this.predicates.clear();
        this.predicates.addAll(functors);
    }
}
