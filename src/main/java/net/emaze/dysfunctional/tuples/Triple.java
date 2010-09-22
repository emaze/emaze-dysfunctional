package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * a Pair + 1 at the same price
 * @author rferranti
 */
public class Triple<E1, E2, E3> {

    private final E1 f;
    private final E2 s;
    private final E3 t;

    public Triple(E1 first, E2 second, E3 third) {
        this.f = first;
        this.s = second;
        this.t = third;
    }

    public E1 first() {
        return f;
    }

    public E2 second() {
        return s;
    }

    public E3 third() {
        return t;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Triple == false) {
            return false;
        }
        final Triple<E1, E2, E3> other = (Triple<E1, E2, E3>) rhs;
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
}
