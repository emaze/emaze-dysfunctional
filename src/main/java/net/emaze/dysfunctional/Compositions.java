package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.TransformingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.TransformingPredicate;
import net.emaze.dysfunctional.dispatching.TransformingProvider;
import net.emaze.dysfunctional.dispatching.TransformingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Composer;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.EndoDelegatesComposer;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

public abstract class Compositions {

    /**
     * Given f, g yields f ° g (f of g, f following g)
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
     * Given f, g yields f ° g (f of g, f following g)
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
     * Given f, g, h yields f ° g ° h (f of g of h, f following g following h)
     *
     * @param <R>
     * @param <T3>
     * @param <T2>
     * @param <T1>
     * @param f the first delegate to be composed
     * @param g the second delegate to be composed
     * @param h the third delegate to be composed
     * @return the composed delegate
     */
    public static <R, T3, T2, T1> Delegate<R, T1> compose(Delegate<R, T3> f, Delegate<T3, T2> g, Delegate<T2, T1> h) {
        return compose(f, compose(g, h));
    }

    /**
     * Composes a predicate with a delegate (predicate ° delegate).
     *
     * @param <R>
     * @param <T>
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
     * @param <R>
     * @param <T2>
     * @param <T1>
     * @param p the predicate to be composed
     * @param f the first delegate to be composed
     * @param g the second delegate to be composed
     * @return the composed predicate
     */
    public static <R, T2, T1> Predicate<T1> compose(Predicate<R> p, Delegate<R, T2> f, Delegate<T2, T1> g) {
        return compose(p, compose(f, g));
    }

    /**
     * Composes a predicate with a binary delegate (predicate ° delegate).
     *
     * @param <R>
     * @param <T1>
     * @param <T2>
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
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param <T3>
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
     * @param endodelegates to be composed (eg. f,g,h)
     * @return a delegate performing f ° g ° h
     */
    public static <T> Delegate<T, T> compose(Iterator<Delegate<T, T>> endodelegates) {
        return new EndoDelegatesComposer<T>().perform(endodelegates);
    }
}
