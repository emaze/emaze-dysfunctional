package net.emaze.dysfunctional.casts;

import java.util.function.Function;

/**
 * Casts a value.
 *
 * Usually composed with other functors to make the simulate covaraiant /
 * controvariant types.
 *
 * <pre> E.g:
   final Function<Integer, Integer> source = new Identity<Integer>();
   final Function<Integer, Number> got = Compositions.compose(new Vary<Number, Integer>(), source);
 * </pre>
 *
 * @author rferranti
 * @param <T> the source type
 * @param <R> the result type
 */
public class Vary<T, R> implements Function<T, R> {

    @Override
    public R apply(T value) {
        return (R) value;
    }
}
