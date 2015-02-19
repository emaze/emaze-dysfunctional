package net.emaze.dysfunctional;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.Proposition;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
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
    public static Proposition spy(Proposition proposition, Box<Boolean> result) {
        return new CapturingProposition(proposition, result);
    }

    /**
     * Proxies a provider, spying for result.
     *
     * @param <R> the provider result type
     * @param provider the provider to be spied
     * @param result a box that will be containing spied result
     * @return the proxied provider
     */
    public static <R> Supplier<R> spy(Supplier<R> provider, Box<R> result) {
        return new CapturingProvider<R>(provider, result);
    }

    /**
     * Proxies a delegate spying for result and parameter.
     *
     * @param <T> the delegate parameter type
     * @param <R> the delegate result type
     * @param delegate the delegate to be spied
     * @param result a box that will be containing spied result
     * @param param a box that will be containing spied param
     * @return the proxied delegate
     */
    public static <T, R> Function<T, R> spy(Function<T, R> delegate, Box<R> result, Box<T> param) {
        return new CapturingDelegate<>(delegate, result, param);
    }

    /**
     * Proxies a binary delegate spying for result and parameters.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <R> the delegate result type
     * @param delegate the delegate to be spied
     * @param result a box that will be containing spied result
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied delegate
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> spy(BiFunction<T1, T2, R> delegate, Box<R> result, Box<T1> param1, Box<T2> param2) {
        return new BinaryCapturingDelegate<>(delegate, result, param1, param2);
    }

    /**
     * Proxies a ternary delegate spying for result and parameters.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate to be spied
     * @param result a box that will be containing spied result
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spy(TernaryDelegate<R, T1, T2, T3> delegate, Box<R> result, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingDelegate<R, T1, T2, T3>(delegate, result, param1, param2, param3);
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spy(TernaryPredicate<T1, T2, T3> predicate, Box<Boolean> result, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingPredicate<T1, T2, T3>(predicate, result, param1, param2, param3);
    }

    /**
     * Proxies an action spying for parameter.
     *
     * @param <T> the action parameter type
     * @param action the action to be spied
     * @param param a box that will be containing the spied parameter
     * @return the proxied action
     */
    public static <T> Consumer<T> spy(Consumer<T> action, Box<T> param) {
        return new CapturingAction<T>(action, param);
    }

    /**
     * Proxies a binary action spying for parameters.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param action the action that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied action
     */
    public static <T1, T2> BinaryAction<T1, T2> spy(BinaryAction<T1, T2> action, Box<T1> param1, Box<T2> param2) {
        return new BinaryCapturingAction<T1, T2>(action, param1, param2);
    }

    /**
     * Proxies a ternary action spying for parameters.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the action that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @param param2 a box that will be containing the second spied parameter
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> spy(TernaryAction<T1, T2, T3> action, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingAction<T1, T2, T3>(action, param1, param2, param3);
    }

    /**
     * Proxies a ternary delegate spying for result.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spyRes(TernaryDelegate<R, T1, T2, T3> delegate, Box<R> result) {
        return spy(delegate, result, Box.<T1>empty(), Box.<T2>empty(), Box.<T3>empty());
    }

    /**
     * Proxies a ternary delegate spying for first parameter.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spy1st(TernaryDelegate<R, T1, T2, T3> delegate, Box<T1> param1) {
        return spy(delegate, Box.<R>empty(), param1, Box.<T2>empty(), Box.<T3>empty());
    }

    /**
     * Proxies a ternary delegate spying for second parameter
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spy2nd(TernaryDelegate<R, T1, T2, T3> delegate, Box<T2> param2) {
        return spy(delegate, Box.<R>empty(), Box.<T1>empty(), param2, Box.<T3>empty());
    }

    /**
     * Proxies a ternary delegate spying for third parameter.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate that will be spied
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spy3rd(TernaryDelegate<R, T1, T2, T3> delegate, Box<T3> param3) {
        return spy(delegate, Box.<R>empty(), Box.<T1>empty(), Box.<T2>empty(), param3);
    }

    /**
     * Proxies a binary delegate spying for result.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <R> the delegate result type
     * @param delegate the delegate that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied delegate
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> spyRes(BiFunction<T1, T2, R> delegate, Box<R> result) {
        return spy(delegate, result, Box.<T1>empty(), Box.<T2>empty());
    }

    /**
     * Proxies a binary delegate spying for first parameter.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <R> the delegate result type
     * @param delegate the delegate that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied delegate
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> spy1st(BiFunction<T1, T2, R> delegate, Box<T1> param1) {
        return spy(delegate, Box.<R>empty(), param1, Box.<T2>empty());
    }

    /**
     * Proxies a binary delegate spying for second parameter.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <R> the delegate result type
     * @param delegate the delegate that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied delegate
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> spy2nd(BiFunction<T1, T2, R> delegate, Box<T2> param2) {
        return spy(delegate, Box.<R>empty(), Box.<T1>empty(), param2);
    }

    /**
     * Proxies a delegate spying for result.
     *
     * @param <R> the delegate result type
     * @param <T> the delegate parameter type
     * @param delegate the delegate that will be spied
     * @param result a box that will be containing spied result
     * @return the proxied delegate
     */
    public static <R, T> Function<T, R> spyRes(Function<T, R> delegate, Box<R> result) {
        return spy(delegate, result, Box.<T>empty());
    }

    /**
     * Proxies a delegate spying for parameter.
     *
     * @param <R> the delegate result type
     * @param <T> the delegate parameter type
     * @param delegate the delegate that will be spied
     * @param param a box that will be containing spied parameter
     * @return the proxied delegate
     */
    public static <R, T> Function<T, R> spy1st(Function<T, R> delegate, Box<T> param) {
        return spy(delegate, Box.<R>empty(), param);
    }

    /**
     * Proxies a ternary action spying for first parameter.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the action that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> spy1st(TernaryAction<T1, T2, T3> action, Box<T1> param1) {
        return spy(action, param1, Box.<T2>empty(), Box.<T3>empty());
    }

    /**
     * Proxies a ternary action spying for second parameter.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the action that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> spy2nd(TernaryAction<T1, T2, T3> action, Box<T2> param2) {
        return spy(action, Box.<T1>empty(), param2, Box.<T3>empty());
    }

    /**
     * Proxies a ternary action spying for third parameter.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the action that will be spied
     * @param param3 a box that will be containing the third spied parameter
     * @return the proxied action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> spy3rd(TernaryAction<T1, T2, T3> action, Box<T3> param3) {
        return spy(action, Box.<T1>empty(), Box.<T2>empty(), param3);
    }

    /**
     * Proxies a binary action spying for first parameter.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param action the action that will be spied
     * @param param1 a box that will be containing the first spied parameter
     * @return the proxied action
     */
    public static <T1, T2> BinaryAction<T1, T2> spy1st(BinaryAction<T1, T2> action, Box<T1> param1) {
        return spy(action, param1, Box.<T2>empty());
    }

    /**
     * Proxies a binary action spying for second parameter.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param action the action that will be spied
     * @param param2 a box that will be containing the second spied parameter
     * @return the proxied action
     */
    public static <T1, T2> BinaryAction<T1, T2> spy2nd(BinaryAction<T1, T2> action, Box<T2> param2) {
        return spy(action, Box.<T1>empty(), param2);
    }

    /**
     * Proxies a binary action spying for first parameter.
     *
     * @param <T> the action parameter type
     * @param action the action that will be spied
     * @param param a box that will be containing the spied parameter
     * @return the proxied action
     */
    public static <T> Consumer<T> spy1st(Consumer<T> action, Box<T> param) {
        return spy(action, param);
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spyRes(TernaryPredicate<T1, T2, T3> predicate, Box<Boolean> result) {
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spy1st(TernaryPredicate<T1, T2, T3> predicate, Box<T1> param1) {
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spy2nd(TernaryPredicate<T1, T2, T3> predicate, Box<T2> param2) {
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spy3rd(TernaryPredicate<T1, T2, T3> predicate, Box<T3> param3) {
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
     * Monitors calls to an action.
     *
     * @param <T> the action parameter type
     * @param action the action that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied action
     */
    public static <T> Consumer<T> monitor(Consumer<T> action, AtomicLong calls) {
        return new MonitoringAction<T>(action, calls);
    }

    /**
     * Monitors calls to a delegate.
     *
     * @param <T> the delegate parameter type
     * @param <R> the delegate result type
     * @param delegate the delegate that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied delegate
     */
    public static <T, R> Function<T, R> monitor(Function<T, R> delegate, AtomicLong calls) {
        return new MonitoringDelegate<>(delegate, calls);
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
    public static Proposition monitor(Proposition proposition, AtomicLong calls) {
        return new MonitoringProposition(proposition, calls);
    }

    /**
     * Monitors calls to a provider.
     *
     * @param <R> the provider result type
     * @param provider the provider that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied provider
     */
    public static <R> Supplier<R> monitor(Supplier<R> provider, AtomicLong calls) {
        return new MonitoringProvider<R>(provider, calls);
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
     * Monitors calls to a binary action.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param action the action that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied action
     */
    public static <T1, T2> BinaryAction<T1, T2> monitor(BinaryAction<T1, T2> action, AtomicLong calls) {
        return new BinaryMonitoringAction<T1, T2>(action, calls);
    }

    /**
     * Monitors calls to a binary delegate.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <R> the delegate result type
     * @param delegate the delegate that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied delegate
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> monitor(BiFunction<T1, T2, R> delegate, AtomicLong calls) {
        return new BinaryMonitoringDelegate<>(delegate, calls);
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
     * Monitors calls to a ternary action.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the action that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> monitor(TernaryAction<T1, T2, T3> action, AtomicLong calls) {
        return new TernaryMonitoringAction<T1, T2, T3>(action, calls);
    }

    /**
     * Monitors calls to a ternary delegate.
     *
     * @param <R> the delegate result type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate that will be monitored
     * @param calls a value holder accumulating calls
     * @return the proxied delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> monitor(TernaryDelegate<R, T1, T2, T3> delegate, AtomicLong calls) {
        return new TernaryMonitoringDelegate<R, T1, T2, T3>(delegate, calls);
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> monitor(TernaryPredicate<T1, T2, T3> predicate, AtomicLong calls) {
        return new TernaryMonitoringPredicate<T1, T2, T3>(predicate, calls);
    }
}
