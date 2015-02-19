package net.emaze.dysfunctional.options;

import java.util.function.Function;

/**
 * Pointed.pure() implementation of the {@literal Either<LT,RT>} Bifunctor.
 *
 * @author rferranti
 * @param <LT> the right type
 * @param <RT> the left type
 */
public class PureEither<LT, RT> implements Function<RT, Either<LT, RT>> {

    @Override
    public Either<LT, RT> apply(RT value) {
        return Either.right(value);
    }
}
