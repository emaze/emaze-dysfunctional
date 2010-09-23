package net.emaze.dysfunctional.delegates;

/**
 * \mathds{1}_{T}
 * @author rferranti
 */
public class Identity<T> implements Delegate<T,T>{

    @Override
    public T perform(T element) {
        return element;
    }

}
