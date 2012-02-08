package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;

/**
 * A composite unary functor with no return value. On call every composed action
 * is called.
 *
 * @param <E> the type parameter
 * @author rferranti
 */
public class PipelinedAction<E> implements Action<E> {

    private final Iterable<Action<E>> actions;

    public PipelinedAction(Iterable<Action<E>> actions) {
        dbc.precondition(actions != null, "cannot create a pipeline from a null iterable of actions");
        this.actions = actions;
    }

    /**
     * performs every composed action
     *
     * @param value
     */
    @Override
    public void perform(E value) {
        for (Action<E> action : actions) {
            action.perform(value);
        }
    }
}
