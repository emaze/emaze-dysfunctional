package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.delegates.Predicate;

/**
 * Unary Predicate matching Maybe.nothing elements
 * @author rferranti
 */
public class IsNothing<T> implements Predicate<Maybe<T>> {

    @Override
    public boolean test(Maybe<T> element) {
        return !element.hasValue();
    }

}
