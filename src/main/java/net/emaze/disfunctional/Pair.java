package net.emaze.disfunctional;

import net.emaze.disfunctional.concepts.EqualsBuilder;
import net.emaze.disfunctional.concepts.HashCodeBuilder;

/**
 *
 * @author rferranti
 */
public class Pair<E1, E2> {

    private final E1 f;
    private final E2 l;

    Pair(E1 f, E2 l) {
        this.f = f;
        this.l = l;
    }

    public E1 former() {
        return f;
    }

    public E2 latter() {
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
