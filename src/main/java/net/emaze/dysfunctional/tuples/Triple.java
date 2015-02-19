package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * a Pair + 1 at the same price
 *
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @param <T3> the third element type
 * @author rferranti
 */
public class Triple<T1, T2, T3> {

    private final T1 f;
    private final T2 s;
    private final T3 t;

    public Triple(T1 first, T2 second, T3 third) {
        this.f = first;
        this.s = second;
        this.t = third;
    }

    public T1 first() {
        return f;
    }

    public T2 second() {
        return s;
    }

    public T3 third() {
        return t;
    }

    public <R1, R2, R3> Triple<R1, R2, R3> fmap(Function<T1, R1> withFirst, Function<T2, R2> withSecond, Function<T3, R3> withThird) {
        dbc.precondition(withFirst != null, "cannot fmap on triple with a null first delegate");
        dbc.precondition(withSecond != null, "cannot fmap on triple with a null second delegate");
        dbc.precondition(withThird != null, "cannot fmap on triple with a null second delegate");
        return Triple.of(withFirst.apply(f), withSecond.apply(s), withThird.apply(t));
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Triple == false) {
            return false;
        }
        final Triple<T1, T2, T3> other = (Triple<T1, T2, T3>) rhs;
        return new EqualsBuilder().append(this.f, other.f).
                append(this.s, other.s).
                append(this.t, other.t).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.f).
                append(this.s).
                append(this.t).
                toHashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s,%s,%s)", f, s, t);
    }

    public static <T1, T2, T3> Triple<T1, T2, T3> of(T1 first, T2 second, T3 third) {
        return new Triple<T1, T2, T3>(first, second, third);
    }
}
