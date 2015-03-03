package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.UnaryOperatorsComposer;
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
    public static <T, R> Supplier<R> compose(Function<T, R> f, Supplier<T> g) {
        dbc.precondition(f != null, "cannot compose supplier with a null function");
        dbc.precondition(g != null, "cannot compose function with a null supplier");
        return () -> f.apply(g.get());
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
        dbc.precondition(f != null, "cannot compose a null function");
        dbc.precondition(g != null, "cannot compose a null function");
        return f.compose(g);
    }

    /**
     * Composes three functions.
     *
     * Given f, g, h yields f ° g ° h (f of g of h, f following g following h).
     *
     * @param <T1> the third function parameter type
     * @param <T2> the second function parameter type, the third function result type
     * @param <T3> the first function parameter type, the second function result type
     * @param <R> the first function result type
     * @param f the first function to be composed
     * @param g the second function to be composed
     * @param h the third function to be composed
     * @return the composed function
     */
    public static <T1, T2, T3, R> Function<T1, R> compose(Function<T3, R> f, Function<T2, T3> g, Function<T1, T2> h) {
        dbc.precondition(f != null, "cannot compose a null function");
        dbc.precondition(g != null, "cannot compose a null function");
        dbc.precondition(h != null, "cannot compose a null function");
        return f.compose(g.compose(h));
    }

    /**
     * Composes a function with a binary function.
     *
     * @param <T1> the first binary parameter type
     * @param <T2> the second binary parameter type
     * @param <T3> unary parameter type and binary return type
     * @param <R> unary return type
     * @param unary the function to be composed
     * @param binary the binary function to be composed
     * @return the composed binary function
     */
    public static <T1, T2, T3, R> BiFunction<T1, T2, R> compose(Function<T3, R> unary, BiFunction<T1, T2, T3> binary) {
        dbc.precondition(unary != null, "cannot compose a null unary function");
        dbc.precondition(binary != null, "cannot compose a null binary function");
        return binary.andThen(unary);
    }

    /**
     * Composes a function with a ternary function.
     *
     * @param <T1> the first ternary parameter type
     * @param <T2> the second ternary parameter type
     * @param <T3> the third ternary parameter type
     * @param <T4> unary parameter type and ternary return type
     * @param <R> unary return type
     * @param unary the unary function to be composed
     * @param ternary the ternary function to be composed
     * @return the composed ternary function
     */
    public static <T1, T2, T3, T4, R> TriFunction<T1, T2, T3, R> compose(Function<T4, R> unary, TriFunction<T1, T2, T3, T4> ternary) {
        dbc.precondition(unary != null, "cannot compose a null unary function");
        dbc.precondition(ternary != null, "cannot compose a null ternary function");
        return ternary.andThen(unary);
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
        dbc.precondition(predicate != null, "cannot compose function with a null predicate");
        dbc.precondition(function != null, "cannot compose predicate with a null function");
        return t -> predicate.test(function.apply(t));
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
        dbc.precondition(predicate != null, "cannot compose function with a null predicate");
        dbc.precondition(function != null, "cannot compose predicate with a null binary function");
        return (t1, t2) -> predicate.test(function.apply(t1, t2));
    }

    /**
     * Composes a predicate with a ternary function (predicate ° function).
     *
     * @param <T1> the function first type
     * @param <T2> the function second type
     * @param <T3> the function third type
     * @param <R> the function result type and the predicate type
     * @param predicate the predicate to be composed
     * @param function the function to be composed
     * @return the composed predicate
     */
    public static <T1, T2, T3, R> TriPredicate<T1, T2, T3> compose(Predicate<R> predicate, TriFunction<T1, T2, T3, R> function) {
        dbc.precondition(predicate != null, "cannot compose function with a null predicate");
        dbc.precondition(function != null, "cannot compose predicate with a null ternary function");
        return (t1, t2, t3) -> predicate.test(function.apply(t1, t2, t3));
    }

    /**
     * Composes an iterator of endofunctions.
     *
     * @param <T> the functions parameter and result type
     * @param endodelegates to be composed (e.g: f,g,h)
     * @return a function performing f ° g ° h
     */
    public static <T> UnaryOperator<T> compose(Iterator<Function<T, T>> endodelegates) {
        return new UnaryOperatorsComposer<T>().apply(endodelegates);
    }
}
