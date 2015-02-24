package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * Two things.
 *
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @author rferranti
 */
public class Pair<T1, T2> {

    private final T1 first;
    private final T2 second;

    public Pair(T1 f, T2 l) {
        this.first = f;
        this.second = l;
    }

    public T1 first() {
        return first;
    }

    public T2 second() {
        return second;
    }

    public <R1, R2> Pair<R1, R2> map(Function<T1, R1> withFirst, Function<T2, R2> withSecond) {
        dbc.precondition(withFirst != null, "cannot fmap on pair with a null first function");
        dbc.precondition(withSecond != null, "cannot fmap on pair with a null second function");
        return Pair.of(withFirst.apply(first), withSecond.apply(second));
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Pair == false) {
            return false;
        }
        final Pair<T1, T2> other = (Pair<T1, T2>) rhs;
        return new EqualsBuilder().append(this.first, other.first).
                append(this.second, other.second).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.first).
                append(this.second).
                toHashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", first, second);
    }

    public static <T1, T2> Pair<T1, T2> of(T1 first, T2 second) {
        return new Pair<T1, T2>(first, second);
    }
}
