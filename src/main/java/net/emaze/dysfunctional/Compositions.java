package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.TransformingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.TransformingPredicate;
import net.emaze.dysfunctional.dispatching.TransformingProvider;
import net.emaze.dysfunctional.dispatching.TransformingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.delegates.BinaryComposer;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Composer;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.EndoDelegatesComposer;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.delegates.TernaryComposer;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * compose
 *
 * @author rferranti
 */
public abstract class Compositions {

    /**
     * Composes a delegate with a provider (delegate ° provider).
     *
     * Given f, g yields f ° g (f of g, f following g).
     *
     * @param <R> the delegate return type
     * @param <T> the delegate and provider parameter type
     * @param f the delegate to be composed
     * @param g the provider to be composed
     * @return the composed provider
     */
    public static <R, T> Provider<R> compose(final Delegate<R, T> f, final Provider<T> g) {
        return new TransformingProvider<R, T>(f, g);
    }

    /**
     * Composes a delegate with another delegate.
     *
     * Given f, g yields f ° g (f of g, f following g).
     *
     * @param <R> f return type
     * @param <T2> f parameter type and g return type
     * @param <T1> g parameter type
     * @param f the first delegate to be composed
     * @param g the second delegate to be composed
     * @return the composed delegate
     */
    public static <R, T2, T1> Delegate<R, T1> compose(Delegate<R, T2> f, Delegate<T2, T1> g) {
        return new Composer<R, T2, T1>(f, g);
    }

    /**
     * Composes a delegate with a binary delegate.
     *
     * @param <R> unary return type
     * @param <T1> unary parameter type and binary return type
     * @param <T2> the first binary parameter type
     * @param <T3> the second binary parameter type
     * @param unary the delegate to be composed
     * @param binary the binary delegate to be composed
     * @return the composed binary delegate
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T2, T3> compose(Delegate<R, T1> unary, BinaryDelegate<T1, T2, T3> binary) {
        return new BinaryComposer<R, T1, T2, T3>(unary, binary);
    }

    /**
     * Composes a delegate with a ternary delegate.
     *
     * @param <R> unary return type
     * @param <T1> unary parameter type and ternary return type
     * @param <T2> the first ternary parameter type
     * @param <T3> the second ternary parameter type
     * @param <T4> the third ternary parameter type
     * @param unary the unary delegate to be composed
     * @param ternary the ternary delegate to be composed
     */
    public static <R, T1, T2, T3, T4> TernaryDelegate<R, T2, T3, T4> compose(Delegate<R, T1> unary, TernaryDelegate<T1, T2, T3, T4> ternary) {
        return new TernaryComposer<R, T1, T2, T3, T4>(unary, ternary);
    }

    /**
     * Composes three delegates.
     *
     * Given f, g, h yields f ° g ° h (f of g of h, f following g following h).
     *
     * @param <R> the first delegate result type
     * @param <T3> the first delegate parameter type, the second delegate result
     * type
     * @param <T2> the second delegate parameter type, the third delegate result
     * type
     * @param <T1> the third delegate parameter type
     * @param f the first delegate to be composed
     * @param g the second delegate to be composed
     * @param h the third delegate to be composed
     * @return the composed delegate
     */
    public static <R, T3, T2, T1> Delegate<R, T1> compose(Delegate<R, T3> f, Delegate<T3, T2> g, Delegate<T2, T1> h) {
        return new Composer<R, T3, T1>(f, new Composer<T3, T2, T1>(g, h));
    }

    /**
     * Composes a predicate with a delegate (predicate ° delegate).
     *
     * @param <R> the predicate parameter type, the delegate result type
     * @param <T> the delegate parameter type
     * @param predicate the predicate to be composed
     * @param delegate the delegate to be composed
     * @return the composed predicate
     */
    public static <R, T> Predicate<T> compose(Predicate<R> predicate, Delegate<R, T> delegate) {
        return new TransformingPredicate<R, T>(predicate, delegate);
    }

    /**
     * Composes a predicate and two delegates, (predicate ° delegate1 °
     * delegate2).
     *
     * @param <R> the predicate parameter type, the first delegate result type
     * @param <T2> the first delegate parameter type, the second delegate result
     * type
     * @param <T1> the third delegate parameter type
     * @param p the predicate to be composed
     * @param f the first delegate to be composed
     * @param g the second delegate to be composed
     * @return the composed predicate
     */
    public static <R, T2, T1> Predicate<T1> compose(Predicate<R> p, Delegate<R, T2> f, Delegate<T2, T1> g) {
        return new TransformingPredicate<R, T1>(p, new Composer<R, T2, T1>(f, g));
    }

    /**
     * Composes a predicate with a binary delegate (predicate ° delegate).
     *
     * @param <R> the predicate parameter type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param predicate the predicate to be composed
     * @param delegate the delegate to be composed
     * @return the composed predicate
     */
    public static <R, T1, T2> BinaryPredicate<T1, T2> compose(Predicate<R> predicate, BinaryDelegate<R, T1, T2> delegate) {
        return new TransformingBinaryPredicate<R, T1, T2>(predicate, delegate);
    }

    /**
     * Composes a predicate with a ternary delegate (predicate ° delegate).
     *
     * @param <R> the predicate parameter type
     * @param <T1> the delegate first parameter type
     * @param <T2> the delegate second parameter type
     * @param <T3> the delegate third parameter type
     * @param predicate the predicate to be composed
     * @param delegate the delegate to be composed
     * @return the composed predicate
     */
    public static <R, T1, T2, T3> TernaryPredicate<T1, T2, T3> compose(Predicate<R> predicate, TernaryDelegate<R, T1, T2, T3> delegate) {
        return new TransformingTernaryPredicate<R, T1, T2, T3>(predicate, delegate);
    }

    /**
     * Composes an iterator of endodelegates.
     *
     * @param <T> the delegates parameter and result type
     * @param endodelegates to be composed (e.g: f,g,h)
     * @return a delegate performing f ° g ° h
     */
    public static <T> Delegate<T, T> compose(Iterator<Delegate<T, T>> endodelegates) {
        return new EndoDelegatesComposer<T>().perform(endodelegates);
    }
}
