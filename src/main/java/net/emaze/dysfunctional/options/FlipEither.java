package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Flips an either.
 *
 * @author rferranti
 * @param <LT> the source either left type parameter
 * @param <RT> the source either right type parameter
 */
public class FlipEither<LT, RT> implements Function<Either<LT, RT>, Either<RT, LT>> {

    @Override
    public Either<RT, LT> apply(Either<LT, RT> either) {
        dbc.precondition(either != null, "cannot flip a null either");
        return either.flip();
    }
}
