package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * A composite binary functor with no return value. On call every composed
 * action is called.
 *
 * @param <E1> the former type parameter
 * @param <E2> the latter type parameter
 * @author dangelocola
 */
public class PipelinedBinaryAction<E1, E2> implements BinaryAction<E1, E2> {

    private final Iterable<BinaryAction<E1, E2>> actions;

    public PipelinedBinaryAction(Iterable<BinaryAction<E1, E2>> actions) {
        dbc.precondition(actions != null, "cannot create a pipeline from a null iterable of binary actions");
        this.actions = actions;
    }

    /**
     * Performs every composed action.
     *
     * @param former the former value
     * @param latter the latter value
     */
    @Override
    public void perform(E1 former, E2 latter) {
        for (BinaryAction<E1, E2> action : actions) {
            action.perform(former, latter);
        }
    }
}
