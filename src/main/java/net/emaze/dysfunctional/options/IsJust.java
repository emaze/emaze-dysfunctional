package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.delegates.Predicate;

/**
 * Unary Predicate matching Maybe.just elements
 * @author dangelocola, rferranti
 */
public class IsJust<T> implements Predicate<Maybe<T>> {

    @Override
    public boolean test(Maybe<T> element) {
        return element.hasValue();
    }

}