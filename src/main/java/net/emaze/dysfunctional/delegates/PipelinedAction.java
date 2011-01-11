package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite unary functor with no return value. On call every composed action
 * is called in registration order (where an order exists)
 * @param <E> the type parameter
 * @author rferranti
 */
public class PipelinedAction<E> implements Action<E>, Multicasting<Action<E>> {

    private final List<Action<E>> actions = new ArrayList<Action<E>>();

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

    @Override
    public void add(Action<E> anAction) {
        dbc.precondition(anAction != null, "trying to add a null action");
        actions.add(anAction);
    }

    @Override
    public boolean remove(Action<E> anAction) {
        dbc.precondition(anAction != null, "trying to remove a null action");
        return actions.remove(anAction);
    }

    @Override
    public void setFunctors(Collection<Action<E>> functors){
        dbc.precondition(functors != null, "cannot set a null functor collection");
        this.actions.clear();
        this.actions.addAll(functors);
    }
}
