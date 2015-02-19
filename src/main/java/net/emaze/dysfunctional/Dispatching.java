package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.adapting.*;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import java.util.Optional;

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
    public static <T> Runnable curry(Consumer<T> action, T value) {
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
    public static <T1, T2> Consumer<T2> curry(BiConsumer<T1, T2> action, T1 first) {
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
    public static <T1, T2, T3> BiConsumer<T2, T3> curry(TernaryAction<T1, T2, T3> action, T1 first) {
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
    public static <T1, T2, T3> BiPredicate<T2, T3> curry(TernaryPredicate<T1, T2, T3> predicate, T1 first) {
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
    public static <T, R> Supplier<R> curry(Function<T, R> delegate, T value) {
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
    public static <T1, T2, R> Function<T2, R> curry(BiFunction<T1, T2, R> delegate, T1 first) {
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
    public static <T1, T2, T3, R> BiFunction<T2, T3, R> curry(TernaryDelegate<R, T1, T2, T3> delegate, T1 first) {
        return new BinderFirstOfThree<>(delegate, first);
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
    public static <T1, T2, T3> BiConsumer<T1, T3> mcurry(TernaryAction<T1, T2, T3> action, T2 second) {
        return new ActionBinderSecondOfThree<T1, T2, T3>(action, second);
    }

    /**
     * Partial application of the second (middle) parameter to a ternary
     * delegate.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be curried
     * @param second the value to be curried as second parameter
     * @return the curried binary delegate
     */
    public static <T1, T2, T3, R> BiFunction<T1, T3, R> mcurry(TernaryDelegate<R, T1, T2, T3> delegate, T2 second) {
        return new BinderSecondOfThree<>(delegate, second);
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
    public static <T1, T2, T3> BiPredicate<T1, T3> mcurry(TernaryPredicate<T1, T2, T3> predicate, T2 second) {
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
    public static <T1, T2> Consumer<T1> rcurry(BiConsumer<T1, T2> action, T2 second) {
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
    public static <T1, T2, T3> BiConsumer<T1, T2> rcurry(TernaryAction<T1, T2, T3> action, T3 third) {
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
    public static <T1, T2, T3> BiPredicate<T1, T2> rcurry(TernaryPredicate<T1, T2, T3> predicate, T3 third) {
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
    public static <T1, T2, R> Function<T1, R> rcurry(BiFunction<T1, T2, R> delegate, T2 second) {
        return new BinderSecond<>(delegate, second);
    }

    /**
     * Partial application of the last (rightmost) parameter to a ternary
     * delegate.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be curried
     * @param third the value to be curried as third parameter
     * @return the curried binary delegate
     */
    public static <T1, T2, T3, R> BiFunction<T1, T2, R> rcurry(TernaryDelegate<R, T1, T2, T3> delegate, T3 third) {
        return new BinderThird<>(delegate, third);
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore1st(BiPredicate<T2, T3> predicate, Class<T1> ignored) {
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore2nd(BiPredicate<T1, T3> predicate, Class<T2> ignored) {
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
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> ignore3rd(BiPredicate<T1, T2> predicate, Class<T3> ignored) {
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
    public static <T> Consumer<T> ignore(Runnable runnable, Class<T> ignored) {
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
    public static <T1, T2> BiConsumer<T1, T2> ignore1st(Consumer<T2> action, Class<T1> ignored) {
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
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore1st(BiConsumer<T2, T3> action, Class<T1> ignored) {
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
    public static <T1, T2> BiConsumer<T1, T2> ignore2nd(Consumer<T1> action, Class<T2> ignored) {
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
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore2nd(BiConsumer<T1, T3> action, Class<T2> ignored) {
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
    public static <T1, T2, T3> TernaryAction<T1, T2, T3> ignore3rd(BiConsumer<T1, T2> action, Class<T3> ignored) {
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
    public static <T, R> Function<T, R> ignore(Supplier<R> provider, Class<T> ignored) {
        return new IgnoreParameter<>(provider);
    }

    /**
     * Adapts a delegate to a binary delegate by ignoring the first parameter.
     *
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param <R> the adapted delegate result type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> ignore1st(Function<T2, R> delegate, Class<T1> ignored) {
        return new IgnoreFirst<>(delegate);
    }

    /**
     * Adapts a binary delegate to a ternary delegate by ignoring the first
     * parameter.
     *
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param <T3> the adapted delegate third parameter type
     * @param <R> the adapted delegate result type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <T1, T2, T3, R> TernaryDelegate<R, T1, T2, T3> ignore1st(BiFunction<T2, T3, R> delegate, Class<T1> ignored) {
        return new IgnoreFirstOfThree<>(delegate);
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
    public static <T1, T2, R> BiFunction<T1, T2, R> ignore2nd(Function<T1, R> delegate, Class<T2> ignored) {
        return new IgnoreSecond<>(delegate);
    }

    /**
     * Adapts a binary delegate to a ternary delegate by ignoring the second
     * parameter.
     *
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param <T3> the adapted delegate third parameter type
     * @param <R> the adapted delegate result type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <T1, T2, T3, R> TernaryDelegate<R, T1, T2, T3> ignore2nd(BiFunction<T1, T3, R> delegate, Class<T2> ignored) {
        return new IgnoreSecondOfThree<>(delegate);
    }

    /**
     * Adapts a binary delegate to a ternary delegate by ignoring the third
     * parameter.
     *
     * @param <T1> the adapted delegate first parameter type
     * @param <T2> the adapted delegate second parameter type
     * @param <T3> the adapted delegate third parameter type
     * @param <R> the adapted delegate result type
     * @param delegate the delegate to be adapted
     * @param ignored the adapted delegate ignored parameter type class
     * @return the adapted delegate
     */
    public static <T1, T2, T3, R> TernaryDelegate<R, T1, T2, T3> ignore3rd(BiFunction<T1, T2, R> delegate, Class<T3> ignored) {
        return new IgnoreThird<R, T1, T2, T3>(delegate);
    }

    /**
     * Adapts an iterator to a provider.
     *
     * @param adaptee the runnable to be adapted
     * @return the adapted provider
     */
    public static <T> Supplier<Optional<T>> provider(Iterator<T> adaptee) {
        return new IteratingProvider<T>(adaptee);
    }

    /**
     * Adapts a runnable to a provider.
     *
     * @param adaptee the runnable to be adapted
     * @return the adapted provider
     */
    public static Supplier<Void> provider(Runnable adaptee) {
        return new RunnableToProvider(adaptee);
    }

    /**
     * Adapts a proposition to a provider.
     *
     * @param adaptee the proposition to be adapted
     * @return the adapted provider
     */
    public static Supplier<Boolean> provider(BooleanSupplier adaptee) {
        return new PropositionToProvider(adaptee);
    }

    /**
     * Adapts an action to a delegate.
     *
     * @param <T> the action parameter type
     * @param adaptee the action to be adapted
     * @return the adapted delegate
     */
    public static <T> Function<T, Void> delegate(Consumer<T> adaptee) {
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
    public static <T1, T2> BiFunction<T1, T2, Void> delegate(BiConsumer<T1, T2> adaptee) {
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
    public static <T1, T2> BiFunction<T1, T2, Boolean> delegate(BiPredicate<T1, T2> adaptee) {
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
    public static <T> Runnable runnable(Supplier<T> provider) {
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
    public static <T, R> Consumer<T> action(Function<T, R> delegate) {
        return new DelegateToAction<>(delegate);
    }

    /**
     * Adapts a binary delegate to a binary action.
     *
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <R> the delegate return type
     * @param delegate the delegate to be adapted
     * @return the adapted action
     */
    public static <T1, T2, R> BiConsumer<T1, T2> action(BiFunction<T1, T2, R> delegate) {
        return new BinaryDelegateToBinaryAction<>(delegate);
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
    public static BooleanSupplier proposition(Supplier<Boolean> provider) {
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
    public static <T1, T2> BiPredicate<T1, T2> predicate(BiFunction<T1, T2, Boolean> delegate) {
        return new BinaryDelegateToBinaryPredicate<>(delegate);
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
