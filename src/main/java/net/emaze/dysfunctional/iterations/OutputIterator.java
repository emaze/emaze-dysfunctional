package net.emaze.dysfunctional.iterations;

/**
 *
 * @author rferranti
 */
public interface OutputIterator<T> {
    
    /**
     *
     * @return
     */
    public boolean hasNext();
    
    /**
     *
     * @param element
     */
    public void next(T element);
}
