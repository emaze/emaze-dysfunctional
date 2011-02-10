package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.casts.Casts;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * Two things
 * @param <E1>
 * @param <E2> 
 * @author rferranti
 */
public class Pair<E1, E2> {

    private final E1 first;
    private final E2 second;

    public Pair(E1 f, E2 l) {
        this.first = f;
        this.second = l;
    }

    public E1 first() {
        return first;
    }

    public E2 second() {
        return second;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Pair == false) {
            return false;
        }
        final Pair<E1, E2> other = Casts.widen(rhs);
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

    public static <E1, E2> Pair<E1, E2> of(E1 first, E2 second) {
        return new Pair<E1, E2>(first, second);
    }
}
