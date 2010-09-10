package net.emaze.dysfunctional.delegates;

import java.util.Collection;

/**
 *
 * @author rferranti
 */
public interface Multicasting<F> {
    public void add(F functor);
    public void remove(F functor);
    public void setFunctors(Collection<F> functors);
}
