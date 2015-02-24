package net.emaze.dysfunctional;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.dispatching.spying.*;
import net.emaze.dysfunctional.options.Box;

/**
 * spy, spy1st, spy2nd, spy3rd, spyRes, monitor.
 *
 * @author rferranti
 */
public abstract class Spies {

    /**
     * Proxies a proposition, spying for result.
     *
     * @param proposition the proposition to be spied
     * @param result a box that will be containing spied result
     * @return the proxied proposition
     */
    public static BooleanSupplier spy(BooleanSupplier proposition, Box<Boolean> result) {
        return new CapturingProposition(proposition, result);
    }

    /**
     * Proxies a supplier, spying for result.
     *
     * @param <R> the supplier result type
     * @param supplier the supplier to be spied
     * @param result a box that will be containing spied result
     * @return the proxied supplier
     */
    public static <R> Supplier<R> spy(Supplier<R> supplier, Box<R> result) {
        return new CapturingSupplier<R>(supplier, result);
    }

    /**
     * Proxies a function spying for result and parameter.
     *
     * @param <T> the function parameter type
     * @param <R> the function result type
     * @param function the function to be spied
     * @param result a box that will be containing spied result
     * @param param a box that will be containing spied param
     * @return the proxied function
     */
    public static <T, R> Function<T, R> spy(Function<T, R> function, Box<R> result, Box<T> param) {
        return new CapturingFunction<>(function, result, param);
    }

    /**
     * Proxies a binary function spying for result and parameters.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function result type
     * @param function the function to be spied
     * @param result a box that will be containing spied result
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> spy(BiFunction<T1, T2, R> function, Box<R> result, Box<T1> param1, Box<T2> param2) {
        return new BinaryCapturingFunction<>(function, result, param1, param2);
    }

    /**
     * Proxies a ternary function spying for result and parameters.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param function the function to be spied
     * @param result a box that will be containing spied result
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> spy(TriFunction<T1, T2, T3, R> function, Box<R> result, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingFunction<T1, T2, T3, R>(function, result, param1, param2, param3);
    }

    /**
     * Proxies a predicate spying for result and parameter.
     *
     * @param <T> the predicate parameter type
     * @param predicate the predicateto be spied
     * @param result a box that will be containing spied result
     * @param param a box that will be containing the spied parameter
     * @return the proxied predicate
     */
    public static <T> Predicate<T> spy(Predicate<T> predicate, Box<Boolean> result, Box<T> param) {
        return new CapturingPredicate<T>(predicate, result, param);
    }

    /**
     * Proxies a binary predicate spying for result and parameters.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param predicate the predicate to be spied
     * @param result a box that will be containing spied result
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> spy(BiPredicate<T1, T2> predicate, Box<Boolean> result, Box<T1> param1, Box<T2> param2) {
        return new BinaryCapturingPredicate<T1, T2>(predicate, result, param1, param2);
    }

    /**
     * Proxies a ternary predicate spying for result and parameters.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate to be spied
     * @param result a box that will be containing spied result
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> spy(TriPredicate<T1, T2, T3> predicate, Box<Boolean> result, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingPredicate<T1, T2, T3>(predicate, result, param1, param2, param3);
    }

    /**
     * Proxies an consumer spying for parameter.
     *
     * @param <T> the consumer parameter type
     * @param consumer the consumer to be spied
     * @param param a box that will be containing the spied parameter
     * @return the proxied consumer
     */
    public static <T> Consumer<T> spy(Consumer<T> consumer, Box<T> param) {
        return new CapturingConsumer<T>(consumer, param);
    }

    /**
     * Proxies a binary consumer spying for parameters.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param consumer the consumer that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> spy(BiConsumer<T1, T2> consumer, Box<T1> param1, Box<T2> param2) {
        return new BinaryCapturingConsumer<T1, T2>(consumer, param1, param2);
    }

    /**
     * Proxies a ternary consumer spying for parameters.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> spy(TriConsumer<T1, T2, T3> consumer, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingConsumer<T1, T2, T3>(consumer, param1, param2, param3);
    }

    /**
     * Proxies a ternary function spying for result.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param function the function that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> spyRes(TriFunction<T1, T2, T3, R> function, Box<R> result) {
        return spy(function, result, Box.<T1>empty(), Box.<T2>empty(), Box.<T3>empty());
    }

    /**
     * Proxies a ternary function spying for first parameter.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param function the function that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> spy1st(TriFunction<T1, T2, T3, R> function, Box<T1> param1) {
        return spy(function, Box.<R>empty(), param1, Box.<T2>empty(), Box.<T3>empty());
    }

    /**
     * Proxies a ternary function spying for second parameter
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param function the function that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> spy2nd(TriFunction<T1, T2, T3, R> function, Box<T2> param2) {
        return spy(function, Box.<R>empty(), Box.<T1>empty(), param2, Box.<T3>empty());
    }

    /**
     * Proxies a ternary function spying for third parameter.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param function the function that will be spied
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> spy3rd(TriFunction<T1, T2, T3, R> function, Box<T3> param3) {
        return spy(function, Box.<R>empty(), Box.<T1>empty(), Box.<T2>empty(), param3);
    }

    /**
     * Proxies a binary function spying for result.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function result type
     * @param function the function that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> spyRes(BiFunction<T1, T2, R> function, Box<R> result) {
        return spy(function, result, Box.<T1>empty(), Box.<T2>empty());
    }

    /**
     * Proxies a binary function spying for first parameter.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function result type
     * @param function the function that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> spy1st(BiFunction<T1, T2, R> function, Box<T1> param1) {
        return spy(function, Box.<R>empty(), param1, Box.<T2>empty());
    }

    /**
     * Proxies a binary function spying for second parameter.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function result type
     * @param function the function that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> spy2nd(BiFunction<T1, T2, R> function, Box<T2> param2) {
        return spy(function, Box.<R>empty(), Box.<T1>empty(), param2);
    }

    /**
     * Proxies a function spying for result.
     *
     * @param <R> the function result type
     * @param <T> the function parameter type
     * @param function the function that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied function
     */
    public static <R, T> Function<T, R> spyRes(Function<T, R> function, Box<R> result) {
        return spy(function, result, Box.<T>empty());
    }

    /**
     * Proxies a function spying for parameter.
     *
     * @param <R> the function result type
     * @param <T> the function parameter type
     * @param function the function that will be spied
     * @param param a box that will be containing spied parameter
     * @return the proxied function
     */
    public static <R, T> Function<T, R> spy1st(Function<T, R> function, Box<T> param) {
        return spy(function, Box.<R>empty(), param);
    }

    /**
     * Proxies a ternary consumer spying for first parameter.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> spy1st(TriConsumer<T1, T2, T3> consumer, Box<T1> param1) {
        return spy(consumer, param1, Box.<T2>empty(), Box.<T3>empty());
    }

    /**
     * Proxies a ternary consumer spying for second parameter.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> spy2nd(TriConsumer<T1, T2, T3> consumer, Box<T2> param2) {
        return spy(consumer, Box.<T1>empty(), param2, Box.<T3>empty());
    }

    /**
     * Proxies a ternary consumer spying for third parameter.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer that will be spied
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> spy3rd(TriConsumer<T1, T2, T3> consumer, Box<T3> param3) {
        return spy(consumer, Box.<T1>empty(), Box.<T2>empty(), param3);
    }

    /**
     * Proxies a binary consumer spying for first parameter.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param consumer the consumer that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> spy1st(BiConsumer<T1, T2> consumer, Box<T1> param1) {
        return spy(consumer, param1, Box.<T2>empty());
    }

    /**
     * Proxies a binary consumer spying for second parameter.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param consumer the consumer that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> spy2nd(BiConsumer<T1, T2> consumer, Box<T2> param2) {
        return spy(consumer, Box.<T1>empty(), param2);
    }

    /**
     * Proxies a binary consumer spying for first parameter.
     *
     * @param <T> the consumer parameter type
     * @param consumer the consumer that will be spied
     * @param param a box that will be containing the spied parameter
     * @return the proxied consumer
     */
    public static <T> Consumer<T> spy1st(Consumer<T> consumer, Box<T> param) {
        return spy(consumer, param);
    }

    /**
     * Proxies a ternary predicate spying for result.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> spyRes(TriPredicate<T1, T2, T3> predicate, Box<Boolean> result) {
        return spy(predicate, result, Box.<T1>empty(), Box.<T2>empty(), Box.<T3>empty());
    }

    /**
     * Proxies a ternary predicate spying for first parameter.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> spy1st(TriPredicate<T1, T2, T3> predicate, Box<T1> param1) {
        return spy(predicate, Box.<Boolean>empty(), param1, Box.<T2>empty(), Box.<T3>empty());
    }

    /**
     * Proxies a ternary predicate spying for second parameter.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> spy2nd(TriPredicate<T1, T2, T3> predicate, Box<T2> param2) {
        return spy(predicate, Box.<Boolean>empty(), Box.<T1>empty(), param2, Box.<T3>empty());
    }

    /**
     * Proxies a ternary predicate spying for third parameter.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate that will be spied
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> spy3rd(TriPredicate<T1, T2, T3> predicate, Box<T3> param3) {
        return spy(predicate, Box.<Boolean>empty(), Box.<T1>empty(), Box.<T2>empty(), param3);
    }

    /**
     * Proxies a binary predicate spying for result.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param predicate the predicate that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> spyRes(BiPredicate<T1, T2> predicate, Box<Boolean> result) {
        return spy(predicate, result, Box.<T1>empty(), Box.<T2>empty());
    }

    /**
     * Proxies a binary predicate spying for first parameter
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param predicate the predicate that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> spy1st(BiPredicate<T1, T2> predicate, Box<T1> param1) {
        return spy(predicate, Box.<Boolean>empty(), param1, Box.<T2>empty());
    }

    /**
     * Proxies a binary predicate spying for second parameter.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param predicate the predicate that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> spy2nd(BiPredicate<T1, T2> predicate, Box<T2> param2) {
        return spy(predicate, Box.<Boolean>empty(), Box.<T1>empty(), param2);
    }

    /**
     * Proxies a predicate spying for result.
     *
     * @param <T> the predicate parameter type
     * @param predicate the predicate that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied predicate
     */
    public static <T> Predicate<T> spyRes(Predicate<T> predicate, Box<Boolean> result) {
        return spy(predicate, result, Box.<T>empty());
    }

    /**
     * Proxies a predicate spying for parameter.
     *
     * @param <T> the predicate parameter type
     * @param predicate the predicate that will be spied
     * @param param a box that will be containing spied parameter
     * @return the proxied predicate
     */
    public static <T> Predicate<T> spy1st(Predicate<T> predicate, Box<T> param) {
        return spy(predicate, Box.<Boolean>empty(), param);
    }

    /**
     * Monitors calls to an consumer.
     *
     * @param <T> the consumer parameter type
     * @param consumer the consumer that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied consumer
     */
    public static <T> Consumer<T> monitor(Consumer<T> consumer, AtomicLong calls) {
        return new MonitoringConsumer<T>(consumer, calls);
    }

    /**
     * Monitors calls to a function.
     *
     * @param <T> the function parameter type
     * @param <R> the function result type
     * @param function the function that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied function
     */
    public static <T, R> Function<T, R> monitor(Function<T, R> function, AtomicLong calls) {
        return new MonitoringFunction<>(function, calls);
    }

    /**
     * Monitors calls to a predicate.
     *
     * @param <T> the predicate parameter type
     * @param predicate the predicate that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied predicate
     */
    public static <T> Predicate<T> monitor(Predicate<T> predicate, AtomicLong calls) {
        return new MonitoringPredicate<T>(predicate, calls);
    }

    /**
     * Monitors calls to a proposition.
     *
     * @param proposition the proposition that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied proposition
     */
    public static BooleanSupplier monitor(BooleanSupplier proposition, AtomicLong calls) {
        return new MonitoringProposition(proposition, calls);
    }

    /**
     * Monitors calls to a supplier.
     *
     * @param <R> the supplier result type
     * @param supplier the supplier that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied supplier
     */
    public static <R> Supplier<R> monitor(Supplier<R> supplier, AtomicLong calls) {
        return new MonitoringSupplier<>(supplier, calls);
    }

    /**
     * Monitors calls to a runnable.
     *
     * @param runnable the runnable that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied runnable
     */
    public static Runnable monitor(Runnable runnable, AtomicLong calls) {
        return new MonitoringRunnable(runnable, calls);
    }

    /**
     * Monitors calls to a binary consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param consumer the consumer that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied consumer
     */
    public static <T1, T2> BiConsumer<T1, T2> monitor(BiConsumer<T1, T2> consumer, AtomicLong calls) {
        return new BinaryMonitoringConsumer<T1, T2>(consumer, calls);
    }

    /**
     * Monitors calls to a binary function.
     *
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <R> the function result type
     * @param function the function that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied function
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> monitor(BiFunction<T1, T2, R> function, AtomicLong calls) {
        return new BinaryMonitoringFunction<>(function, calls);
    }

    /**
     * Monitors calls to a binary predicate
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param predicate the predicate that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> monitor(BiPredicate<T1, T2> predicate, AtomicLong calls) {
        return new BinaryMonitoringPredicate<T1, T2>(predicate, calls);
    }

    /**
     * Monitors calls to a ternary consumer.
     *
     * @param <T1> the consumer first parameter type
     * @param <T2> the consumer second parameter type
     * @param <T3> the consumer third parameter type
     * @param consumer the consumer that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied consumer
     */
    public static <T1, T2, T3> TriConsumer<T1, T2, T3> monitor(TriConsumer<T1, T2, T3> consumer, AtomicLong calls) {
        return new TernaryMonitoringConsumer<T1, T2, T3>(consumer, calls);
    }

    /**
     * Monitors calls to a ternary function.
     *
     * @param <R> the function result type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param function the function that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied function
     */
    public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> monitor(TriFunction<T1, T2, T3, R> function, AtomicLong calls) {
        return new TernaryMonitoringFunction<T1, T2, T3, R>(function, calls);
    }

    /**
     * Monitors calls to a ternary predicate.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param predicate the predicate that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> monitor(TriPredicate<T1, T2, T3> predicate, AtomicLong calls) {
        return new TernaryMonitoringPredicate<T1, T2, T3>(predicate, calls);
    }
}
