package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.multicasting.Multicasting;

/**
 *
 * @param <E1>
 * @param <E2> 
 * @author rferranti
 */
public interface CompositeBinaryPredicate<E1, E2> extends BinaryPredicate<E1, E2>, Multicasting<BinaryPredicate<E1, E2>>  {

}
