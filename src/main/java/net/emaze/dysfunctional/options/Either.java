package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

/**
 * Either type represents values with two possibilities. A get of type
 * {@literal Either<Left,Right>} is either Left or Right.
 *
 * @author rferranti
 * @param <LT> the left type
 * @param <RT> the right type
 */
public class Either<LT, RT> {

    private final Optional<LT> left;
    private final Optional<RT> right;

    public Either(Optional<LT> left, Optional<RT> right) {
        dbc.precondition(left != null, "cannot create Either with null left");
        dbc.precondition(right != null, "cannot create Either with null right");
        dbc.precondition(left.isPresent() != right.isPresent(), "Either left or right must have a value");
        this.left = left;
        this.right = right;
    }

    public <LR, RR> Either<LR, RR> map(Function<LT, LR> withLeft, Function<RT, RR> withRight) {
        dbc.precondition(withLeft != null, "cannot fmap an either with a null left delegate");
        dbc.precondition(withRight != null, "cannot fmap an either with a null right delegate");
        if (left.isPresent()) {
            return Either.left(withLeft.apply(left.get()));
        }
        return Either.right(withRight.apply(right.get()));
    }

    public <T> T fold(Function<LT, T> withLeft, Function<RT, T> withRight) {
        dbc.precondition(withLeft != null, "cannot fold an either with a null left delegate");
        dbc.precondition(withRight != null, "cannot fold an either with a null right delegate");
        if (left.isPresent()) {
            return withLeft.apply(left.get());
        }
        return withRight.apply(right.get());
    }

    public Optional<LT> left() {
        return left;
    }

    public Optional<RT> right() {
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
        if (left.isPresent()) {
            return String.format("Left %s", left.get());
        }
        return String.format("Right %s", right.get());
    }

    public static <T1, T2> Either<T1, T2> left(T1 left) {
        dbc.precondition(left != null, "cannot create Either with null left value");
        return new Either<>(Optional.of(left), Optional.<T2>empty());
    }

    public static <T1, T2> Either<T1, T2> right(T2 right) {
        dbc.precondition(right != null, "cannot create Either with null right value");
        return new Either<>(Optional.<T1>empty(), Optional.of(right));
    }
}
