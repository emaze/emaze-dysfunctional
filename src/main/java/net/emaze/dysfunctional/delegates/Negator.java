package net.emaze.dysfunctional.delegates;

/**
 * negates a predicate
 *  x => !predicate(x)
 * @param <T> 
 * @author rferranti
 */
public class Negator<T> implements Predicate<T> {

    private final Predicate<T> predicate;

    public Negator(Predicate<T> predicate) {
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
