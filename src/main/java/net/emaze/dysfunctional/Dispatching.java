package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.adapting.*;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

/**
 * curry, mcurry, rcurry, ignore, ignore1st, ignore2nd, ignore3rd, supplier,
 * function, runnable, consumer, proposition, predicate.
 *
 * @author rferranti
 */
public abstract class Dispatching {

    /**
     * Partial application of the first parameter to an consumer.
     *
     * @param <T> the consumer parameter type
     * @param consumer the consumer to be curried
     * @param value the value to be curried
     * @return the curried runnable
     */
    public static <T> Runnable curry(Consumer<T> consumer, T value) {
        dbc.precondition(consumer != null, "cannot bind parameter of a null consumer");
        return () -> consumer.accept(value);
    }

    /**
     * Partial application of the first parameter to a binary consumer.
     *
     * @param <T1> the consumer former parameter type
     * @param <T2> the consumer latter parameter type
     * @param consumer the binary consumer to be curried
     * @param first the value to be curried as first parameter
     * @return the curried unary consumer
     */
    public static <T1, T2> Consumer<T2> curry(BiConsumer<T1, T2> consumer, T1 first) {
        dbc.precondition(consumer != null, "cannot bind parameter of a null consumer");
        return second -> consumer.accept(first, second);
    }

    /**
     * Partial application of the first parameter to a ternary consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the ternary consumer to be curried as first parameter
     * @param first the value to be curried
     * @return the curried binary consumer
     */
    public static <T1, T2, T3> BiConsumer<T2, T3> curry(TriConsumer<T1, T2, T3> consumer, T1 first) {
        dbc.precondition(consumer != null, "cannot bind parameter of a null consumer");
        return (second, third) -> consumer.accept(first, second, third);
    }

    /**
     * Partial application of the parameter to a predicate.
     *
     * @param <T> the predicate parameter type
     * @param predicate the predicate to be curried
     * @param value the value to be curried
     * @return the curried proposition
     */
    public static <T> BooleanSupplier curry(Predicate<T> predicate, T value) {
        dbc.precondition(predicate != null, "cannot bind parameter of a null predicate");
        return () -> predicate.test(value);
    }

    /**
     * Partial application of the first parameter to a binary predicate.
     *
     * @param <T1> the predicate former parameter type
     * @param <T2> the predicate latter parameter type
     * @param predicate the predicate to be curried
     * @param first the value to be curried as first parameter
     * @return the curried predicate
     */
    public static <T1, T2> Predicate<T2> curry(BiPredicate<T1, T2> predicate, T1 first) {
        dbc.precondition(predicate != null, "cannot bind parameter of a null predicate");
        return second -> predicate.test(first, second);
    }

    /**
     * Partial application of the first parameter to a ternary predicate.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate to be curried
     * @param first the value to be curried as first parameter
     * @return the curried binary predicate
     */
    public static <T1, T2, T3> BiPredicate<T2, T3> curry(TriPredicate<T1, T2, T3> predicate, T1 first) {
        dbc.precondition(predicate != null, "cannot bind parameter of a null predicate");
        return (second, third) -> predicate.test(first, second, third);
    }

    /**
     * Partial application of the parameter to a function.
     *
     * @param <T> the function parameter type
     * @param <R> the function return type
     * @param function the function to be curried
     * @param value the value to be curried
     * @return the curried supplier
     */
    public static <T, R> Supplier<R> curry(Function<T, R> function, T value) {
        dbc.precondition(function != null, "cannot bind parameter of a null function");
        return () -> function.apply(value);
    }

    /**
     * Partial application of the first parameter to a binary function.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function return type
     * @param function the function to be curried
     * @param first the value to be curried as first parameter
     * @return the curried function
     */
    public static <T1, T2, R> Function<T2, R> curry(BiFunction<T1, T2, R> function, T1 first) {
        dbc.precondition(function != null, "cannot bind parameter of a null function");
        return second -> function.apply(first, second);
    }

    /**
     * Partial application of the first parameter to a ternary function.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param <R> the function return type
     * @param function the function to be curried
     * @param first the value to be curried as first parameter
     * @return the curried binary function
     */
    public static <T1, T2, T3, R> BiFunction<T2, T3, R> curry(TriFunction<T1, T2, T3, R> function, T1 first) {
        dbc.precondition(function != null, "cannot bind parameter of a null function");
        return (second, third) -> function.apply(first, second, third);
    }

    /**
     * Partial application of the second (middle) parameter to a ternary
     * consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer to be curried
     * @param second the value to be curried as second parameter
     * @return the curried binary consumer
     */
    public static <T1, T2, T3> BiConsumer<T1, T3> mcurry(TriConsumer<T1, T2, T3> consumer, T2 second) {
        dbc.precondition(consumer != null, "cannot bind parameter of a null consumer");
        return (first, third) -> consumer.accept(first, second, third);
    }

    /**
     * Partial application of the second (middle) parameter to a ternary
     * function.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param <R> the function return type
     * @param function the function to be curried
     * @param second the value to be curried as second parameter
     * @return the curried binary function
     */
    public static <T1, T2, T3, R> BiFunction<T1, T3, R> mcurry(TriFunction<T1, T2, T3, R> function, T2 second) {
        dbc.precondition(function != null, "cannot bind parameter of a null function");
        return (first, third) -> function.apply(first, second, third);
    }

    /**
     * Partial application of the second (middle) parameter to a ternary
     * predicate.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate to be curried
     * @param second the value to be curried as second parameter
     * @return the curried binary predicate
     */
    public static <T1, T2, T3> BiPredicate<T1, T3> mcurry(TriPredicate<T1, T2, T3> predicate, T2 second) {
        dbc.precondition(predicate != null, "cannot bind parameter of a null predicate");
        return (first, third) -> predicate.test(first, second, third);
    }

    /**
     * Partial application of the last (rightmost) parameter to a binary
     * consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param consumer the consumer to be curried
     * @param second the value to be curried as second parameter
     * @return the curried consumer
     */
    public static <T1, T2> Consumer<T1> rcurry(BiConsumer<T1, T2> consumer, T2 second) {
        dbc.precondition(consumer != null, "cannot bind parameter of a null consumer");
        return first -> consumer.accept(first, second);
    }

    /**
     * Partial application of the last (rightmost) parameter to a ternary
     * consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer to be curried
     * @param third the value to be curried as third parameter
     * @return the curried binary consumer
     */
    public static <T1, T2, T3> BiConsumer<T1, T2> rcurry(TriConsumer<T1, T2, T3> consumer, T3 third) {
        dbc.precondition(consumer != null, "cannot bind parameter of a null consumer");
        return (first, second) -> consumer.accept(first, second, third);
    }

    /**
     * Partial application of the last (rightmost) parameter to a binary
     * predicate.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param predicate the predicate to be curried
     * @param second the value to be curried as second parameter
     * @return the curried predicate
     *
     */
    public static <T1, T2> Predicate<T1> rcurry(BiPredicate<T1, T2> predicate, T2 second) {
        dbc.precondition(predicate != null, "cannot bind parameter of a null predicate");
        return first -> predicate.test(first, second);
    }

    /**
     * Partial application of the last (rightmost) parameter to a ternary
     * predicate.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate to be curried
     * @param third the value to be curried as third parameter
     * @return the curried binary predicate
     */
    public static <T1, T2, T3> BiPredicate<T1, T2> rcurry(TriPredicate<T1, T2, T3> predicate, T3 third) {
        dbc.precondition(predicate != null, "cannot bind parameter of a null predicate");
        return (first, second) -> predicate.test(first, second, third);
    }

    /**
     * Partial application of the last (rightmost) parameter to a binary
     * function.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function return type
     * @param function the function to be curried
     * @param second the value to be curried as second parameter
     * @return the curried function
     */
    public static <T1, T2, R> Function<T1, R> rcurry(BiFunction<T1, T2, R> function, T2 second) {
        dbc.precondition(function != null, "cannot bind parameter of a null function");
        return first -> function.apply(first, second);
    }

    /**
     * Partial application of the last (rightmost) parameter to a ternary
     * function.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param <R> the function return type
     * @param function the function to be curried
     * @param third the value to be curried as third parameter
     * @return the curried binary function
     */
    public static <T1, T2, T3, R> BiFunction<T1, T2, R> rcurry(TriFunction<T1, T2, T3, R> function, T3 third) {
        dbc.precondition(function != null, "cannot bind parameter of a null function");
        return (first, second) -> function.apply(first, second, third);
    }

    /**
     * Adapts a proposition to a predicate by ignoring the passed parameter.
     *
     * @param <T> the predicate parameter type
     * @param proposition the proposition to be adapted
     * @param ignored the adapted predicate parameter type class
     * @return the adapted predicate
     */
    public static <T> Predicate<T> ignore(BooleanSupplier proposition, Class<T> ignored) {
        dbc.precondition(proposition != null, "cannot ignore parameter of a null proposition");
        return t -> proposition.getAsBoolean();
    }

    /**
     * Adapts a predicate to a binary predicate by ignoring first parameter.
     *
     * @param <T1> the adapted predicate first parameter type
     * @param <T2> the adapted predicate second parameter type
     * @param predicate the predicate to be adapted
     * @param ignored the adapted predicate ignored parameter type class
     * @return the adapted binary predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> ignore1st(Predicate<T2> predicate, Class<T1> ignored) {
        dbc.precondition(predicate != null, "cannot ignore parameter of a null predicate");
        return (first, second) -> predicate.test(second);
    }

    /**
     * Adapts a binary predicate to a ternary predicate by ignoring first
     * parameter.
     *
     * @param <T1> the adapted predicate first parameter type
     * @param <T2> the adapted predicate second parameter type
     * @param <T3> the adapted predicate third parameter type
     * @param predicate the predicate to be adapted
     * @param ignored the adapted predicate ignored parameter type class
     * @return the adapted ternary predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> ignore1st(BiPredicate<T2, T3> predicate, Class<T1> ignored) {
        dbc.precondition(predicate != null, "cannot ignore parameter of a null predicate");
        return (first, second, third) -> predicate.test(second, third);
    }

    /**
     * Adapts a predicate to a binary predicate by ignoring second parameter.
     *
     * @param <T1> the adapted predicate first parameter type
     * @param <T2> the adapted predicate second parameter type
     * @param predicate the predicate to be adapted
     * @param ignored the adapted predicate ignored parameter type class
     * @return the adapted binary predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> ignore2nd(Predicate<T1> predicate, Class<T2> ignored) {
        dbc.precondition(predicate != null, "cannot ignore parameter of a null predicate");
        return (first, second) -> predicate.test(first);
    }

    /**
     * Adapts a binary predicate to a ternary predicate by ignoring second
     * parameter.
     *
     * @param <T1> the adapted predicate first parameter type
     * @param <T2> the adapted predicate second parameter type
     * @param <T3> the adapted predicate third parameter type
     * @param predicate the predicate to be adapted
     * @param ignored the adapted predicate ignored parameter type class
     * @return the adapted ternary predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> ignore2nd(BiPredicate<T1, T3> predicate, Class<T2> ignored) {
        dbc.precondition(predicate != null, "cannot ignore parameter of a null predicate");
        return (first, second, third) -> predicate.test(first, third);
    }

    /**
     * Adapts a binary predicate to a ternary predicate by ignoring third
     * parameter.
     *
     * @param <T1> the adapted predicate first parameter type
     * @param <T2> the adapted predicate second parameter type
     * @param <T3> the adapted predicate third parameter type
     * @param predicate the predicate to be adapted
     * @param ignored the adapted predicate ignored parameter type class
     * @return the adapted ternary predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> ignore3rd(BiPredicate<T1, T2> predicate, Class<T3> ignored) {
        dbc.precondition(predicate != null, "cannot ignore parameter of a null predicate");
        return (first, second, third) -> predicate.test(first, second);
    }

    /**
     * Adapts a runnable to an consumer by ignoring the parameter.
     *
     * @param <T> the adapted consumer parameter type
     * @param runnable the runnable to be adapted
     * @param ignored the adapted consumer ignored parameter type class
     * @return the adapted consumer
     */
    public static <T> Consumer<T> ignore(Runnable runnable, Class<T> ignored) {
        dbc.precondition(runnable != null, "cannot ignore parameter of a null runnable");
        return first -> runnable.run();
    }

    /**
     * Adapts an consumer to a binary consumer by ignoring the first parameter.
     *
     * @param <T1> the adapted consumer first parameter type
     * @param <T2> the adapted consumer second parameter type
     * @param consumer the consumer to be adapted
     * @param ignored the adapted consumer ignored parameter type class
     * @return the adapted binary consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> ignore1st(Consumer<T2> consumer, Class<T1> ignored) {
        dbc.precondition(consumer != null, "cannot ignore parameter of a null consumer");
        return (first, second) -> consumer.accept(second);
    }

    /**
     * Adapts a binary consumer to a ternary consumer by ignoring the first
     * parameter.
     *
     * @param <T1> the adapted consumer first parameter type
     * @param <T2> the adapted consumer second parameter type
     * @param <T3> the adapted consumer third parameter type
     * @param consumer the consumer to be adapted
     * @param ignored the adapted consumer ignored parameter type class
     * @return the adapted ternary consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> ignore1st(BiConsumer<T2, T3> consumer, Class<T1> ignored) {
        dbc.precondition(consumer != null, "cannot ignore parameter of a null consumer");
        return (first, second, third) -> consumer.accept(second, third);
    }

    /**
     * Adapts an consumer to a binary consumer by ignoring the second parameter.
     *
     * @param <T1> the adapted consumer first parameter type
     * @param <T2> the adapted consumer second parameter type
     * @param consumer the consumer to be adapted
     * @param ignored the adapted consumer ignored parameter type class
     * @return the adapted binary consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> ignore2nd(Consumer<T1> consumer, Class<T2> ignored) {
        dbc.precondition(consumer != null, "cannot ignore parameter of a null consumer");
        return (first, second) -> consumer.accept(first);
    }

    /**
     * Adapts a binary consumer to a ternary consumer by ignoring the second
     * parameter.
     *
     * @param <T1> the adapted consumer first parameter type
     * @param <T2> the adapted consumer second parameter type
     * @param <T3> the adapted consumer third parameter type
     * @param consumer the consumer to be adapted
     * @param ignored the adapted consumer ignored parameter type class
     * @return the adapted ternary consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> ignore2nd(BiConsumer<T1, T3> consumer, Class<T2> ignored) {
        dbc.precondition(consumer != null, "cannot ignore parameter of a null consumer");
        return (first, second, third) -> consumer.accept(first, third);
    }

    /**
     * Adapts a binary consumer to a ternary consumer by ignoring the third
     * parameter.
     *
     * @param <T1> the adapted consumer first parameter type
     * @param <T2> the adapted consumer second parameter type
     * @param <T3> the adapted consumer third parameter type
     * @param consumer the consumer to be adapted
     * @param ignored the adapted consumer ignored parameter type class
     * @return the adapted ternary consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> ignore3rd(BiConsumer<T1, T2> consumer, Class<T3> ignored) {
        dbc.precondition(consumer != null, "cannot ignore parameter of a null consumer");
        return (first, second, third) -> consumer.accept(first, second);
    }

    /**
     * Adapts a supplier to a function by ignoring the passed parameter.
     *
     * @param <T> the adapted function parameter type
     * @param <R> the adapted function result type
     * @param supplier the supplier to be adapted
     * @param ignored the adapted function ignored parameter type class
     * @return the adapted function
     */
    public static <T, R> Function<T, R> ignore(Supplier<R> supplier, Class<T> ignored) {
        dbc.precondition(supplier != null, "cannot ignore parameter of a null supplier");
        return first -> supplier.get();
    }

    /**
     * Adapts a function to a binary function by ignoring the first parameter.
     *
     * @param <T1> the adapted function first parameter type
     * @param <T2> the adapted function second parameter type
     * @param <R> the adapted function result type
     * @param function the function to be adapted
     * @param ignored the adapted function ignored parameter type class
     * @return the adapted function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> ignore1st(Function<T2, R> function, Class<T1> ignored) {
        dbc.precondition(function != null, "cannot ignore parameter of a null function");
        return (first, second) -> function.apply(second);
    }

    /**
     * Adapts a binary function to a ternary function by ignoring the first
     * parameter.
     *
     * @param <T1> the adapted function first parameter type
     * @param <T2> the adapted function second parameter type
     * @param <T3> the adapted function third parameter type
     * @param <R> the adapted function result type
     * @param function the function to be adapted
     * @param ignored the adapted function ignored parameter type class
     * @return the adapted function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> ignore1st(BiFunction<T2, T3, R> function, Class<T1> ignored) {
        dbc.precondition(function != null, "cannot ignore parameter of a null function");
        return (first, second, third) -> function.apply(second, third);
    }

    /**
     * Adapts a function to a binary function by ignoring the second parameter.
     *
     * @param <T1> the adapted function first parameter type
     * @param <T2> the adapted function second parameter type
     * @param <R> the adapted function result type
     * @param function the function to be adapted
     * @param ignored the adapted function ignored parameter type class
     * @return the adapted function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> ignore2nd(Function<T1, R> function, Class<T2> ignored) {
        dbc.precondition(function != null, "cannot ignore parameter of a null function");
        return (first, second) -> function.apply(first);
    }

    /**
     * Adapts a binary function to a ternary function by ignoring the second
     * parameter.
     *
     * @param <T1> the adapted function first parameter type
     * @param <T2> the adapted function second parameter type
     * @param <T3> the adapted function third parameter type
     * @param <R> the adapted function result type
     * @param function the function to be adapted
     * @param ignored the adapted function ignored parameter type class
     * @return the adapted function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> ignore2nd(BiFunction<T1, T3, R> function, Class<T2> ignored) {
        dbc.precondition(function != null, "cannot ignore parameter of a null function");
        return (first, second, third) -> function.apply(first, third);
    }

    /**
     * Adapts a binary function to a ternary function by ignoring the third
     * parameter.
     *
     * @param <T1> the adapted function first parameter type
     * @param <T2> the adapted function second parameter type
     * @param <T3> the adapted function third parameter type
     * @param <R> the adapted function result type
     * @param function the function to be adapted
     * @param ignored the adapted function ignored parameter type class
     * @return the adapted function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> ignore3rd(BiFunction<T1, T2, R> function, Class<T3> ignored) {
        dbc.precondition(function != null, "cannot ignore parameter of a null function");
        return (first, second, third) -> function.apply(first, second);
    }

    /**
     * Adapts an iterator to a supplier.
     *
     * @param adaptee the runnable to be adapted
     * @return the adapted supplier
     */
    public static <T> Supplier<Optional<T>> supplier(Iterator<T> adaptee) {
        return new IteratingSupplier<T>(adaptee);
    }

    /**
     * Adapts a runnable to a supplier.
     *
     * @param adaptee the runnable to be adapted
     * @return the adapted supplier
     */
    public static Supplier<Void> supplier(Runnable adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null runnable");
        return () -> {
            adaptee.run();
            return null;
        };
    }

    /**
     * Adapts a proposition to a supplier.
     *
     * @param adaptee the proposition to be adapted
     * @return the adapted supplier
     */
    public static Supplier<Boolean> supplier(BooleanSupplier adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null boolean supplier");
        return () -> adaptee.getAsBoolean();
    }

    /**
     * Adapts an consumer to a function.
     *
     * @param <T> the consumer parameter type
     * @param adaptee the consumer to be adapted
     * @return the adapted function
     */
    public static <T> Function<T, Void> function(Consumer<T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null consumer");
        return first -> {
            adaptee.accept(first);
            return null;
        };
    }

    /**
     * Adapts a binary consumer to a binary function.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param adaptee the consumer to be adapted
     * @return the adapted function
     */
    public static <T1, T2> BiFunction<T1, T2, Void> function(BiConsumer<T1, T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null consumer");
        return (first, second) -> {
            adaptee.accept(first, second);
            return null;
        };
    }

    /**
     * Adapts a ternary consumer to a ternary function.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param adaptee the consumer to be adapted
     * @return the adapted function
     */
    public static <T1, T2, T3> TriFunction<T1, T2, T3, Void> function(TriConsumer<T1, T2, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null consumer");
        return (first, second, third) -> {
            adaptee.accept(first, second, third);
            return null;
        };
    }

    /**
     * Adapts a predicate to a function.
     *
     * @param <T> the predicate parameter type
     * @param adaptee the predicate to be adapted
     * @return the adapted function
     */
    public static <T> Function<T, Boolean> function(Predicate<T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null predicate");
        return adaptee::test;
    }

    /**
     * Adapts a binary predicate to a binary function.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param adaptee the predicate to be adapted
     * @return the adapted function
     */
    public static <T1, T2> BiFunction<T1, T2, Boolean> function(BiPredicate<T1, T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null predicate");
        return adaptee::test;
    }

    /**
     * Adapts a ternary predicate to a ternary function.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param adaptee the predicate to be adapted
     * @return the adapted function
     */
    public static <T1, T2, T3> TriFunction<T1, T2, T3, Boolean> function(TriPredicate<T1, T2, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null predicate");
        return adaptee::test;
    }

    /**
     * Adapts a supplier to a runnable.
     *
     * @param <T> the supplier parameter type
     * @param supplier the supplier to be adapted
     * @return the adapted runnable
     */
    public static <T> Runnable runnable(Supplier<T> supplier) {
        dbc.precondition(supplier != null, "cannot adapt a null supplier");
        return supplier::get;
    }

    /**
     * Adapts a function to an consumer.
     *
     * @param <T> the function parameter type
     * @param <R> the function return type
     * @param function the function to be adapted
     * @return the adapted consumer
     */
    public static <T, R> Consumer<T> consumer(Function<T, R> function) {
        dbc.precondition(function != null, "cannot adapt a null function");
        return function::apply;
    }

    /**
     * Adapts a binary function to a binary consumer.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function return type
     * @param function the function to be adapted
     * @return the adapted consumer
     */
    public static <T1, T2, R> BiConsumer<T1, T2> consumer(BiFunction<T1, T2, R> function) {
        dbc.precondition(function != null, "cannot adapt a null function");
        return function::apply;
    }

    /**
     * Adapts a ternary function to a ternary consumer.
     *
     * @param <R> the function return type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param function the function to be adapted
     * @return the adapted consumer
     */
    public static <T1, T2, T3, R> TriConsumer<T1, T2, T3> consumer(TriFunction<T1, T2, T3, R> function) {
        dbc.precondition(function != null, "cannot adapt a null function");
        return function::apply;
    }

    /**
     * Adapts a supplier to a proposition.
     *
     * @param supplier the supplier to be adapted
     * @return the adapted proposition
     */
    public static BooleanSupplier proposition(Supplier<Boolean> supplier) {
        dbc.precondition(supplier != null, "cannot adapt a null supplier");
        return supplier::get;
    }

    /**
     * Adapts a function to a predicate.
     *
     * @param <T> the function parameter type
     * @param function the function to be adapted
     * @return the adapted predicate
     */
    public static <T> Predicate<T> predicate(Function<T, Boolean> function) {
        dbc.precondition(function != null, "cannot adapt a null function");
        return function::apply;
    }

    /**
     * Adapts a binary function to a binary predicate
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param function the function to be adapted
     * @return the adapted predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> predicate(BiFunction<T1, T2, Boolean> function) {
        dbc.precondition(function != null, "cannot adapt a null function");
        return function::apply;
    }

    /**
     * Adapts a ternary function to a ternary predicate.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param function the function to be adapted
     * @return the adapted predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> predicate(TriFunction<T1, T2, T3, Boolean> function) {
        dbc.precondition(function != null, "cannot adapt a null function");
        return function::apply;
    }
}
