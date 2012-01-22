package net.emaze.dysfunctional;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.Proposition;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.dispatching.spying.BinaryCapturingAction;
import net.emaze.dysfunctional.dispatching.spying.BinaryCapturingDelegate;
import net.emaze.dysfunctional.dispatching.spying.BinaryCapturingPredicate;
import net.emaze.dysfunctional.dispatching.spying.BinaryMonitoringAction;
import net.emaze.dysfunctional.dispatching.spying.BinaryMonitoringDelegate;
import net.emaze.dysfunctional.dispatching.spying.BinaryMonitoringPredicate;
import net.emaze.dysfunctional.dispatching.spying.CapturingAction;
import net.emaze.dysfunctional.dispatching.spying.CapturingDelegate;
import net.emaze.dysfunctional.dispatching.spying.CapturingPredicate;
import net.emaze.dysfunctional.dispatching.spying.CapturingProposition;
import net.emaze.dysfunctional.dispatching.spying.CapturingProvider;
import net.emaze.dysfunctional.dispatching.spying.MonitoringAction;
import net.emaze.dysfunctional.dispatching.spying.MonitoringDelegate;
import net.emaze.dysfunctional.dispatching.spying.MonitoringPredicate;
import net.emaze.dysfunctional.dispatching.spying.MonitoringProposition;
import net.emaze.dysfunctional.dispatching.spying.MonitoringProvider;
import net.emaze.dysfunctional.dispatching.spying.MonitoringRunnable;
import net.emaze.dysfunctional.dispatching.spying.TernaryCapturingAction;
import net.emaze.dysfunctional.dispatching.spying.TernaryCapturingDelegate;
import net.emaze.dysfunctional.dispatching.spying.TernaryCapturingPredicate;
import net.emaze.dysfunctional.dispatching.spying.TernaryMonitoringAction;
import net.emaze.dysfunctional.dispatching.spying.TernaryMonitoringDelegate;
import net.emaze.dysfunctional.dispatching.spying.TernaryMonitoringPredicate;
import net.emaze.dysfunctional.options.Box;

/**
 *
 * @author rferranti
 */
public abstract class Spies {

    public static Proposition spy(Proposition proposition, Box<Boolean> result) {
        return new CapturingProposition(proposition, result);
    }

    public static <R> Provider<R> spy(Provider<R> provider, Box<R> result) {
        return new CapturingProvider<R>(provider, result);
    }

    public static <R, T> Delegate<R, T> spy(Delegate<R, T> delegate, Box<R> result, Box<T> param) {
        return new CapturingDelegate<R, T>(delegate, result, param);
    }

    public static <R, T1, T2> BinaryDelegate<R, T1, T2> spy(BinaryDelegate<R, T1, T2> delegate, Box<R> result, Box<T1> param1, Box<T2> param2) {
        return new BinaryCapturingDelegate<R, T1, T2>(delegate, result, param1, param2);
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spy(TernaryDelegate<R, T1, T2, T3> delegate, Box<R> result, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingDelegate<R, T1, T2, T3>(delegate, result, param1, param2, param3);
    }

    public static <T> Predicate<T> spy(Predicate<T> delegate, Box<Boolean> result, Box<T> param) {
        return new CapturingPredicate<T>(delegate, result, param);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> spy(BinaryPredicate<T1, T2> delegate, Box<Boolean> result, Box<T1> param1, Box<T2> param2) {
        return new BinaryCapturingPredicate<T1, T2>(delegate, result, param1, param2);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spy(TernaryPredicate<T1, T2, T3> delegate, Box<Boolean> result, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingPredicate<T1, T2, T3>(delegate, result, param1, param2, param3);
    }

    public static <T> Action<T> spy(Action<T> delegate, Box<T> param) {
        return new CapturingAction<T>(delegate, param);
    }

    public static <T1, T2> BinaryAction<T1, T2> spy(BinaryAction<T1, T2> delegate, Box<T1> param1, Box<T2> param2) {
        return new BinaryCapturingAction<T1, T2>(delegate, param1, param2);
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> spy(TernaryAction<T1, T2, T3> delegate, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        return new TernaryCapturingAction<T1, T2, T3>(delegate, param1, param2, param3);
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spyRes(TernaryDelegate<R, T1, T2, T3> delegate, Box<R> result) {
        return spy(delegate, result, Box.<T1>empty(), Box.<T2>empty(), Box.<T3>empty());
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spy1st(TernaryDelegate<R, T1, T2, T3> delegate, Box<T1> param1) {
        return spy(delegate, Box.<R>empty(), param1, Box.<T2>empty(), Box.<T3>empty());
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spy2nd(TernaryDelegate<R, T1, T2, T3> delegate, Box<T2> param2) {
        return spy(delegate, Box.<R>empty(), Box.<T1>empty(), param2, Box.<T3>empty());
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> spy3rd(TernaryDelegate<R, T1, T2, T3> delegate, Box<T3> param3) {
        return spy(delegate, Box.<R>empty(), Box.<T1>empty(), Box.<T2>empty(), param3);
    }

    public static <R, T1, T2> BinaryDelegate<R, T1, T2> spyRes(BinaryDelegate<R, T1, T2> delegate, Box<R> result) {
        return spy(delegate, result, Box.<T1>empty(), Box.<T2>empty());
    }

    public static <R, T1, T2> BinaryDelegate<R, T1, T2> spy1st(BinaryDelegate<R, T1, T2> delegate, Box<T1> param1) {
        return spy(delegate, Box.<R>empty(), param1, Box.<T2>empty());
    }

    public static <R, T1, T2> BinaryDelegate<R, T1, T2> spy2nd(BinaryDelegate<R, T1, T2> delegate, Box<T2> param2) {
        return spy(delegate, Box.<R>empty(), Box.<T1>empty(), param2);
    }

    public static <R, T> Delegate<R, T> spyRes(Delegate<R, T> delegate, Box<R> result) {
        return spy(delegate, result, Box.<T>empty());
    }

    public static <R, T> Delegate<R, T> spy1st(Delegate<R, T> delegate, Box<T> param) {
        return spy(delegate, Box.<R>empty(), param);
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> spy1st(TernaryAction<T1, T2, T3> action, Box<T1> param1) {
        return spy(action, param1, Box.<T2>empty(), Box.<T3>empty());
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> spy2nd(TernaryAction<T1, T2, T3> action, Box<T2> param2) {
        return spy(action, Box.<T1>empty(), param2, Box.<T3>empty());
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> spy3rd(TernaryAction<T1, T2, T3> action, Box<T3> param3) {
        return spy(action, Box.<T1>empty(), Box.<T2>empty(), param3);
    }

    public static <T1, T2> BinaryAction<T1, T2> spy1st(BinaryAction<T1, T2> action, Box<T1> param1) {
        return spy(action, param1, Box.<T2>empty());
    }

    public static <T1, T2> BinaryAction<T1, T2> spy2nd(BinaryAction<T1, T2> action, Box<T2> param2) {
        return spy(action, Box.<T1>empty(), param2);
    }

    public static <T> Action<T> spy1st(Action<T> action, Box<T> param) {
        return spy(action, param);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spyRes(TernaryPredicate<T1, T2, T3> predicate, Box<Boolean> result) {
        return spy(predicate, result, Box.<T1>empty(), Box.<T2>empty(), Box.<T3>empty());
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spy1st(TernaryPredicate<T1, T2, T3> predicate, Box<T1> param1) {
        return spy(predicate, Box.<Boolean>empty(), param1, Box.<T2>empty(), Box.<T3>empty());
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spy2nd(TernaryPredicate<T1, T2, T3> predicate, Box<T2> param2) {
        return spy(predicate, Box.<Boolean>empty(), Box.<T1>empty(), param2, Box.<T3>empty());
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> spy3rd(TernaryPredicate<T1, T2, T3> predicate, Box<T3> param3) {
        return spy(predicate, Box.<Boolean>empty(), Box.<T1>empty(), Box.<T2>empty(), param3);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> spyRes(BinaryPredicate<T1, T2> predicate, Box<Boolean> result) {
        return spy(predicate, result, Box.<T1>empty(), Box.<T2>empty());
    }

    public static <T1, T2> BinaryPredicate<T1, T2> spy1st(BinaryPredicate<T1, T2> predicate, Box<T1> param1) {
        return spy(predicate, Box.<Boolean>empty(), param1, Box.<T2>empty());
    }

    public static <T1, T2> BinaryPredicate<T1, T2> spy2nd(BinaryPredicate<T1, T2> predicate, Box<T2> param2) {
        return spy(predicate, Box.<Boolean>empty(), Box.<T1>empty(), param2);
    }

    public static <T> Predicate<T> spyRes(Predicate<T> predicate, Box<Boolean> result) {
        return spy(predicate, result, Box.<T>empty());
    }

    public static <T> Predicate<T> spy1st(Predicate<T> predicate, Box<T> param) {
        return spy(predicate, Box.<Boolean>empty(), param);
    }

    public static <T> Action<T> monitor(Action<T> action, AtomicLong calls) {
        return new MonitoringAction<T>(action, calls);
    }

    public static <R, T> Delegate<R, T> monitor(Delegate<R, T> delegate, AtomicLong calls) {
        return new MonitoringDelegate<R, T>(delegate, calls);
    }

    public static <T> Predicate<T> monitor(Predicate<T> predicate, AtomicLong calls) {
        return new MonitoringPredicate<T>(predicate, calls);
    }

    public static Proposition monitor(Proposition proposition, AtomicLong calls) {
        return new MonitoringProposition(proposition, calls);
    }

    public static <T> Provider<T> monitor(Provider<T> provider, AtomicLong calls) {
        return new MonitoringProvider<T>(provider, calls);
    }

    public static Runnable monitor(Runnable runnable, AtomicLong calls) {
        return new MonitoringRunnable(runnable, calls);
    }

    public static <T1, T2> BinaryAction<T1, T2> monitor(BinaryAction<T1, T2> action, AtomicLong calls) {
        return new BinaryMonitoringAction<T1, T2>(action, calls);
    }

    public static <R, T1, T2> BinaryDelegate<R, T1, T2> monitor(BinaryDelegate<R, T1, T2> delegate, AtomicLong calls) {
        return new BinaryMonitoringDelegate<R, T1, T2>(delegate, calls);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> monitor(BinaryPredicate<T1, T2> predicate, AtomicLong calls) {
        return new BinaryMonitoringPredicate<T1, T2>(predicate, calls);
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> monitor(TernaryAction<T1, T2, T3> action, AtomicLong calls) {
        return new TernaryMonitoringAction<T1, T2, T3>(action, calls);
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> monitor(TernaryDelegate<R, T1, T2, T3> delegate, AtomicLong calls) {
        return new TernaryMonitoringDelegate<R, T1, T2, T3>(delegate, calls);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> monitor(TernaryPredicate<T1, T2, T3> predicate, AtomicLong calls) {
        return new TernaryMonitoringPredicate<T1, T2, T3>(predicate, calls);
    }
}
