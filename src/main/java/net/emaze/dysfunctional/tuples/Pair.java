package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * Two things
 * @param <E1>
 * @param <E2> 
 * @author rferranti
 */
public class Pair<E1, E2> {

    private final E1 f;
    private final E2 l;

    public Pair(E1 f, E2 l) {
        this.f = f;
        this.l = l;
    }

    public E1 first() {
        return f;
    }

    public E2 second() {
        return l;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Pair == false) {
            return false;
        }
        final Pair<E1, E2> other = (Pair<E1, E2>) rhs;
        return new EqualsBuilder().append(this.f, other.f).
                append(this.l, other.l).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.f).
                append(this.l).
                toHashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", f, l);
    }



}
