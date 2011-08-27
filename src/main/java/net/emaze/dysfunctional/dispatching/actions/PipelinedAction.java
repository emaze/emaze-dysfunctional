package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite unary functor with no return value. On call every composed action
 * is called.
 * @param <E> the type parameter
 * @author rferranti
 */
public class PipelinedAction<E> implements Action<E> {

    private final Iterable<Action<E>> actions;

    public PipelinedAction(Iterable<Action<E>> actions) {
        dbc.precondition(actions != null, "cannot create a PipelinedAction with a null iterable");
        this.actions = actions;
    }
    
    /**
     * performs every composed action
     * @param value
     */
    @Override
    public void perform(E value) {
        for (Action<E> action : actions) {
            action.perform(value);
        }
    }
}
