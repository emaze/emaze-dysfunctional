package net.emaze.dysfunctional;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.tuples.*;

/**
 * tupled.
 *
 * @author rferranti
 */
public abstract class Tuples {

    /**
     * Adapts a binary delegate to a delegate accepting a pair.
     *
     * @param <T> the delegate first parameter type
     * @param <U> the delegate second parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be adapted
     * @return the adapted delegate
     */
    public static <T, U, R> Function<Pair<T, U>, R> tupled(BiFunction<T, U, R> delegate) {
        return new BinaryToUnaryDelegate<T, U, R>(delegate);
    }

    /**
     * Adapts a binary predicate to a predicate accepting a pair.
     *
     * @param <T> the predicate first parameter type
     * @param <U> the predicate second parameter type
     * @param predicate the predicate to be adapted
     * @return the adapted predicate
     */
    public static <T, U> Predicate<Pair<T, U>> tupled(BiPredicate<T, U> predicate) {
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
    public static <T, U> Consumer<Pair<T, U>> tupled(BiConsumer<T, U> action) {
        return new BinaryToUnaryAction<T, U>(action);
    }

    /**
     * Adapts a ternary delegate to a delegate accepting a triple.
     *
     * @param <T> the delegate first parameter type
     * @param <U> the delegate second parameter type
     * @param <V> the delegate third parameter type
     * @param <R> the delegate return type
     * @param function the delegate to be adapted
     * @return the adapted delegate
     */
    public static <T, U, V, R> Function<Triple<T, U, V>, R> tupled(TriFunction<T, U, V, R> function) {
        return new TernaryToUnaryDelegate<>(function);
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
    public static <T, U, V> Consumer<Triple<T, U, V>> tupled(TernaryAction<T, U, V> action) {
        return new TernaryToUnaryAction<T, U, V>(action);
    }

    /**
     * untupled.
     */
    public abstract static class Pairs {

        /**
         * Adapts a delegate accepting a pair to a binary delegate.
         *
         * @param <T> the delegate first parameter type
         * @param <U> the delegate second parameter type
         * @param <R> the delegate return type
         * @param delegate the delegate to be adapted
         * @return the adapted delegate
         */
        public static <T, U, R> BiFunction<T, U, R> untupled(Function<Pair<T, U>, R> delegate) {
            return new UnaryToBinaryDelegate<>(delegate);
        }

        /**
         * Adapts a predicate accepting a pair to a binary predicate.
         *
         * @param <T> the predicate first parameter type
         * @param <U> the predicate second parameter type
         * @param predicate the predicate to be adapted
         * @return the adapted predicate
         */
        public static <T, U> BiPredicate<T, U> untupled(Predicate<Pair<T, U>> predicate) {
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
        public static <T, U> BiConsumer<T, U> untupled(Consumer<Pair<T, U>> action) {
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
         * @param <T> the delegate first parameter type
         * @param <U> the delegate second parameter type
         * @param <V> the delegate third parameter type
         * @param <R> the delegate return type
         * @param function the delegate to be adapted
         * @return the adapted delegate
         */
        public static <T, U, V, R> TriFunction<T, U, V, R> untupled(Function<Triple<T, U, V>, R> function) {
            return new UnaryToTernaryDelegate<>(function);
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
        public static <T, U, V> TernaryAction<T, U, V> untupled(Consumer<Triple<T, U, V>> action) {
            return new UnaryToTernaryAction<T, U, V>(action);
        }
    }
}
