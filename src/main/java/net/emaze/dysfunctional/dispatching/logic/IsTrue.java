package net.emaze.dysfunctional.dispatching.logic;

import java.util.function.Predicate;

/**
 * A unary predicate evaluating if passed element is true.
 *
 * Same as:
 * {@code Dispatching.predicate(new Identity<Boolean>()); }
 *
 * @author dangelocola
 */
public class IsTrue implements Predicate<Boolean> {

    @Override
    public boolean test(Boolean element) {
        return element;
    }
}
