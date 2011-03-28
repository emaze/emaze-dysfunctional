package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Composer;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionBinderFirst;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionBinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionBinderSecond;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionBinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionBinderThird;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionIgnoreFirst;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionIgnoreFirstOfThree;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionIgnoreSecond;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionIgnoreSecondOfThree;
import net.emaze.dysfunctional.dispatching.actions.adapting.ActionIgnoreThird;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.adapting.Binder;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderFirst;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderSecond;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderThird;
import net.emaze.dysfunctional.dispatching.delegates.adapting.IgnoreFirst;
import net.emaze.dysfunctional.dispatching.delegates.adapting.IgnoreFirstOfThree;
import net.emaze.dysfunctional.dispatching.delegates.adapting.IgnoreSecond;
import net.emaze.dysfunctional.dispatching.delegates.adapting.IgnoreSecondOfThree;
import net.emaze.dysfunctional.dispatching.delegates.adapting.IgnoreThird;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateBinderFirst;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateBinderSecond;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateBinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateIgnoreSecond;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateIgnoreFirst;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateIgnoreFirstOfThree;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateBinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateBinderThird;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateIgnoreSecondOfThree;
import net.emaze.dysfunctional.dispatching.logic.adapters.PredicateIgnoreThird;

/**
 *
 * @author rferranti
 */
public abstract class Dispatching {

    public static <T1, T2> Action<T2> curry(BinaryAction<T1, T2> action, T1 first) {
        return new ActionBinderFirst<T1, T2>(action, first);
    }

    public static <T1, T2, T3> BinaryAction<T2, T3> curry(TernaryAction<T1, T2, T3> action, T1 first) {
        return new ActionBinderFirstOfThree<T1, T2, T3>(action, first);
    }

    public static <T1, T2> Predicate<T2> curry(BinaryPredicate<T1, T2> predicate, T1 first) {
        return new PredicateBinderFirst<T1, T2>(predicate, first);
    }

    public static <T1, T2, T3> BinaryPredicate<T2, T3> curry(TernaryPredicate<T1, T2, T3> predicate, T1 first) {
        return new PredicateBinderFirstOfThree<T1, T2, T3>(predicate, first);
    }

    public static <R, T> Provider<R> curry(Delegate<R, T> delegate, T only) {
        return new Binder<R, T>(delegate, only);
    }

    public static <R, T1, T2> Delegate<R, T2> curry(BinaryDelegate<R, T1, T2> delegate, T1 first) {
        return new BinderFirst<R, T1, T2>(delegate, first);
    }

    public static <R, T1, T2, T3> BinaryDelegate<R, T2, T3> curry(TernaryDelegate<R, T1, T2, T3> delegate, T1 first) {
        return new BinderFirstOfThree<R, T1, T2, T3>(delegate, first);
    }

    public static <T1, T2, T3> BinaryAction<T1, T3> mcurry(TernaryAction<T1, T2, T3> action, T2 first) {
        return new ActionBinderSecondOfThree<T1, T2, T3>(action, first);
    }

    public static <R, T1, T2, T3> BinaryDelegate<R, T1, T3> mcurry(TernaryDelegate<R, T1, T2, T3> delegate, T2 first) {
        return new BinderSecondOfThree<R, T1, T2, T3>(delegate, first);
    }

    public static <T1, T2, T3> BinaryPredicate<T1, T3> mcurry(TernaryPredicate<T1, T2, T3> predicate, T2 second) {
        return new PredicateBinderSecondOfThree<T1, T2, T3>(predicate, second);
    }

    public static <T1, T2> Action<T1> rcurry(BinaryAction<T1, T2> action, T2 second) {
        return new ActionBinderSecond<T1, T2>(action, second);
    }

    public static <T1, T2, T3> BinaryAction<T1, T2> rcurry(TernaryAction<T1, T2, T3> action, T3 third) {
        return new ActionBinderThird<T1, T2, T3>(action, third);
    }

    public static <T1, T2> Predicate<T1> rcurry(BinaryPredicate<T1, T2> predicate, T2 second) {
        return new PredicateBinderSecond<T1, T2>(predicate, second);
    }

    public static <T1, T2, T3> BinaryPredicate<T1, T2> rcurry(TernaryPredicate<T1, T2, T3> predicate, T3 third) {
        return new PredicateBinderThird<T1, T2, T3>(predicate, third);
    }

    public static <R, T1, T2> Delegate<R, T1> rcurry(BinaryDelegate<R, T1, T2> delegate, T2 second) {
        return new BinderSecond<R, T1, T2>(delegate, second);
    }

    public static <R, T1, T2, T3> BinaryDelegate<R, T1, T2> rcurry(TernaryDelegate<R, T1, T2, T3> delegate, T3 third) {
        return new BinderThird<R, T1, T2, T3>(delegate, third);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> ignore1st(Predicate<T2> predicate, Class<T1> ignored) {
        return new PredicateIgnoreFirst<T1, T2>(predicate);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore1st(BinaryPredicate<T2, T3> predicate, Class<T1> ignored) {
        return new PredicateIgnoreFirstOfThree<T1, T2, T3>(predicate);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> ignore2nd(Predicate<T1> predicate, Class<T2> ignored) {
        return new PredicateIgnoreSecond<T1, T2>(predicate);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore2nd(BinaryPredicate<T1, T3> predicate, Class<T2> ignored) {
        return new PredicateIgnoreSecondOfThree<T1, T2, T3>(predicate);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore3rd(BinaryPredicate<T1, T2> predicate, Class<T3> ignored) {
        return new PredicateIgnoreThird<T1, T2, T3>(predicate);
    }

    public static <T1, T2> BinaryAction<T1, T2> ignore1st(Action<T2> action, Class<T1> ignored) {
        return new ActionIgnoreFirst<T1, T2>(action);
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore1st(BinaryAction<T2, T3> action, Class<T1> ignored) {
        return new ActionIgnoreFirstOfThree<T1, T2, T3>(action);
    }

    public static <T1, T2> BinaryAction<T1, T2> ignore2nd(Action<T1> action, Class<T2> ignored) {
        return new ActionIgnoreSecond<T1, T2>(action);
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore2nd(BinaryAction<T1, T3> action, Class<T2> ignored) {
        return new ActionIgnoreSecondOfThree<T1, T2, T3>(action);
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore3rd(BinaryAction<T1, T2> action, Class<T3> ignored) {
        return new ActionIgnoreThird<T1, T2, T3>(action);
    }

    public static <R, T1, T2> BinaryDelegate<R, T1, T2> ignore1st(Delegate<R, T2> delegate, Class<T1> ignored) {
        return new IgnoreFirst<R, T1, T2>(delegate);
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> ignore1st(BinaryDelegate<R, T2, T3> delegate, Class<T1> ignored) {
        return new IgnoreFirstOfThree<R, T1, T2, T3>(delegate);
    }

    public static <R, T1, T2> BinaryDelegate<R, T1, T2> ignore2nd(Delegate<R, T1> delegate, Class<T2> ignored) {
        return new IgnoreSecond<R, T1, T2>(delegate);
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> ignore2nd(BinaryDelegate<R, T1, T3> delegate, Class<T2> ignored) {
        return new IgnoreSecondOfThree<R, T1, T2, T3>(delegate);
    }

    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> ignore3rd(BinaryDelegate<R, T1, T2> delegate, Class<T3> ignored) {
        return new IgnoreThird<R, T1, T2, T3>(delegate);
    }

    public static <T> ActionToDelegate<T> delegate(Action<T> adaptee) {
        return new ActionToDelegate<T>(adaptee);
    }

    public static <T1, T2> BinaryActionToBinaryDelegate<T1, T2> delegate(BinaryAction<T1, T2> adaptee) {
        return new BinaryActionToBinaryDelegate<T1, T2>(adaptee);
    }

    public static <T1, T2, T3> TernaryActionToTernaryDelegate<T1, T2, T3> delegate(TernaryAction<T1, T2, T3> adaptee) {
        return new TernaryActionToTernaryDelegate<T1, T2, T3>(adaptee);
    }

    public static <T> PredicateToDelegate<T> delegate(Predicate<T> adaptee) {
        return new PredicateToDelegate<T>(adaptee);
    }

    public static <T1, T2> BinaryPredicateToBinaryDelegate<T1, T2> delegate(BinaryPredicate<T1, T2> adaptee) {
        return new BinaryPredicateToBinaryDelegate<T1, T2>(adaptee);
    }

    public static <T1, T2, T3> TernaryPredicateToTernaryDelegate<T1, T2, T3> delegate(TernaryPredicate<T1, T2, T3> adaptee) {
        return new TernaryPredicateToTernaryDelegate<T1, T2, T3>(adaptee);
    }

    public static <T> Action<T> action(Delegate<?, T> delegate) {
        return new DelegateToAction<T>(delegate);
    }

    public static <T1, T2> BinaryAction<T1, T2> action(BinaryDelegate<?, T1, T2> delegate) {
        return new BinaryDelegateToBinaryAction<T1, T2>(delegate);
    }

    public static <T1, T2, T3> TernaryAction<T1, T2, T3> action(TernaryDelegate<?, T1, T2, T3> delegate) {
        return new TernaryDelegateToTernaryAction<T1, T2, T3>(delegate);
    }

    public static <T> Predicate<T> predicate(Delegate<Boolean, T> delegate) {
        return new DelegateToPredicate<T>(delegate);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> predicate(BinaryDelegate<Boolean, T1, T2> delegate) {
        return new BinaryDelegateToBinaryPredicate<T1, T2>(delegate);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> predicate(TernaryDelegate<Boolean, T1, T2, T3> delegate) {
        return new TernaryDelegateToTernaryPredicate<T1, T2, T3>(delegate);
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
