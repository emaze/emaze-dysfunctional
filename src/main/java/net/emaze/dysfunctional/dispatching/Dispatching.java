package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Composer;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.adapting.Binder;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderFirst;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderSecond;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderThird;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateBinderSecond;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateIgnoreSecond;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateIgnoreFirst;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @author rferranti
 */
public abstract class Dispatching {

    /**
     * 
     * @param <T>
     * @param adaptee
     * @return
     */
    public static <T> ActionToDelegate<T> action2delegate(Action<T> adaptee) {
        return new ActionToDelegate<T>(adaptee);
    }

    /**
     * 
     * @param <T1>
     * @param <T2>
     * @param adaptee
     * @return
     */
    public static <T1, T2> BinaryActionToBinaryDelegate<T1, T2> action2delegate(BinaryAction<T1, T2> adaptee) {
        return new BinaryActionToBinaryDelegate<T1, T2>(adaptee);
    }

    /**
     * 
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param adaptee
     * @return
     */
    public static <T1, T2, T3> TernaryActionToTernaryDelegate<T1, T2, T3> action2delegate(TernaryAction<T1, T2, T3> adaptee) {
        return new TernaryActionToTernaryDelegate<T1, T2, T3>(adaptee);
    }

    /**
     * 
     * @param <R>
     * @param <T>
     * @param delegate
     * @param only
     * @return
     */
    public static <R, T> Provider<R> bind(Delegate<R, T> delegate, T only) {
        return new Binder<R, T>(delegate, only);
    }

    /**
     *
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2> Delegate<R, T2> bind1st(BinaryDelegate<R, T1, T2> delegate, T1 first) {
        return new BinderFirst<R, T1, T2>(delegate, first);
    }

    /**
     * 
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T2, T3> bind1st(TernaryDelegate<R, T1, T2, T3> delegate, T1 first) {
        return new BinderFirstOfThree<R, T1, T2, T3>(delegate, first);
    }

    /**
     * 
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param delegate
     * @param second
     * @return
     */
    public static <R, T1, T2> Delegate<R, T1> bind2nd(BinaryDelegate<R, T1, T2> delegate, T2 second) {
        return new BinderSecond<R, T1, T2>(delegate, second);
    }

    public static <T1, T2> Predicate<T1> bind2nd(BinaryPredicate<T1, T2> predicate, T2 second) {
        return new PredicateBinderSecond<T1, T2>(predicate, second);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> ignore2nd(Predicate<T1> predicate, Class<T2> ignored) {
        return new PredicateIgnoreSecond<T1, T2>(predicate);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> ignore1st(Predicate<T2> predicate, Class<T1> ignored) {
        return new PredicateIgnoreFirst<T1, T2>(predicate);
    }

    /**
     *
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T1, T3> bind2nd(TernaryDelegate<R, T1, T2, T3> delegate, T2 first) {
        return new BinderSecondOfThree<R, T1, T2, T3>(delegate, first);
    }

    /**
     * 
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T1, T2> bind3rd(TernaryDelegate<R, T1, T2, T3> delegate, T3 first) {
        return new BinderThird<R, T1, T2, T3>(delegate, first);
    }

    /**
     * given f, g yields f ° g (f of g, f following g)
     * @param <R>
     * @param <T>
     * @param <U>
     * @param f
     * @param g
     * @return
     */
    public static <R, T, U> Delegate<R, T> compose(Delegate<R, U> f, Delegate<U, T> g) {
        return new Composer<R, T, U>(f, g);
    }
}
