package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rferranti
 */
public class PipelinedAction<E> implements Action<E>, Multicasting<Action<E>> {

    private final List<Action<E>> actions = new ArrayList<Action<E>>();

    public void perform(E value) {
        for (Action action : actions) {
            action.perform(value);
        }
    }

    public void add(Action<E> anAction) {
        actions.add(anAction);
    }

    public void remove(Action<E> anAction) {
        actions.remove(anAction);
    }
}
