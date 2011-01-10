package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite binary functor with no return value. On call every composed action
 * is called in registration order (where an order exists)

 * @author dangelocola
 */
public class PipelinedBinaryAction<E1, E2> implements BinaryAction<E1, E2>, Multicasting<BinaryAction<E1, E2>> {

    private final List<BinaryAction<E1, E2>> actions = new ArrayList<BinaryAction<E1, E2>>();

    /**
     * Performs every composed action.
     */
    @Override
    public void perform(E1 value1, E2 value2) {
        for (BinaryAction<E1, E2> action : actions) {
            action.perform(value1, value2);
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
