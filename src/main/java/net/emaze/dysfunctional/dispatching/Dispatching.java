package net.emaze.dysfunctional.dispatching;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.adapting.BinaryPredicateToBinaryDelegate;
import net.emaze.dysfunctional.dispatching.adapting.TernaryDelegateToTernaryPredicate;
import net.emaze.dysfunctional.dispatching.adapting.BinaryDelegateToBinaryPredicate;
import net.emaze.dysfunctional.dispatching.adapting.PredicateToDelegate;
import net.emaze.dysfunctional.dispatching.adapting.TernaryPredicateToTernaryDelegate;
import net.emaze.dysfunctional.dispatching.adapting.ActionToDelegate;
import net.emaze.dysfunctional.dispatching.adapting.DelegateToPredicate;
import net.emaze.dysfunctional.dispatching.adapting.TernaryActionToTernaryDelegate;
import net.emaze.dysfunctional.dispatching.adapting.BinaryDelegateToBinaryAction;
import net.emaze.dysfunctional.dispatching.adapting.BinaryActionToBinaryDelegate;
import net.emaze.dysfunctional.dispatching.adapting.DelegateToAction;
import net.emaze.dysfunctional.dispatching.adapting.TernaryDelegateToTernaryAction;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Composer;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.adapting.ActionBinderFirst;
import net.emaze.dysfunctional.dispatching.adapting.ActionBinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.ActionBinderSecond;
import net.emaze.dysfunctional.dispatching.adapting.ActionBinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.ActionBinderThird;
import net.emaze.dysfunctional.dispatching.adapting.ActionIgnoreFirst;
import net.emaze.dysfunctional.dispatching.adapting.ActionIgnoreFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.ActionIgnoreSecond;
import net.emaze.dysfunctional.dispatching.adapting.ActionIgnoreSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.ActionIgnoreThird;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.adapting.Binder;
import net.emaze.dysfunctional.dispatching.adapting.BinderFirst;
import net.emaze.dysfunctional.dispatching.adapting.BinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.BinderSecond;
import net.emaze.dysfunctional.dispatching.adapting.BinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.BinderThird;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreFirst;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreSecond;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreThird;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderFirst;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderSecond;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreSecond;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreFirst;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreFirstOfThree;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderThird;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreThird;
import net.emaze.dysfunctional.dispatching.composing.AllMatchingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.composing.AllMatchingPredicate;
import net.emaze.dysfunctional.dispatching.composing.AllMatchingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.composing.FirstMatchingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.composing.FirstMatchingPredicate;
import net.emaze.dysfunctional.dispatching.composing.FirstMatchingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryNegator;
import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.Negator;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryNegator;
import net.emaze.dysfunctional.dispatching.logic.TernaryNever;

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
     * @param f
     * @param g
     * @return
     */
    public static <R, T> Provider<R> compose(final Delegate<R, T> f, final Provider<T> g) {
        return new TransformingProvider<R, T>(f, g);
    }

    /**
     * given f, g yields f ° g (f of g, f following g)
     * @param <R>
     * @param <T2>
     * @param <T1>
     * @param f
     * @param g
     * @return
     */
    public static <R, T2, T1> Delegate<R, T1> compose(Delegate<R, T2> f, Delegate<T2, T1> g) {
        return new Composer<R, T2, T1>(f, g);
    }

    /**
     * given f, g, h yields f ° g ° h (f of g of h, f following g following h)
     * @param <R>
     * @param <T3>
     * @param <T2>
     * @param <T1>
     * @param f
     * @param g
     * @param h
     * @return
     */
    public static <R, T3, T2, T1> Delegate<R, T1> compose(Delegate<R, T3> f, Delegate<T3, T2> g, Delegate<T2, T1> h) {
        return compose(f, compose(g, h));
    }

    public static <R, T> Predicate<T> compose(Predicate<R> predicate, Delegate<R, T> delegate) {
        return new TransformingPredicate<R, T>(predicate, delegate);
    }

    public static <R, T2, T1> Predicate<T1> compose(Predicate<R> p, Delegate<R, T2> f, Delegate<T2, T1> g) {
        return compose(p, compose(f, g));
    }

    public static <R, T1, T2> BinaryPredicate<T1, T2> compose(Predicate<R> predicate, BinaryDelegate<R, T1, T2> delegate) {
        return new TransformingBinaryPredicate<R, T1, T2>(predicate, delegate);
    }

    public static <R, T1, T2, T3> TernaryPredicate<T1, T2, T3> compose(Predicate<R> predicate, TernaryDelegate<R, T1, T2, T3> delegate) {
        return new TransformingTernaryPredicate<R, T1, T2, T3>(predicate, delegate);
    }
    
    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T> the element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T> Predicate<T> and(Iterable<Predicate<T>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterable of predicates");
        return new AllMatchingPredicate<T>(predicates);
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T> the element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T> Predicate<T> and(Iterator<Predicate<T>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterator of predicates");
        return Dispatching.and(Consumers.all(predicates));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T> the element type parameter
     * @param first
     * @param second 
     * @return the composite predicate
     */
    public static <T> Predicate<T> and(Predicate<T> first, Predicate<T> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Dispatching.and(Arrays.asList(first, second));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T> the element type parameter
     * @param first
     * @param second
     * @param third 
     * @return the composite predicate
     */
    public static <T> Predicate<T> and(Predicate<T> first, Predicate<T> second, Predicate<T> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Dispatching.and(Arrays.asList(first, second, third));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> and2(Iterable<BinaryPredicate<T1, T2>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterable of predicates");
        return new AllMatchingBinaryPredicate<T1, T2>(predicates);
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> and2(Iterator<BinaryPredicate<T1, T2>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterator of predicates");
        return Dispatching.and2(Consumers.all(predicates));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> and2(BinaryPredicate<T1, T2> first, BinaryPredicate<T1, T2> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Dispatching.and2(Arrays.asList(first, second));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> and2(BinaryPredicate<T1, T2> first, BinaryPredicate<T1, T2> second, BinaryPredicate<T1, T2> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Dispatching.and2(Arrays.asList(first, second, third));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> and3(Iterable<TernaryPredicate<T1, T2, T3>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterable of predicates");
        return new AllMatchingTernaryPredicate<T1, T2, T3>(predicates);
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> and3(Iterator<TernaryPredicate<T1, T2, T3>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterator of predicates");
        return Dispatching.and3(Consumers.all(predicates));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param first
     * @param second 
     * @return the composite predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> and3(TernaryPredicate<T1, T2, T3> first, TernaryPredicate<T1, T2, T3> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Dispatching.and3(Arrays.asList(first, second));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param first
     * @param second
     * @param third 
     * @return the composite predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> and3(TernaryPredicate<T1, T2, T3> first, TernaryPredicate<T1, T2, T3> second, TernaryPredicate<T1, T2, T3> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Dispatching.and3(Arrays.asList(first, second, third));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T> the element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T> Predicate<T> or(Iterable<Predicate<T>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterable of predicates");
        return new FirstMatchingPredicate<T>(predicates);
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T> the element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T> Predicate<T> or(Iterator<Predicate<T>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterator of predicates");
        return Dispatching.or(Consumers.all(predicates));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T> the element type parameter
     * @param first
     * @param second 
     * @return the composite predicate
     */
    public static <T> Predicate<T> or(Predicate<T> first, Predicate<T> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Dispatching.or(Arrays.asList(first, second));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T> the element type parameter
     * @param first 
     * @param second 
     * @param third
     * @return the composite predicate
     */
    public static <T> Predicate<T> or(Predicate<T> first, Predicate<T> second, Predicate<T> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Dispatching.or(Arrays.asList(first, second, third));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> or2(Iterable<BinaryPredicate<T1, T2>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterable of predicates");
        return new FirstMatchingBinaryPredicate<T1, T2>(predicates);
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> or2(Iterator<BinaryPredicate<T1, T2>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterator of predicates");
        return Dispatching.or2(Consumers.all(predicates));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> or2(BinaryPredicate<T1, T2> first, BinaryPredicate<T1, T2> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Dispatching.or2(Arrays.asList(first, second));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param first
     * @param second
     * @param third 
     * @return the composite predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> or2(BinaryPredicate<T1, T2> first, BinaryPredicate<T1, T2> second, BinaryPredicate<T1, T2> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Dispatching.or2(Arrays.asList(first, second, third));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> or3(Iterable<TernaryPredicate<T1, T2, T3>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterable of predicates");
        return new FirstMatchingTernaryPredicate<T1, T2, T3>(predicates);
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> or3(Iterator<TernaryPredicate<T1, T2, T3>> predicates) {
        dbc.precondition(predicates != null, "cannot compose a null iterator of predicates");
        return Dispatching.or3(Consumers.all(predicates));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> or3(TernaryPredicate<T1, T2, T3> first, TernaryPredicate<T1, T2, T3> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Dispatching.or3(Arrays.asList(first, second));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param first
     * @param second
     * @param third
     * @return the composite predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> or3(TernaryPredicate<T1, T2, T3> first, TernaryPredicate<T1, T2, T3> second, TernaryPredicate<T1, T2, T3> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Dispatching.or3(Arrays.asList(first, second, third));
    }

    /**
     * Negates the given predicate.
     * @param <T> the element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return new Negator<T>(predicate);
    }

    /**
     * Negates the given predicate.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> not(BinaryPredicate<T1, T2> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return new BinaryNegator<T1, T2>(predicate);
    }

    /**
     * Negates the given predicate.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> not(TernaryPredicate<T1, T2, T3> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return new TernaryNegator<T1, T2, T3>(predicate);
    }

    /**
     * Creates a predicate always yielding true.
     * @param <T> the element type parameter
     * @return the predicate
     */
    public static <T> Predicate<T> always() {
        return new Always<T>();
    }

    /**
     * Creates a predicate always yielding true.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @return the predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> always2() {
        return new BinaryAlways<T1, T2>();
    }

    /**
     * Creates a predicate always yielding true.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @return the predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> always3() {
        return new TernaryAlways<T1, T2, T3>();
    }

    /**
     * Creates a predicate always yielding false.
     * @param <T> the element type parameter
     * @return the predicate
     */
    public static <T> Predicate<T> never() {
        return new Never<T>();
    }

    /**
     * Creates a predicate always yielding false.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @return the predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> never2() {
        return new BinaryNever<T1, T2>();
    }

    /**
     * Creates a predicate always yielding false.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @return the predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> never3() {
        return new TernaryNever<T1, T2, T3>();
    }    
}
