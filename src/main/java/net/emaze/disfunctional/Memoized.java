package net.emaze.disfunctional;

/**
 *
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
        if (!memory.hasValue()) {
            throw new IllegalStateException("a value should be memoized before being fetched");
        }
        return memory.value();
    }
}
