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
import net.emaze.dysfunctional.dispatching.TransformingSupplier;
import net.emaze.dysfunctional.dispatching.TransformingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.delegates.BinaryComposer;
import net.emaze.dysfunctional.dispatching.delegates.Composer;
import net.emaze.dysfunctional.dispatching.delegates.UnaryOperatorsComposer;
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
     * Composes a function with a supplier (function ° supplier).
     *
     * Given f, g yields f ° g (f of g, f following g).
     *
     * @param <T> the function and supplier parameter type
     * @param <R> the function return type
     * @param f the function to be composed
     * @param g the supplier to be composed
     * @return the composed supplier
     */
    public static <T, R> Supplier<R> compose(final Function<T, R> f, final Supplier<T> g) {
        return new TransformingSupplier<>(f, g);
    }

    /**
     * Composes a function with another function.
     *
     * Given f, g yields f ° g (f of g, f following g).
     *
     * @param <T2> f parameter type and g return type
     * @param <T1> g parameter type
     * @param <R> f return type
     * @param f the first function to be composed
     * @param g the second function to be composed
     * @return the composed function
     */
    public static <T2, T1, R> Function<T1, R> compose(Function<T2, R> f, Function<T1, T2> g) {
        return new Composer<>(f, g);
    }

    /**
     * Composes a function with a binary function.
     *
     * @param <T1> unary parameter type and binary return type
     * @param <T2> the first binary parameter type
     * @param <T3> the second binary parameter type
     * @param <R> unary return type
     * @param unary the function to be composed
     * @param binary the binary function to be composed
     * @return the composed binary function
     */
    public static <T1, T2, T3, R> BiFunction<T2, T3, R> compose(Function<T1, R> unary, BiFunction<T2, T3, T1> binary) {
        return new BinaryComposer<>(unary, binary);
    }

    /**
     * Composes a function with a ternary function.
     *
     * @param <T1> unary parameter type and ternary return type
     * @param <T2> the first ternary parameter type
     * @param <T3> the second ternary parameter type
     * @param <T4> the third ternary parameter type
     * @param <R> unary return type
     * @param unary the unary function to be composed
     * @param ternary the ternary function to be composed
     * @return the composed ternary function
     */
    public static <T1, T2, T3, T4, R> TriFunction<T1, T2, T3, R> compose(Function<T4, R> unary, TriFunction<T1, T2, T3, T4> ternary) {
        return new TernaryComposer<>(unary, ternary);
    }

    /**
     * Composes three delegates.
     *
     * Given f, g, h yields f ° g ° h (f of g of h, f following g following h).
     *
     * @param <R> the first function result type
     * @param <T3> the first function parameter type, the second function result
     * type
     * @param <T2> the second function parameter type, the third function result
     * type
     * @param <T1> the third function parameter type
     * @param f the first function to be composed
     * @param g the second function to be composed
     * @param h the third function to be composed
     * @return the composed function
     */
    public static <R, T3, T2, T1> Function<T1, R> compose(Function<T3, R> f, Function<T2, T3> g, Function<T1, T2> h) {
        return new Composer<>(f, new Composer<>(g, h));
    }

    /**
     * Composes a predicate with a function (predicate ° function).
     *
     * @param <T> the function parameter type
     * @param <R> the predicate parameter type, the function result type
     * @param predicate the predicate to be composed
     * @param function the function to be composed
     * @return the composed predicate
     */
    public static <T, R> Predicate<T> compose(Predicate<R> predicate, Function<T, R> function) {
        return new TransformingPredicate<>(predicate, function);
    }

    /**
     * Composes a predicate and two delegates, (predicate ° delegate1 °
     * delegate2).
     *
     * @param <R> the predicate parameter type, the first function result type
     * @param <T2> the first function parameter type, the second function result
     * type
     * @param <T1> the third function parameter type
     * @param p the predicate to be composed
     * @param f the first function to be composed
     * @param g the second function to be composed
     * @return the composed predicate
     */
    public static <R, T2, T1> Predicate<T1> compose(Predicate<R> p, Function<T2, R> f, Function<T1, T2> g) {
        return new TransformingPredicate<>(p, new Composer<>(f, g));
    }

    /**
     * Composes a predicate with a binary function (predicate ° function).
     *
     * @param <R> the predicate parameter type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param predicate the predicate to be composed
     * @param function the function to be composed
     * @return the composed predicate
     */
    public static <R, T1, T2> BiPredicate<T1, T2> compose(Predicate<R> predicate, BiFunction<T1, T2, R> function) {
        return new TransformingBinaryPredicate<R, T1, T2>(predicate, function);
    }

    /**
     * Composes a predicate with a ternary function (predicate ° function).
     *
     * @param <R> the predicate parameter type
     * @param <T1> the function first parameter type
     * @param <T2> the function second parameter type
     * @param <T3> the function third parameter type
     * @param predicate the predicate to be composed
     * @param function the function to be composed
     * @return the composed predicate
     */
    public static <T1, T2, T3, R> TriPredicate<T1, T2, T3> compose(Predicate<R> predicate, TriFunction<T1, T2, T3, R> function) {
        return new TransformingTernaryPredicate<T1, T2, T3, R>(predicate, function);
    }

    /**
     * Composes an iterator of endodelegates.
     *
     * @param <T> the delegates parameter and result type
     * @param endodelegates to be composed (e.g: f,g,h)
     * @return a function performing f ° g ° h
     */
    public static <T> UnaryOperator<T> compose(Iterator<Function<T, T>> endodelegates) {
        return new UnaryOperatorsComposer<T>().apply(endodelegates);
    }
}
