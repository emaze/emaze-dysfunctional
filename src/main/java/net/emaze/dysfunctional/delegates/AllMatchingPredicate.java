package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite unary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 * @param <E> the element Type
 * @author rferranti
 */
public class AllMatchingPredicate<E> implements Predicate<E>, Multicasting<Predicate<E>> {

    private final List<Predicate<E>> predicates = new ArrayList<Predicate<E>>();

    public AllMatchingPredicate(){}

    public AllMatchingPredicate(Collection<Predicate<E>> predicates){
        this.predicates.addAll(predicates);
    }

    @Override
    public boolean test(E element) {
        dbc.precondition(!predicates.isEmpty(), "try to evaluate an element without any predicate");
        for(Predicate<E> predicate : predicates){
            if(!predicate.test(element)){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void add(Predicate<E> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to add a null predicate");
        predicates.add(aPredicate);
    }

    @Override
    public boolean remove(Predicate<E> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to remove a null predicate");
        return predicates.remove(aPredicate);
    }

    @Override
    public void setFunctors(Collection<Predicate<E>> functors) {
        dbc.precondition(functors != null, "functors cannot be null");
        this.predicates.clear();
        this.predicates.addAll(functors);
    }

}
