package net.emaze.dysfunctional.delegates;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class ActionToDelegate<T> implements Delegate<Void, T> {

    private final Action<T> adapted;

    public ActionToDelegate(Action<T> adapted) {
        this.adapted = adapted;
    }

    @Override
    public Void perform(T value) {
        adapted.perform(value);
        return null;
    }
}
