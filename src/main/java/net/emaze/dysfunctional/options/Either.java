package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * Either type represents values with two possibilities. A value of type
 * {@literal Either<Left,Right>} is either Left or Right.
 *
 * @author rferranti
 * @param <LT> the left type
 * @param <RT> the right type
 */
public class Either<LT, RT> {

    private final Maybe<LT> left;
    private final Maybe<RT> right;

    public Either(Maybe<LT> left, Maybe<RT> right) {
        dbc.precondition(left != null, "cannot create Either with null left");
        dbc.precondition(right != null, "cannot create Either with null right");
        dbc.precondition(left.hasValue() != right.hasValue(), "Either left or right must have a value");
        this.left = left;
        this.right = right;
    }

    public <LR, RR> Either<LR, RR> fmap(Delegate<LR, LT> withLeft, Delegate<RR, RT> withRight) {
        dbc.precondition(withLeft != null, "cannot fmap an either with a null left delegate");
        dbc.precondition(withRight != null, "cannot fmap an either with a null right delegate");
        if (left.hasValue()) {
            return Either.left(withLeft.perform(left.value()));
        }
        return Either.right(withRight.perform(right.value()));
    }

    public Maybe<RT> maybe() {
        return right;
    }

    public Either<RT, LT> flip() {
        return new Either<RT, LT>(right, left);
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
        final Either<LT, RT> other = (Either<LT, RT>) rhs;
        return new EqualsBuilder().append(this.left, other.left).
                append(this.right, other.right).
                isEquals();
    }

    @Override
    public String toString() {
        if (left.hasValue()) {
            return String.format("Left %s", left.value());
        }
        return String.format("Right %s", right.value());
    }

    public static <T1, T2> Either<T1, T2> left(T1 left) {
        return new Either<T1, T2>(Maybe.just(left), Maybe.<T2>nothing());
    }

    public static <T1, T2> Either<T1, T2> right(T2 right) {
        return new Either<T1, T2>(Maybe.<T1>nothing(), Maybe.just(right));
    }
}
