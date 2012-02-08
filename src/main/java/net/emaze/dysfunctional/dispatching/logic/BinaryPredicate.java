package net.emaze.dysfunctional.dispatching.logic;

/**
 * A binary functor returning a boolean.
 *
 * @param <T1> the former element Type
 * @param <T2> the latter element Type
 * @author rferranti
 */
public interface BinaryPredicate<T1, T2> {

    boolean accept(T1 former, T2 latter);
}
