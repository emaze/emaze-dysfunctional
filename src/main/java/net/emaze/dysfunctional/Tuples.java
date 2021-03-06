package net.emaze.dysfunctional;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.tuples.*;

/**
 * tupled.
 *
 * @author rferranti
 */
public abstract class Tuples {

    /**
     * Adapts a binary function to a function accepting a pair.
     *
     * @param <T> the function first parameter type
     * @param <U> the function second parameter type
     * @param <R> the function return type
     * @param function the function to be adapted
     * @return the adapted function
     */
    public static <T, U, R> Function<Pair<T, U>, R> tupled(BiFunction<T, U, R> function) {
        dbc.precondition(function != null, "cannot apply a pair to a null function");
        return pair -> function.apply(pair.first(), pair.second());
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
        dbc.precondition(predicate != null, "cannot apply a pair to a null predicate");
        return pair -> predicate.test(pair.first(), pair.second());
    }

    /**
     * Adapts a binary consumer to an consumer accepting a pair.
     *
     * @param <T> the consumer first parameter type
     * @param <U> the consumer second parameter type
     * @param consumer the consumer to be adapted
     * @return the adapted consumer
     */
    public static <T, U> Consumer<Pair<T, U>> tupled(BiConsumer<T, U> consumer) {
        dbc.precondition(consumer != null, "cannot apply a pair to a null consumer");
        return pair -> consumer.accept(pair.first(), pair.second());
    }

    /**
     * Adapts a ternary function to a function accepting a triple.
     *
     * @param <T> the function first parameter type
     * @param <U> the function second parameter type
     * @param <V> the function third parameter type
     * @param <R> the function return type
     * @param function the function to be adapted
     * @return the adapted function
     */
    public static <T, U, V, R> Function<Triple<T, U, V>, R> tupled(TriFunction<T, U, V, R> function) {
        dbc.precondition(function != null, "cannot apply a triple to a null function");
        return triple -> function.apply(triple.first(), triple.second(), triple.third());
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
    public static <T, U, V> Predicate<Triple<T, U, V>> tupled(TriPredicate<T, U, V> predicate) {
        dbc.precondition(predicate != null, "cannot apply a triple to a null predicate");
        return triple -> predicate.test(triple.first(), triple.second(), triple.third());
    }

    /**
     * Adapts a ternary consumer to an consumer accepting a triple.
     *
     * @param <T> the consumer first parameter type
     * @param <U> the consumer second parameter type
     * @param <V> the consumer third parameter type
     * @param consumer the consumer to be adapted
     * @return the adatped consumer
     */
    public static <T, U, V> Consumer<Triple<T, U, V>> tupled(TriConsumer<T, U, V> consumer) {
        dbc.precondition(consumer != null, "cannot apply a triple to a null consumer");
        return triple -> consumer.accept(triple.first(), triple.second(), triple.third());
    }

    /**
     * untupled.
     */
    public abstract static class Pairs {

        /**
         * Adapts a function accepting a pair to a binary function.
         *
         * @param <T> the function first parameter type
         * @param <U> the function second parameter type
         * @param <R> the function return type
         * @param function the function to be adapted
         * @return the adapted function
         */
        public static <T, U, R> BiFunction<T, U, R> untupled(Function<Pair<T, U>, R> function) {
            dbc.precondition(function != null, "cannot untuple a null function");
            return (first, second) -> function.apply(Pair.of(first, second));
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
            dbc.precondition(predicate != null, "cannot untuple a null predicate");
            return (first, second) -> predicate.test(Pair.of(first, second));
        }

        /**
         * Adapts an consumer accepting a pair to a binary consumer.
         *
         * @param <T> the consumer first parameter type
         * @param <U> the consumer second parameter type
         * @param consumer the consumer to be adapted
         * @return the adapted consumer
         */
        public static <T, U> BiConsumer<T, U> untupled(Consumer<Pair<T, U>> consumer) {
            dbc.precondition(consumer != null, "cannot untuple a null consumer");
            return (first, second) -> consumer.accept(Pair.of(first, second));
        }
    }

    /**
     * untupled.
     */
    public abstract static class Triples {

        /**
         * Adapts a function accepting a triple to a ternary function.
         *
         * @param <T> the function first parameter type
         * @param <U> the function second parameter type
         * @param <V> the function third parameter type
         * @param <R> the function return type
         * @param function the function to be adapted
         * @return the adapted function
         */
        public static <T, U, V, R> TriFunction<T, U, V, R> untupled(Function<Triple<T, U, V>, R> function) {
            dbc.precondition(function != null, "cannot untuple a null function");
            return (first, second, third) -> function.apply(Triple.of(first, second, third));
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
        public static <T, U, V> TriPredicate<T, U, V> untupled(Predicate<Triple<T, U, V>> predicate) {
            dbc.precondition(predicate != null, "cannot untuple a null predicate");
            return (first, second, third) -> predicate.test(Triple.of(first, second, third));
        }

        /**
         * Adapts an consumer accepting a triple to a ternary consumer.
         *
         * @param <T> the consumer first parameter type
         * @param <U> the consumer second parameter type
         * @param <V> the consumer third parameter type
         * @param consumer the consumer to be adapted
         * @return the adatped consumer
         */
        public static <T, U, V> TriConsumer<T, U, V> untupled(Consumer<Triple<T, U, V>> consumer) {
            dbc.precondition(consumer != null, "cannot untuple a null consumer");
            return (first, second, third) -> consumer.accept(Triple.of(first, second, third));
        }
    }
}
