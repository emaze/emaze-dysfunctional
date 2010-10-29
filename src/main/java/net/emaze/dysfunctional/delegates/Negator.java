package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * negates a predicate
 *  x => !predicate(x)
 * @param <T> 
 * @author rferranti
 */
public class Negator<T> implements Predicate<T> {

    private final Predicate<T> predicate;

    public Negator(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        this.predicate = predicate;
    }

    /**
     * tests the nested predicate and negates it
     * @param element the element used to test the predicate
     * @return
     */
    @Override
    public boolean test(T element) {
        return !predicate.test(element);
    }
}
