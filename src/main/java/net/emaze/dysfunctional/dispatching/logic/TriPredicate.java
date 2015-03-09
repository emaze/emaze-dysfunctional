package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A ternary functor returning a boolean.
 *
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element Type
 * @author rferranti
 */
@FunctionalInterface
public interface TriPredicate<T1, T2, T3> {

    boolean test(T1 first, T2 second, T3 third);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another. When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this predicate
     * @return the composed predicate
     * @throws IllegalArgumentException if other is null
     */
    default TriPredicate<T1, T2, T3> and(TriPredicate<T1, T2, T3> other) {
        dbc.precondition(other != null, "cannot compose a ternary predicate with a null predicate");
        return (fst, snd, trd) -> test(fst, snd, trd) && other.test(fst, snd, trd);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return the negated predicate
     */
    default TriPredicate<T1, T2, T3> negate() {
        return (fst, snd, trd) -> !test(fst, snd, trd);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another. When evaluating the composed predicate,
     * if this predicate is {@code true}, then the {@code other} predicate is
     * not evaluated.
     *
     * @param other a predicate that will be logically-ORed with this predicate
     * @return the composed predicate
     * @throws IllegalArgumentException if other is null
     */
    default TriPredicate<T1, T2, T3> or(TriPredicate<T1, T2, T3> other) {
        dbc.precondition(other != null, "cannot compose a ternary predicate with a null predicate");
        return (fst, snd, trd) -> test(fst, snd, trd) || other.test(fst, snd, trd);
    }
}
