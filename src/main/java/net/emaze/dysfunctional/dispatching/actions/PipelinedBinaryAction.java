package net.emaze.dysfunctional.dispatching.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.multicasting.Multicasting;

/**
 * A composite binary functor with no return value. On call every composed action
 * is called in registration order (where an order exists)
 * @param <E1> the former type parameter
 * @param <E2> the latter type parameter
 * @author dangelocola
 */
public class PipelinedBinaryAction<E1, E2> implements BinaryAction<E1, E2>, Multicasting<BinaryAction<E1, E2>> {

    private final List<BinaryAction<E1, E2>> actions = new ArrayList<BinaryAction<E1, E2>>();

    /**
     * Performs every composed action.
     * @param former the former value
     * @param latter the latter value
     */
    @Override
    public void perform(E1 former, E2 latter) {
        for (BinaryAction<E1, E2> action : actions) {
            action.perform(former, latter);
        }
    }

    @Override
    public void add(BinaryAction<E1, E2> anAction) {
        dbc.precondition(anAction != null, "trying to add a null action");
        actions.add(anAction);
    }

    @Override
    public boolean remove(BinaryAction<E1, E2> anAction) {
        dbc.precondition(anAction != null, "trying to remove a null action");
        return actions.remove(anAction);
    }

    @Override
    public void setFunctors(Collection<BinaryAction<E1, E2>> functors) {
        dbc.precondition(functors != null, "cannot set a null functor collection");
        this.actions.clear();
        this.actions.addAll(functors);
    }
}
