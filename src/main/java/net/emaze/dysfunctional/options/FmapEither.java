package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Performs fmap on an Either<LT, RT>
 * @author rferranti
 */
public class FmapEither<LR, RR, LT, RT> implements Delegate<Either<LR, RR>, Either<LT, RT>> {

    private final Delegate<LR, LT> left;
    private final Delegate<RR, RT> right;

    public FmapEither(Delegate<LR, LT> left, Delegate<RR, RT> right) {
        dbc.precondition(left != null, "cannot create FmapEither with a null left delegate");
        dbc.precondition(right != null, "cannot create FmapEither with a null right delegate");
        this.left = left;
        this.right = right;
    }

    @Override
    public Either<LR, RR> perform(Either<LT, RT> from) {
        dbc.precondition(from != null, "cannot fmap a null either");
        return from.fmap(left, right);
    }
}
