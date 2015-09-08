package net.emaze.dysfunctional.output;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public interface OutputIterator<T> {
    
    /**
     *
     * @return true if iterator has next, false if not
     */
    boolean hasNext();
    
    /**
     *
     * @param element
     */
    void next(T element);
}
