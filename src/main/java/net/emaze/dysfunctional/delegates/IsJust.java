package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author dangelocola
 */
public class IsJust<T> implements Predicate<Maybe<T>> {

    @Override
    public boolean test(Maybe<T> element) {
        return element.hasValue();
    }

}
