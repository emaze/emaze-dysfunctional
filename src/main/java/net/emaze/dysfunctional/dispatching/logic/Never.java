package net.emaze.dysfunctional.dispatching.logic;

import java.util.function.Predicate;

/**
 * A unary predicate always returning false. ("never" returning true ).
 *
 * Same as {@code Predicate<E> ignore = Dispatching.ignore(new No(), E.class);}
 *
 * @param <E> the type parameter
 * @author rferranti
 */
public class Never<E> implements Predicate<E> {

    /**
     * Yields false.
     *
     * @param element the ignored element
     * @return false
     */
    @Override
    public boolean test(E element) {
        return false;
    }
}
