package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.concepts.EqualsBuilder;
import net.emaze.dysfunctional.concepts.HashCodeBuilder;

/**
 * Two things
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
}
