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
        return new ConsumerBinder<T>(consumer, value);
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
        return new ConsumerBinderFirst<T1, T2>(consumer, first);
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
        return new ConsumerBinderFirstOfThree<T1, T2, T3>(consumer, first);
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
        return new PredicateBinder<T>(predicate, value);
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
        return new PredicateBinderFirst<T1, T2>(predicate, first);
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
        return new PredicateBinderFirstOfThree<T1, T2, T3>(predicate, first);
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
        return new Binder<>(function, value);
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
        return new BinderFirst<>(function, first);
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
        return new BinderFirstOfThree<>(function, first);
    }

    /**
     * Partial application of the second (middle) parameter to a ternary consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer to be curried
     * @param second the value to be curried as second parameter
     * @return the curried binary consumer
     */
    public static <T1, T2, T3> BiConsumer<T1, T3> mcurry(TriConsumer<T1, T2, T3> consumer, T2 second) {
        return new ConsumerBinderSecondOfThree<T1, T2, T3>(consumer, second);
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
        return new BinderSecondOfThree<>(function, second);
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
        return new PredicateBinderSecondOfThree<T1, T2, T3>(predicate, second);
    }

    /**
     * Partial application of the last (rightmost) parameter to a binary consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param consumer the consumer to be curried
     * @param second the value to be curried as second parameter
     * @return the curried consumer
     */
    public static <T1, T2> Consumer<T1> rcurry(BiConsumer<T1, T2> consumer, T2 second) {
        return new ConsumerBinderSecond<T1, T2>(consumer, second);
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
        return new ConsumerBinderThird<T1, T2, T3>(consumer, third);
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
        return new PredicateBinderSecond<T1, T2>(predicate, second);
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
        return new PredicateBinderThird<T1, T2, T3>(predicate, third);
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
        return new BinderSecond<>(function, second);
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
        return new BinderThird<>(function, third);
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
        return new PropositionIgnoreParameter<T>(proposition);
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
        return new PredicateIgnoreFirst<T1, T2>(predicate);
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
        return new PredicateIgnoreFirstOfThree<T1, T2, T3>(predicate);
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
        return new PredicateIgnoreSecond<T1, T2>(predicate);
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
        return new PredicateIgnoreSecondOfThree<T1, T2, T3>(predicate);
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
        return new PredicateIgnoreThird<T1, T2, T3>(predicate);
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
        return new RunnableIgnoreParameter<T>(runnable);
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
        return new ConsumerIgnoreFirst<T1, T2>(consumer);
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
        return new ConsumerIgnoreFirstOfThree<T1, T2, T3>(consumer);
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
        return new ConsumerIgnoreSecond<T1, T2>(consumer);
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
        return new ConsumerIgnoreSecondOfThree<T1, T2, T3>(consumer);
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
        return new ConsumerIgnoreThird<T1, T2, T3>(consumer);
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
        return new IgnoreParameter<>(supplier);
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
        return new IgnoreFirst<>(function);
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
        return new IgnoreFirstOfThree<>(function);
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
        return new IgnoreSecond<>(function);
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
        return new IgnoreSecondOfThree<>(function);
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
        return new IgnoreThird<T1, T2, T3, R>(function);
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
        return new RunnableToSupplier(adaptee);
    }

    /**
     * Adapts a proposition to a supplier.
     *
     * @param adaptee the proposition to be adapted
     * @return the adapted supplier
     */
    public static Supplier<Boolean> supplier(BooleanSupplier adaptee) {
        return new PropositionToSupplier(adaptee);
    }

    /**
     * Adapts an consumer to a function.
     *
     * @param <T> the consumer parameter type
     * @param adaptee the consumer to be adapted
     * @return the adapted function
     */
    public static <T> Function<T, Void> function(Consumer<T> adaptee) {
        return new ConsumerToFunction<T>(adaptee);
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
        return new BinaryConsumerToFunction<T1, T2>(adaptee);
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
        return new TernaryConsumerToFunction<>(adaptee);
    }

    /**
     * Adapts a predicate to a function.
     *
     * @param <T> the predicate parameter type
     * @param adaptee the predicate to be adapted
     * @return the adapted function
     */
    public static <T> Function<T, Boolean> function(Predicate<T> adaptee) {
        return new PredicateToFunction<T>(adaptee);
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
        return new BinaryPredicateToFunction<T1, T2>(adaptee);
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
        return new TernaryPredicateToFunction<>(adaptee);
    }

    /**
     * Adapts a supplier to a runnable.
     *
     * @param <T> the supplier parameter type
     * @param supplier the supplier to be adapted
     * @return the adapted runnable
     */
    public static <T> Runnable runnable(Supplier<T> supplier) {
        return new SupplierToRunnable(supplier);
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
        return new FunctionToConsumer<>(function);
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
        return new BinaryFunctionToConsumer<>(function);
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
        return new TernaryFunctionToConsumer<T1, T2, T3, R>(function);
    }

    /**
     * Adapts a supplier to a proposition.
     *
     * @param supplier the supplier to be adapted
     * @return the adapted proposition
     */
    public static BooleanSupplier proposition(Supplier<Boolean> supplier) {
        return new SupplierToProposition(supplier);
    }

    /**
     * Adapts a function to a predicate.
     *
     * @param <T> the function parameter type
     * @param function the function to be adapted
     * @return the adapted predicate
     */
    public static <T> Predicate<T> predicate(Function<T, Boolean> function) {
        return new FunctionToPredicate<T>(function);
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
        return new BinaryFunctionToPredicate<>(function);
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
        return new TernaryFunctionToPredicate<>(function);
    }
}
