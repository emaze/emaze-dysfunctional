package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite ternary functor with no return value. On call every composed 
 * action is called.
 * @param <E1> the first type parameter
 * @param <E2> the second type parameter
 * @param <E3> the third type parameter
 * @author rferranti
 */
public class PipelinedTernaryAction<E1, E2, E3> implements TernaryAction<E1, E2, E3> {

    private final Iterable<TernaryAction<E1, E2, E3>> actions;

    public PipelinedTernaryAction(Iterable<TernaryAction<E1, E2, E3>> actions) {
        dbc.precondition(actions != null, "cannot create a PipelinedTernaryAction with a null iterable");        
        this.actions = actions;
    }

    /**
     * Performs every composed action.
     * @param first the first element
     * @param second the second element
     * @param third the third element
     */
    @Override
    public void perform(E1 first, E2 second, E3 third) {
        for (TernaryAction<E1, E2, E3> action : actions) {
            action.perform(first, second, third);
        }
    }
}
