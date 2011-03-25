package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.multicasting.Multicasting;

/**
 *
 * @param <E1>
 * @param <E2>
 * @param <E3> 
 * @author rferranti
 */
public interface CompositeTernaryPredicate<E1, E2, E3> extends TernaryPredicate<E1, E2, E3>, Multicasting<TernaryPredicate<E1, E2, E3>>  {

}
