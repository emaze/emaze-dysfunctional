package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.delegates.Multicasting;

/**
 *
 * @param <E> 
 * @author rferranti
 */
public interface CompositePredicate<E> extends Predicate<E>, Multicasting<Predicate<E>>  {

}
