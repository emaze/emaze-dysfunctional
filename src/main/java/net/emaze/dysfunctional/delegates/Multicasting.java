package net.emaze.dysfunctional.delegates;

import java.util.Collection;

/**
 * A multicasting functor contract
 * @author rferranti
 */
public interface Multicasting<F> {
    /**
     * add a functor to the multicasting functor chain (tail)
     * @param functor
     */
    public void add(F functor);
    /**
     * removes a functor from the multicasting functor chain
     * @param functor
     */
    public void remove(F functor);

    /**
     * sets every functor from a collections (shallow copy)
     * @param functors
     */
    public void setFunctors(Collection<F> functors);
}
