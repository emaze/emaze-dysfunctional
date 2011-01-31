package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.delegates.Multicasting;

/**
 *
 * @author rferranti
 */
public interface CompositePredicate<E> extends Predicate<E>, Multicasting<Predicate<E>>  {

}
