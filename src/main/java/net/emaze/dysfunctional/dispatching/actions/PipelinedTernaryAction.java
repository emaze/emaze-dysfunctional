package net.emaze.dysfunctional.dispatching.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.multicasting.Multicasting;

/**
 * A composite ternary functor with no return value. On call every composed 
 * action is called in registration order (where an order exists).
 * @param <E1> the first type parameter
 * @param <E2> the second type parameter
 * @param <E3> the third type parameter
 * @author rferranti
 */
public class PipelinedTernaryAction<E1, E2, E3> implements TernaryAction<E1, E2, E3>, Multicasting<TernaryAction<E1, E2, E3>> {

    private final List<TernaryAction<E1, E2, E3>> actions = new ArrayList<TernaryAction<E1, E2, E3>>();

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

    @Override
    public void add(TernaryAction<E1, E2, E3> anAction) {
        dbc.precondition(anAction != null, "trying to add a null action");
        actions.add(anAction);
    }

    @Override
    public boolean remove(TernaryAction<E1, E2, E3> anAction) {
        dbc.precondition(anAction != null, "trying to remove a null action");
        return actions.remove(anAction);
    }

    @Override
    public void setFunctors(Collection<TernaryAction<E1, E2, E3>> functors) {
        dbc.precondition(functors != null, "cannot set a null functor collection");
        this.actions.clear();
        this.actions.addAll(functors);
    }
}
