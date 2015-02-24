package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.dispatching.TransformingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.TransformingPredicate;
import net.emaze.dysfunctional.dispatching.TransformingProvider;
import net.emaze.dysfunctional.dispatching.TransformingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.delegates.BinaryComposer;
import net.emaze.dysfunctional.dispatching.delegates.Composer;
import net.emaze.dysfunctional.dispatching.delegates.EndoDelegatesComposer;
import net.emaze.dysfunctional.dispatching.delegates.TernaryComposer;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

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
     * @param <T> the delegate and provider parameter type
     * @param <R> the delegate return type
     * @param f the delegate to be composed
     * @param g the provider to be composed
     * @return the composed provider
     */
    public static <T, R> Supplier<R> compose(final Function<T, R> f, final Supplier<T> g) {
        return new TransformingProvider<>(f, g);
    }

    /**
     * Composes a delegate with another delegate.
     *
     * Given f, g yields f ° g (f of g, f following g).
     *
     * @param <T2> f parameter type and g return type
     * @param <T1> g parameter type
     * @param <R> f return type
     * @param f the first delegate to be composed
     * @param g the second delegate to be composed
     * @return the composed delegate
     */
    public static <T2, T1, R> Function<T1, R> compose(Function<T2, R> f, Function<T1, T2> g) {
        return new Composer<>(f, g);
    }

    /**
     * Composes a delegate with a binary delegate.
     *
     * @param <T1> unary parameter type and binary return type
     * @param <T2> the first binary parameter type
     * @param <T3> the second binary parameter type
     * @param <R> unary return type
     * @param unary the delegate to be composed
     * @param binary the binary delegate to be composed
     * @return the composed binary delegate
     */
    public static <T1, T2, T3, R> BiFunction<T2, T3, R> compose(Function<T1, R> unary, BiFunction<T2, T3, T1> binary) {
        return new BinaryComposer<>(unary, binary);
    }

    /**
     * Composes a delegate with a ternary delegate.
     *
     * @param <T1> unary parameter type and ternary return type
     * @param <T2> the first ternary parameter type
     * @param <T3> the second ternary parameter type
     * @param <T4> the third ternary parameter type
     * @param <R> unary return type
     * @param unary the unary delegate to be composed
     * @param ternary the ternary delegate to be composed
     * @return the composed ternary delegate
     */
    public static <T1, T2, T3, T4, R> TriFunction<T1, T2, T3, R> compose(Function<T4, R> unary, TriFunction<T1, T2, T3, T4> ternary) {
        return new TernaryComposer<>(unary, ternary);
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
    public static <R, T3, T2, T1> Function<T1, R> compose(Function<T3, R> f, Function<T2, T3> g, Function<T1, T2> h) {
        return new Composer<>(f, new Composer<>(g, h));
    }

    /**
     * Composes a predicate with a delegate (predicate ° delegate).
     *
     * @param <T> the delegate parameter type
     * @param <R> the predicate parameter type, the delegate result type
     * @param predicate the predicate to be composed
     * @param delegate the delegate to be composed
     * @return the composed predicate
     */
    public static <T, R> Predicate<T> compose(Predicate<R> predicate, Function<T, R> delegate) {
        return new TransformingPredicate<>(predicate, delegate);
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
    public static <R, T2, T1> Predicate<T1> compose(Predicate<R> p, Function<T2, R> f, Function<T1, T2> g) {
        return new TransformingPredicate<>(p, new Composer<>(f, g));
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
    public static <R, T1, T2> BiPredicate<T1, T2> compose(Predicate<R> predicate, BiFunction<T1, T2, R> delegate) {
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
    public static <T1, T2, T3, R> TriPredicate<T1, T2, T3> compose(Predicate<R> predicate, TriFunction<T1, T2, T3, R> delegate) {
        return new TransformingTernaryPredicate<T1, T2, T3, R>(predicate, delegate);
    }

    /**
     * Composes an iterator of endodelegates.
     *
     * @param <T> the delegates parameter and result type
     * @param endodelegates to be composed (e.g: f,g,h)
     * @return a delegate performing f ° g ° h
     */
    public static <T> UnaryOperator<T> compose(Iterator<Function<T, T>> endodelegates) {
        return new EndoDelegatesComposer<T>().apply(endodelegates);
    }
}
