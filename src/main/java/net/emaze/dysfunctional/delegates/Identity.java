package net.emaze.dysfunctional.delegates;

/**
 * \mathds{1}_{T}
 * @param <T> 
 * @author rferranti
 */
public class Identity<T> implements Delegate<T,T>{

    /**
     * yields the given element
     * @param element the given element
     * @return the given element
     */
    @Override
    public T perform(T element) {
        return element;
    }

}
