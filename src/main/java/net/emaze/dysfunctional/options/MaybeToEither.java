package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Transforms a maybe mapping Optional.nothing to
 * Either.left(leftTypeProvider()) and Optional.just to Either.right.
 *
 * @author rferranti
 * @param <L> the either left type parameter
 * @param <R> the either right type parameter
 */
public class MaybeToEither<L, R> implements Function<Optional<R>, Either<L, R>> {

    private final Supplier<L> left;

    public MaybeToEither(Supplier<L> left) {
        dbc.precondition(left != null, "cannot create MaybeToEither with a null left supplier");
        this.left = left;
    }

    @Override
    public Either<L, R> apply(Optional<R> right) {
        dbc.precondition(right != null, "cannot transform a null maybe to an either");
        if (right.isPresent()) {
            return Either.right(right.get());
        }
        return Either.left(left.get());
    }
}
