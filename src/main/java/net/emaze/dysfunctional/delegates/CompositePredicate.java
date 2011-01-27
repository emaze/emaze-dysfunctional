package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public interface CompositePredicate<E> extends Predicate<E>, Multicasting<Predicate<E>>  {

}
