package net.emaze.dysfunctional.consumers;

/**
 *
 * @author rferranti
 */
public interface OutputIterator<T> {
    
    /**
     *
     * @return
     */
    boolean hasNext();
    
    /**
     *
     * @param element
     */
    void next(T element);
}
