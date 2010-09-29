package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * Responsibility: Holds a memoized and invalidable value (i.e: nonexpiring cache)
 * @param <T> 
 * @author rferranti
 */
public class Memoized<T> {

    private Maybe<T> memory = Maybe.nothing();

    public void invalidate() {
        memory = Maybe.nothing();
    }

    public void memoize(T value) {
        memory = Maybe.just(value);
    }

    public boolean isMemoized() {
        return memory.hasValue();
    }

    public T value() {
        dbc.stateprecondition(memory.hasValue(), "a value should be memoized before being fetched");
        return memory.value();
    }
}
