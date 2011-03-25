package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.multicasting.Multicasting;

/**
 *
 * @param <E> 
 * @author rferranti
 */
public interface CompositePredicate<E> extends Predicate<E>, Multicasting<Predicate<E>>  {

}
