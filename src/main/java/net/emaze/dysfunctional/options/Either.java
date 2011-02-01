package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.casts.Casts;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * @author rferranti
 */
public class Either<T1, T2> {

    private final Maybe<T1> left;
    private final Maybe<T2> right;

    public Either(Maybe<T1> left, Maybe<T2> right) {
        dbc.precondition(left != null, "cannot create Either with null left");
        dbc.precondition(right != null, "cannot create Either with null right");
        dbc.precondition(left.hasValue() != right.hasValue(), "Either left or right must have a value");
        this.left = left;
        this.right = right;
    }

    public <R> R withValue(Delegate<R, T1> withLeft, Delegate<R, T2> withRight) {
        if (left.hasValue()) {
            return withLeft.perform(left.value());
        }
        return withRight.perform(right.value());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(left).append(right).toHashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Either == false) {
            return false;
        }
        final Either<T1, T2> other = Casts.widen(rhs);
        return new EqualsBuilder().append(this.left, other.left).
                append(this.right, other.right).
                isEquals();
    }
}
