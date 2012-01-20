package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 * Searches the first element.
 * @param <E>
 * @author rferranti
 */
public class MaybeFirstElement<E> implements Delegate<Maybe<E>, Iterator<E>> {

    @Override
    public Maybe<E> perform(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        return new MaybeIterator<E>(consumable).next();
    }
}
