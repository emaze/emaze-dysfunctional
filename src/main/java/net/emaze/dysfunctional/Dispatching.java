package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.adapting.ActionBinder;
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
import net.emaze.dysfunctional.dispatching.adapting.ActionToDelegate;
import net.emaze.dysfunctional.dispatching.adapting.BinaryActionToBinaryDelegate;
import net.emaze.dysfunctional.dispatching.adapting.BinaryDelegateToBinaryAction;
import net.emaze.dysfunctional.dispatching.adapting.BinaryDelegateToBinaryPredicate;
import net.emaze.dysfunctional.dispatching.adapting.BinaryPredicateToBinaryDelegate;
import net.emaze.dysfunctional.dispatching.adapting.Binder;
import net.emaze.dysfunctional.dispatching.adapting.BinderFirst;
import net.emaze.dysfunctional.dispatching.adapting.BinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.BinderSecond;
import net.emaze.dysfunctional.dispatching.adapting.BinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.BinderThird;
import net.emaze.dysfunctional.dispatching.adapting.DelegateToAction;
import net.emaze.dysfunctional.dispatching.adapting.DelegateToPredicate;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreFirst;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreParameter;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreSecond;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.IgnoreThird;
import net.emaze.dysfunctional.dispatching.adapting.IteratingProvider;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinder;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderFirst;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderSecond;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.PredicateBinderThird;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreFirst;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreFirstOfThree;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreSecond;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreSecondOfThree;
import net.emaze.dysfunctional.dispatching.adapting.PredicateIgnoreThird;
import net.emaze.dysfunctional.dispatching.adapting.PredicateToDelegate;
import net.emaze.dysfunctional.dispatching.adapting.PropositionIgnoreParameter;
import net.emaze.dysfunctional.dispatching.adapting.PropositionToProvider;
import net.emaze.dysfunctional.dispatching.adapting.ProviderToProposition;
import net.emaze.dysfunctional.dispatching.adapting.ProviderToRunnable;
import net.emaze.dysfunctional.dispatching.adapting.RunnableIgnoreParameter;
import net.emaze.dysfunctional.dispatching.adapting.RunnableToProvider;
import net.emaze.dysfunctional.dispatching.adapting.TernaryActionToTernaryDelegate;
import net.emaze.dysfunctional.dispatching.adapting.TernaryDelegateToTernaryAction;
import net.emaze.dysfunctional.dispatching.adapting.TernaryDelegateToTernaryPredicate;
import net.emaze.dysfunctional.dispatching.adapting.TernaryPredicateToTernaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.Proposition;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.options.Maybe;

/**
 * curry, mcurry, rcurry, ignore, ignore1st, ignore2nd, ignore3rd, provider,
 * delegate, runnable, action, proposition, predicate.
 *
 * @author rferranti
 */
public abstract class Dispatching {

    /**
     * Partial application of the first parameter to an action.
     *
     * @param <T> the action parameter type
     * @param action the action to be curried
     * @param value the value to be curried
     * @return the curried runnable
     */
    public static <T> Runnable curry(Action<T> action, T value) {
        return new ActionBinder<T>(action, value);
    }

    /**
     * Partial application of the first parameter to a binary action.
     *
     * @param <T1> the action former parameter type
     * @param <T2> the action latter parameter type
     * @param action the binary action to be curried
     * @param first the value to be curried as first parameter
     * @return the curried unary action
     */
    public static <T1, T2> Action<T2> curry(BinaryAction<T1, T2> action, T1 first) {
        return new ActionBinderFirst<T1, T2>(action, first);
    }

    /**
     * Partial application of the first parameter to a ternary action.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the ternary action to be curried as first parameter
     * @param first the value to be curried
     * @return the curried binary action
     */
    public static <T1, T2, T3> BinaryAction<T2, T3> curry(TernaryAction<T1, T2, T3> action, T1 first) {
        return new ActionBinderFirstOfThree<T1, T2, T3>(action, first);
    }

    /**
     * Partial application of the parameter to a predicate.
     *
     * @param <T> the predicate parameter type
     * @param predicate the predicate to be curried
     * @param value the value to be curried
     * @return the curried proposition
     */
    public static <T> Proposition curry(Predicate<T> predicate, T value) {
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
    public static <T1, T2> Predicate<T2> curry(BinaryPredicate<T1, T2> predicate, T1 first) {
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
    public static <T1, T2, T3> BinaryPredicate<T2, T3> curry(TernaryPredicate<T1, T2, T3> predicate, T1 first) {
        return new PredicateBinderFirstOfThree<T1, T2, T3>(predicate, first);
    }

    /**
     * Partial application of the parameter to a delegate.
     *
     * @param <T> the delegate parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be curried
     * @param value the value to be curried
     * @return the curried provider
     */
    public static <T, R> Provider<R> curry(Function<T, R> delegate, T value) {
        return new Binder<>(delegate, value);
    }

    /**
     * Partial application of the first parameter to a binary delegate.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be curried
     * @param first the value to be curried as first parameter
     * @return the curried delegate
     */
    public static <T1, T2, R> Function<T2, R> curry(BinaryDelegate<R, T1, T2> delegate, T1 first) {
        return new BinderFirst<>(delegate, first);
    }

    /**
     * Partial application of the first parameter to a ternary delegate.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be curried
     * @param first the value to be curried as first parameter
     * @return the curried binary delegate
     */
    public static <T1, T2, T3, R> BinaryDelegate<R, T2, T3> curry(TernaryDelegate<R, T1, T2, T3> delegate, T1 first) {
        return new BinderFirstOfThree<R, T1, T2, T3>(delegate, first);
    }

    /**
     * Partial application of the second (middle) parameter to a ternary action.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the action to be curried
     * @param second the value to be curried as second parameter
     * @return the curried binary action
     */
    public static <T1, T2, T3> BinaryAction<T1, T3> mcurry(TernaryAction<T1, T2, T3> action, T2 second) {
        return new ActionBinderSecondOfThree<T1, T2, T3>(action, second);
    }

    /**
     * Partial application of the second (middle) parameter to a ternary
     * delegate.
     *
     * @param <R> the delegate return type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate to be curried
     * @param second the value to be curried as second parameter
     * @return the curried binary delegate
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T1, T3> mcurry(TernaryDelegate<R, T1, T2, T3> delegate, T2 second) {
        return new BinderSecondOfThree<R, T1, T2, T3>(delegate, second);
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
    public static <T1, T2, T3> BinaryPredicate<T1, T3> mcurry(TernaryPredicate<T1, T2, T3> predicate, T2 second) {
        return new PredicateBinderSecondOfThree<T1, T2, T3>(predicate, second);
    }

    /**
     * Partial application of the last (rightmost) parameter to a binary action.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param action the action to be curried
     * @param second the value to be curried as second parameter
     * @return the curried action
     */
    public static <T1, T2> Action<T1> rcurry(BinaryAction<T1, T2> action, T2 second) {
        return new ActionBinderSecond<T1, T2>(action, second);
    }

    /**
     * Partial application of the last (rightmost) parameter to a ternary
     * action.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param action the action to be curried
     * @param third the value to be curried as third parameter
     * @return the curried binary action
     */
    public static <T1, T2, T3> BinaryAction<T1, T2> rcurry(TernaryAction<T1, T2, T3> action, T3 third) {
        return new ActionBinderThird<T1, T2, T3>(action, third);
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
    public static <T1, T2> Predicate<T1> rcurry(BinaryPredicate<T1, T2> predicate, T2 second) {
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
    public static <T1, T2, T3> BinaryPredicate<T1, T2> rcurry(TernaryPredicate<T1, T2, T3> predicate, T3 third) {
        return new PredicateBinderThird<T1, T2, T3>(predicate, third);
    }

    /**
     * Partial application of the last (rightmost) parameter to a binary
     * delegate.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be curried
     * @param second the value to be curried as second parameter
     * @return the curried delegate
     */
    public static <T1, T2, R> Function<T1, R> rcurry(BinaryDelegate<R, T1, T2> delegate, T2 second) {
        return new BinderSecond<>(delegate, second);
    }

    /**
     * Partial application of the last (rightmost) parameter to a ternary
     * delegate.
     *
     * @param <R> the delegate return type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate to be curried
     * @param third the value to be curried as third parameter
     * @return the curried binary delegate
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T1, T2> rcurry(TernaryDelegate<R, T1, T2, T3> delegate, T3 third) {
        return new BinderThird<R, T1, T2, T3>(delegate, third);
    }

    /**
     * Adapts a proposition to a predicate by ignoring the passed parameter.
     *
     * @param <T> the predicate parameter type
     * @param proposition the proposition to be adapted
     * @param ignored the adapted predicate parameter type class
     * @return the adapted predicate
     */
    public static <T> Predicate<T> ignore(Proposition proposition, Class<T> ignored) {
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
    public static <T1, T2> BinaryPredicate<T1, T2> ignore1st(Predicate<T2> predicate, Class<T1> ignored) {
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore1st(BinaryPredicate<T2, T3> predicate, Class<T1> ignored) {
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
    public static <T1, T2> BinaryPredicate<T1, T2> ignore2nd(Predicate<T1> predicate, Class<T2> ignored) {
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore2nd(BinaryPredicate<T1, T3> predicate, Class<T2> ignored) {
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore3rd(BinaryPredicate<T1, T2> predicate, Class<T3> ignored) {
        return new PredicateIgnoreThird<T1, T2, T3>(predicate);
    }

    /**
     * Adapts a runnable to an action by ignoring the parameter.
     *
     * @param <T> the adapted action parameter type
     * @param runnable the runnable to be adapted
     * @param ignored the adapted action ignored parameter type class
     * @return the adapted action
     */
    public static <T> Action<T> ignore(Runnable runnable, Class<T> ignored) {
        return new RunnableIgnoreParameter<T>(runnable);
    }

    /**
     * Adapts an action to a binary action by ignoring the first parameter.
     *
     * @param <T1> the adapted action first parameter type
     * @param <T2> the adapted action second parameter type
     * @param action the action to be adapted
     * @param ignored the adapted action ignored parameter type class
     * @return the adapted binary action
     */
    public static <T1, T2> BinaryAction<T1, T2> ignore1st(Action<T2> action, Class<T1> ignored) {
        return new ActionIgnoreFirst<T1, T2>(action);
    }

    /**
     * Adapts a binary action to a ternary action by ignoring the first
     * parameter.
     *
     * @param <T1> the adapted action first parameter type
     * @param <T2> the adapted action second parameter type
     * @param <T3> the adapted action third parameter type
     * @param action the action to be adapted
     * @param ignored the adapted action ignored parameter type class
     * @return the adapted ternary action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore1st(BinaryAction<T2, T3> action, Class<T1> ignored) {
        return new ActionIgnoreFirstOfThree<T1, T2, T3>(action);
    }

    /**
     * Adapts an action to a binary action by ignoring the second parameter.
     *
     * @param <T1> the adapted action first parameter type
     * @param <T2> the adapted action second parameter type
     * @param action the action to be adapted
     * @param ignored the adapted action ignored parameter type class
     * @return the adapted binary action
     */
    public static <T1, T2> BinaryAction<T1, T2> ignore2nd(Action<T1> action, Class<T2> ignored) {
        return new ActionIgnoreSecond<T1, T2>(action);
    }

    /**
     * Adapts a binary action to a ternary action by ignoring the second
     * parameter.
     *
     * @param <T1> the adapted action first parameter type
     * @param <T2> the adapted action second parameter type
     * @param <T3> the adapted action third parameter type
     * @param action the action to be adapted
     * @param ignored the adapted action ignored parameter type class
     * @return the adapted ternary action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore2nd(BinaryAction<T1, T3> action, Class<T2> ignored) {
        return new ActionIgnoreSecondOfThree<T1, T2, T3>(action);
    }

    /**
     * Adapts a binary action to a ternary action by ignoring the third
     * parameter.
     *
     * @param <T1> the adapted action first parameter type
     * @param <T2> the adapted action second parameter type
     * @param <T3> the adapted action third parameter type
     * @param action the action to be adapted
     * @param ignored the adapted action ignored parameter type class
     * @return the adapted ternary action
     */
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore3rd(BinaryAction<T1, T2> action, Class<T3> ignored) {
        return new ActionIgnoreThird<T1, T2, T3>(action);
    }

    /**
     * Adapts a provider to a delegate by ignoring the passed parameter.
     *
     * @param <T> the adapted delegate parameter type
     * @param <R> the adapted delegate result type
     * @param provider the provider to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <T, R> Function<T, R> ignore(Provider<R> provider, Class<T> ignored) {
        return new IgnoreParameter<>(provider);
    }

    /**
     * Adapts a delegate to a binary delegate by ignoring the first parameter.
     *
     * @param <R> the adapted delegate result type
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <T1, T2, R> BinaryDelegate<R, T1, T2> ignore1st(Function<T2, R> delegate, Class<T1> ignored) {
        return new IgnoreFirst<>(delegate);
    }

    /**
     * Adapts a binary delegate to a ternary delegate by ignoring the first
     * parameter.
     *
     * @param <R> the adapted delegate result type
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param <T3> the adapted delegate third parameter type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> ignore1st(BinaryDelegate<R, T2, T3> delegate, Class<T1> ignored) {
        return new IgnoreFirstOfThree<R, T1, T2, T3>(delegate);
    }

    /**
     * Adapts a delegate to a binary delegate by ignoring the second parameter.
     *
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param <R> the adapted delegate result type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <T1, T2, R> BinaryDelegate<R, T1, T2> ignore2nd(Function<T1, R> delegate, Class<T2> ignored) {
        return new IgnoreSecond<>(delegate);
    }

    /**
     * Adapts a binary delegate to a ternary delegate by ignoring the second
     * parameter.
     *
     * @param <R> the adapted delegate result type
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param <T3> the adapted delegate third parameter type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> ignore2nd(BinaryDelegate<R, T1, T3> delegate, Class<T2> ignored) {
        return new IgnoreSecondOfThree<R, T1, T2, T3>(delegate);
    }

    /**
     * Adapts a binary delegate to a ternary delegate by ignoring the third
     * parameter.
     *
     * @param <R> the adapted delegate result type
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param <T3> the adapted delegate third parameter type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <R, T1, T2, T3> TernaryDelegate<R, T1, T2, T3> ignore3rd(BinaryDelegate<R, T1, T2> delegate, Class<T3> ignored) {
        return new IgnoreThird<R, T1, T2, T3>(delegate);
    }

    /**
     * Adapts an iterator to a provider.
     *
     * @param adaptee the runnable to be adapted
     * @return the adapted provider
     */
    public static <T> Provider<Maybe<T>> provider(Iterator<T> adaptee) {
        return new IteratingProvider<T>(adaptee);
    }

    /**
     * Adapts a runnable to a provider.
     *
     * @param adaptee the runnable to be adapted
     * @return the adapted provider
     */
    public static Provider<Void> provider(Runnable adaptee) {
        return new RunnableToProvider(adaptee);
    }

    /**
     * Adapts a proposition to a provider.
     *
     * @param adaptee the proposition to be adapted
     * @return the adapted provider
     */
    public static Provider<Boolean> provider(Proposition adaptee) {
        return new PropositionToProvider(adaptee);
    }

    /**
     * Adapts an action to a delegate.
     *
     * @param <T> the action parameter type
     * @param adaptee the action to be adapted
     * @return the adapted delegate
     */
    public static <T> Function<T, Void> delegate(Action<T> adaptee) {
        return new ActionToDelegate<T>(adaptee);
    }

    /**
     * Adapts a binary action to a binary delegate.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param adaptee the action to be adapted
     * @return the adapted delegate
     */
    public static <T1, T2> BinaryDelegate<Void, T1, T2> delegate(BinaryAction<T1, T2> adaptee) {
        return new BinaryActionToBinaryDelegate<T1, T2>(adaptee);
    }

    /**
     * Adapts a ternary action to a ternary delegate.
     *
     * @param <T1> the action first parameter type
     * @param <T2> the action second parameter type
     * @param <T3> the action third parameter type
     * @param adaptee the action to be adapted
     * @return the adapted delegate
     */
    public static <T1, T2, T3> TernaryDelegate<Void, T1, T2, T3> delegate(TernaryAction<T1, T2, T3> adaptee) {
        return new TernaryActionToTernaryDelegate<T1, T2, T3>(adaptee);
    }

    /**
     * Adapts a predicate to a delegate.
     *
     * @param <T> the predicate parameter type
     * @param adaptee the predicate to be adapted
     * @return the adapted delegate
     */
    public static <T> Function<T, Boolean> delegate(Predicate<T> adaptee) {
        return new PredicateToDelegate<T>(adaptee);
    }

    /**
     * Adapts a binary predicate to a binary delegate.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param adaptee the predicate to be adapted
     * @return the adapted delegate
     */
    public static <T1, T2> BinaryDelegate<Boolean, T1, T2> delegate(BinaryPredicate<T1, T2> adaptee) {
        return new BinaryPredicateToBinaryDelegate<T1, T2>(adaptee);
    }

    /**
     * Adapts a ternary predicate to a ternary delegate.
     *
     * @param <T1> the predicate first parameter type
     * @param <T2> the predicate second parameter type
     * @param <T3> the predicate third parameter type
     * @param adaptee the predicate to be adapted
     * @return the adapted delegate
     */
    public static <T1, T2, T3> TernaryDelegate<Boolean, T1, T2, T3> delegate(TernaryPredicate<T1, T2, T3> adaptee) {
        return new TernaryPredicateToTernaryDelegate<T1, T2, T3>(adaptee);
    }

    /**
     * Adapts a provider to a runnable.
     *
     * @param <T> the provider parameter type
     * @param provider the provider to be adapted
     * @return the adapted runnable
     */
    public static <T> Runnable runnable(Provider<T> provider) {
        return new ProviderToRunnable(provider);
    }

    /**
     * Adapts a delegate to an action.
     *
     * @param <T> the delegate parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be adapted
     * @return the adapted action
     */
    public static <T, R> Action<T> action(Function<T, R> delegate) {
        return new DelegateToAction<>(delegate);
    }

    /**
     * Adapts a binary delegate to a binary action.
     *
     * @param <R> the delegate return type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param delegate the delegate to be adapted
     * @return the adapted action
     */
    public static <R, T1, T2> BinaryAction<T1, T2> action(BinaryDelegate<R, T1, T2> delegate) {
        return new BinaryDelegateToBinaryAction<R, T1, T2>(delegate);
    }

    /**
     * Adapts a ternary delegate to a ternary action.
     *
     * @param <R> the delegate return type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate to be adapted
     * @return the adapted action
     */
    public static <R, T1, T2, T3> TernaryAction<T1, T2, T3> action(TernaryDelegate<R, T1, T2, T3> delegate) {
        return new TernaryDelegateToTernaryAction<R, T1, T2, T3>(delegate);
    }

    /**
     * Adapts a provider to a proposition.
     *
     * @param provider the provider to be adapted
     * @return the adapted proposition
     */
    public static Proposition proposition(Provider<Boolean> provider) {
        return new ProviderToProposition(provider);
    }

    /**
     * Adapts a delegate to a predicate.
     *
     * @param <T> the delegate parameter type
     * @param delegate the delegate to be adapted
     * @return the adapted predicate
     */
    public static <T> Predicate<T> predicate(Function<T, Boolean> delegate) {
        return new DelegateToPredicate<T>(delegate);
    }

    /**
     * Adapts a binary delegate to a binary predicate
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param delegate the delegate to be adapted
     * @return the adapted predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> predicate(BinaryDelegate<Boolean, T1, T2> delegate) {
        return new BinaryDelegateToBinaryPredicate<T1, T2>(delegate);
    }

    /**
     * Adapts a ternary delegate to a ternary predicate.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param delegate the delegate to be adapted
     * @return the adapted predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> predicate(TernaryDelegate<Boolean, T1, T2, T3> delegate) {
        return new TernaryDelegateToTernaryPredicate<T1, T2, T3>(delegate);
    }
}
