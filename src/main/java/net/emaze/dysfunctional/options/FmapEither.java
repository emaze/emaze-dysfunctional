package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Performs fmap on an {@literal Either<LT, RT>}
 *
 * @param <RT> the right source type parameter
 * @param <LT> the left source type parameter
 * @param <LR> the left result type parameter
 * @param <RR> the right result type parameter
 * @author rferranti
 */
public class FmapEither<LT, RT, LR, RR> implements Function<Either<LT, RT>, Either<LR, RR>> {

    private final Function<LT, LR> left;
    private final Function<RT, RR> right;

    public FmapEither(Function<LT, LR> left, Function<RT, RR> right) {
        dbc.precondition(left != null, "cannot create FmapEither with a null left delegate");
        dbc.precondition(right != null, "cannot create FmapEither with a null right delegate");
        this.left = left;
        this.right = right;
    }

    @Override
    public Either<LR, RR> apply(Either<LT, RT> from) {
        dbc.precondition(from != null, "cannot fmap a null either");
        return from.fmap(left, right);
    }
}
