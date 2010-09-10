package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rferranti
 */
public class FirstMatchingPredicate<E> implements Predicate<E>, Multicasting<Predicate<E>> {

    private final List<Predicate<E>> predicates = new ArrayList<Predicate<E>>();

    public boolean test(E element) {
        for(Predicate<E> predicate : predicates){
            if(predicate.test(element)){
                return true;
            }
        }
        return false;
    }
    
    public void add(Predicate<E> aPredicate) {
        predicates.add(aPredicate);
    }

    public void remove(Predicate<E> aPredicate) {
        predicates.remove(aPredicate);
    }

}
