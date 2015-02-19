package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;

/**
 * A composite unary functor with no return value. On call every composed action
 * is called.
 *
 * @param <E> the type parameter
 * @author rferranti
 */
public class PipelinedAction<E> implements Consumer<E> {

    private final Iterable<Consumer<E>> actions;

    public PipelinedAction(Iterable<Consumer<E>> actions) {
        dbc.precondition(actions != null, "cannot create a pipeline from a null iterable of actions");
        this.actions = actions;
    }

    /**
     * performs every composed action
     *
     * @param value
     */
    @Override
    public void accept(E value) {
        for (Consumer<E> action : actions) {
            action.accept(value);
        }
    }
}
