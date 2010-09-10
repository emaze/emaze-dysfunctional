package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite unary predicate returning true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 * @author rferranti
 */
public class FirstMatchingPredicate<E> implements Predicate<E>, Multicasting<Predicate<E>> {

    private final List<Predicate<E>> predicates = new ArrayList<Predicate<E>>();

    @Override
    public boolean test(E element) {
        for(Predicate<E> predicate : predicates){
            if(predicate.test(element)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void add(Predicate<E> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to add a null predicate");
        predicates.add(aPredicate);
    }

    @Override
    public void remove(Predicate<E> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to remove a null predicate");
        predicates.remove(aPredicate);
    }

    @Override
    public void setFunctors(Collection<Predicate<E>> functors) {
        this.predicates.clear();
        this.predicates.addAll(functors);
    }

}
