package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A binary functor
 * @param <R> the result Type
 * @param <T1> the former element Type
 * @param <T2> the latter element Type
 * @author rferranti
 */
public interface BinaryDelegate<R,T1,T2> {
    /**
     * Executes the delegate for the given elements yielding a result of type R
     * @param former the former element
     * @param latter the latter element
     * @return
     */
    public R perform(T1 former,T2 latter);
}
