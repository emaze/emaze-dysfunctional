package net.emaze.dysfunctional.delegates;

/**
 *
 * @param <T> 
 * @author dangelocola
 */
public class NotNull<T> implements Predicate<T> {

    @Override
    public boolean test(T element) {
        return element != null;
    }

}
