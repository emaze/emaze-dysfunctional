package net.emaze.dysfunctional.output;

/**
 *
 * @param <T> 
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
