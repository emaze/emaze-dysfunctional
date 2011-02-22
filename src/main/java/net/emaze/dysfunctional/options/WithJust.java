package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class WithJust<R, T> implements Delegate<Maybe<R>, Maybe<T>> {

    public final Delegate<R, T> delegate;

    public WithJust(Delegate<R, T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Maybe<R> perform(Maybe<T> from) {
        return from.withValue(delegate);
    }
}
