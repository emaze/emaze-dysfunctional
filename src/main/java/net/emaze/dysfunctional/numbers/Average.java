package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.numbers.policies.SumPolicy;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * A binary function accumulating sum and count of passed values on a pair.
 *
 * @author rferranti
 * @param <T> the value type.
 */
public class Average<T> implements BiFunction<Pair<T, Long>, T, Pair<T, Long>> {

    private final SumPolicy<T, T, T> policy;

    public Average(SumPolicy<T, T, T> policy) {
        dbc.precondition(policy != null, "cannot create Average with a null sum policy");
        this.policy = policy;
    }

    @Override
    public Pair<T, Long> apply(Pair<T, Long> accumulator, T value) {
        final T sum = policy.sum(accumulator.first(), value);
        final long count = accumulator.second() + 1;
        return Pair.of(sum, count);
    }
}
