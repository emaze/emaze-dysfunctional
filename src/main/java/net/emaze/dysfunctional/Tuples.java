package net.emaze.dysfunctional;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.tuples.BinaryToUnaryAction;
import net.emaze.dysfunctional.tuples.BinaryToUnaryDelegate;
import net.emaze.dysfunctional.tuples.BinaryToUnaryPredicate;
import net.emaze.dysfunctional.tuples.Pair;
import net.emaze.dysfunctional.tuples.TernaryToUnaryAction;
import net.emaze.dysfunctional.tuples.TernaryToUnaryDelegate;
import net.emaze.dysfunctional.tuples.TernaryToUnaryPredicate;
import net.emaze.dysfunctional.tuples.Triple;
import net.emaze.dysfunctional.tuples.UnaryToBinaryAction;
import net.emaze.dysfunctional.tuples.UnaryToBinaryDelegate;
import net.emaze.dysfunctional.tuples.UnaryToBinaryPredicate;
import net.emaze.dysfunctional.tuples.UnaryToTernaryAction;
import net.emaze.dysfunctional.tuples.UnaryToTernaryDelegate;
import net.emaze.dysfunctional.tuples.UnaryToTernaryPredicate;

/**
 * tupled.
 *
 * @author rferranti
 */
public abstract class Tuples {

    /**
     * Adapts a binary delegate to a delegate accepting a pair.
     *
     * @param <R> the delegate return type
     * @param <T> the delegate first parameter type
     * @param <U> the delegate second parameter type
     * @param delegate the delegate to be adapted
     * @return the adapted delegate
     */
    public static <R, T, U> Delegate<R, Pair<T, U>> tupled(BinaryDelegate<R, T, U> delegate) {
        return new BinaryToUnaryDelegate<R, T, U>(delegate);
    }

    /**
     * Adapts a binary predicate to a predicate accepting a pair.
     *
     * @param <T> the predicate first parameter type
     * @param <U> the predicate second parameter type
     * @param predicate the predicate to be adapted
     * @return the adapted predicate
     */
    public static <T, U> Predicate<Pair<T, U>> tupled(BinaryPredicate<T, U> predicate) {
        return new BinaryToUnaryPredicate<T, U>(predicate);
    }

    /**
     * Adapts a binary action to an action accepting a pair.
     *
     * @param <T> the action first parameter type
     * @param <U> the action second parameter type
     * @param action the action to be adapted
     * @return the adapted action
     */
    public static <T, U> Action<Pair<T, U>> tupled(BinaryAction<T, U> action) {
        return new BinaryToUnaryAction<T, U>(action);
    }

    /**
     * Adapts a ternary delegate to a delegate accepting a triple.
     *
     * @param <R> the delegate return type
     * @param <T> the delegate first parameter type
     * @param <U> the delegate second parameter type
     * @param <V> the delegate third parameter type
     * @param delegate the delegate to be adapted
     * @return the adapted delegate
     */
    public static <R, T, U, V> Delegate<R, Triple<T, U, V>> tupled(TernaryDelegate<R, T, U, V> delegate) {
        return new TernaryToUnaryDelegate<R, T, U, V>(delegate);
    }

    /**
     * Adapts a ternary predicate to a predicate accepting a triple.
     *
     * @param <T> the predicate first parameter type
     * @param <U> the predicate second parameter type
     * @param <V> the predicate third parameter type
     * @param predicate the predicate to be adapted
     * @return the adapted predicate
     */
    public static <T, U, V> Predicate<Triple<T, U, V>> tupled(TernaryPredicate<T, U, V> predicate) {
        return new TernaryToUnaryPredicate<T, U, V>(predicate);
    }

    /**
     * Adapts a ternary action to an action accepting a triple.
     *
     * @param <T> the action first parameter type
     * @param <U> the action second parameter type
     * @param <V> the action third parameter type
     * @param action the action to be adapted
     * @return the adatped action
     */
    public static <T, U, V> Action<Triple<T, U, V>> tupled(TernaryAction<T, U, V> action) {
        return new TernaryToUnaryAction<T, U, V>(action);
    }

    /**
     * untupled.
     */
    public abstract static class Pairs {

        /**
         * Adapts a delegate accepting a pair to a binary delegate.
         *
         * @param <R> the delegate return type
         * @param <T> the delegate first parameter type
         * @param <U> the delegate second parameter type
         * @param delegate the delegate to be adapted
         * @return the adapted delegate
         */
        public static <R, T, U> BinaryDelegate<R, T, U> untupled(Delegate<R, Pair<T, U>> delegate) {
            return new UnaryToBinaryDelegate<R, T, U>(delegate);
        }

        /**
         * Adapts a predicate accepting a pair to a binary predicate.
         *
         * @param <T> the predicate first parameter type
         * @param <U> the predicate second parameter type
         * @param predicate the predicate to be adapted
         * @return the adapted predicate
         */
        public static <T, U> BinaryPredicate<T, U> untupled(Predicate<Pair<T, U>> predicate) {
            return new UnaryToBinaryPredicate<T, U>(predicate);
        }

        /**
         * Adapts an action accepting a pair to a binary action.
         *
         * @param <T> the action first parameter type
         * @param <U> the action second parameter type
         * @param action the action to be adapted
         * @return the adapted action
         */
        public static <T, U> BinaryAction<T, U> untupled(Action<Pair<T, U>> action) {
            return new UnaryToBinaryAction<T, U>(action);
        }
    }

    /**
     * untupled.
     */
    public abstract static class Triples {

        /**
         * Adapts a delegate accepting a triple to a ternary delegate.
         *
         * @param <R> the delegate return type
         * @param <T> the delegate first parameter type
         * @param <U> the delegate second parameter type
         * @param <V> the delegate third parameter type
         * @param delegate the delegate to be adapted
         * @return the adapted delegate
         */
        public static <R, T, U, V> TernaryDelegate<R, T, U, V> untupled(Delegate<R, Triple<T, U, V>> delegate) {
            return new UnaryToTernaryDelegate<R, T, U, V>(delegate);
        }

        /**
         * Adapts a predicate accepting a triple to a ternary predicate.
         *
         * @param <T> the predicate first parameter type
         * @param <U> the predicate second parameter type
         * @param <V> the predicate third parameter type
         * @param predicate the predicate to be adapted
         * @return the adapted predicate
         */
        public static <T, U, V> TernaryPredicate<T, U, V> untupled(Predicate<Triple<T, U, V>> predicate) {
            return new UnaryToTernaryPredicate<T, U, V>(predicate);
        }

        /**
         * Adapts an action accepting a triple to a ternary action.
         *
         * @param <T> the action first parameter type
         * @param <U> the action second parameter type
         * @param <V> the action third parameter type
         * @param action the action to be adapted
         * @return the adatped action
         */
        public static <T, U, V> TernaryAction<T, U, V> untupled(Action<Triple<T, U, V>> action) {
            return new UnaryToTernaryAction<T, U, V>(action);
        }
    }
}
