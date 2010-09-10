package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
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
        predicates.add(aPredicate);
    }

    @Override
    public void remove(Predicate<E> aPredicate) {
        predicates.remove(aPredicate);
    }

    @Override
    public void setFunctors(Collection<Predicate<E>> functors) {
        this.predicates.clear();
        this.predicates.addAll(functors);
    }

}
