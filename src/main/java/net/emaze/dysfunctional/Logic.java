package net.emaze.dysfunctional;

import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.composing.AllMatchingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.composing.AllMatchingPredicate;
import net.emaze.dysfunctional.dispatching.composing.AllMatchingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.composing.FirstMatchingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.composing.FirstMatchingPredicate;
import net.emaze.dysfunctional.dispatching.composing.FirstMatchingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.*;

/**
 * and, or, not, always, never.
 *
 * @author rferranti
 */
public abstract class Logic {

    /**
     * and, or, not, always, never.
     */
    public abstract static class Unary {

        /**
         * Creates a composite AND predicate from the given predicates.
         *
         * @param <T> the element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T> Predicate<T> and(Iterable<Predicate<T>> predicates) {
            return new AllMatchingPredicate<T>(predicates);
        }

        /**
         * Creates a composite AND predicate from the given predicates.
         *
         * @param <T> the element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T> Predicate<T> and(Iterator<Predicate<T>> predicates) {
            return new AllMatchingPredicate<T>(Consumers.all(predicates));
        }

        /**
         * Creates a composite OR predicate from the given predicates.
         *
         * @param <T> the element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T> Predicate<T> or(Iterable<Predicate<T>> predicates) {
            return new FirstMatchingPredicate<T>(predicates);
        }

        /**
         * Creates a composite OR predicate from the given predicates.
         *
         * @param <T> the element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T> Predicate<T> or(Iterator<Predicate<T>> predicates) {
            return new FirstMatchingPredicate<T>(Consumers.all(predicates));
        }

        /**
         * Creates a predicate always yielding true.
         *
         * @param <T> the element type parameter
         * @return the predicate
         */
        public static <T> Predicate<T> always() {
            return new Always<T>();
        }

        /**
         * Creates a predicate always yielding false.
         *
         * @param <T> the element type parameter
         * @return the predicate
         */
        public static <T> Predicate<T> never() {
            return new Never<T>();
        }
    }

    /**
     * and, or, not, always, never.
     */
    public abstract static class Binary {

        /**
         * Creates a composite AND predicate from the given predicates.
         *
         * @param <T1> the former element type parameter
         * @param <T2> the latter element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T1, T2> BiPredicate<T1, T2> and(Iterable<BiPredicate<T1, T2>> predicates) {
            return new AllMatchingBinaryPredicate<T1, T2>(predicates);
        }

        /**
         * Creates a composite AND predicate from the given predicates.
         *
         * @param <T1> the former element type parameter
         * @param <T2> the latter element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T1, T2> BiPredicate<T1, T2> and(Iterator<BiPredicate<T1, T2>> predicates) {
            return new AllMatchingBinaryPredicate<T1, T2>(Consumers.all(predicates));
        }

        /**
         * Creates a composite OR predicate from the given predicates.
         *
         * @param <T1> the former element type parameter
         * @param <T2> the latter element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T1, T2> BiPredicate<T1, T2> or(Iterable<BiPredicate<T1, T2>> predicates) {
            return new FirstMatchingBinaryPredicate<T1, T2>(predicates);
        }

        /**
         * Creates a composite OR predicate from the given predicates.
         *
         * @param <T1> the former element type parameter
         * @param <T2> the latter element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T1, T2> BiPredicate<T1, T2> or(Iterator<BiPredicate<T1, T2>> predicates) {
            return new FirstMatchingBinaryPredicate<T1, T2>(Consumers.all(predicates));
        }

        /**
         * Creates a predicate always yielding true.
         *
         * @param <T1> the first element type parameter
         * @param <T2> the second element type parameter
         * @return the predicate
         */
        public static <T1, T2> BiPredicate<T1, T2> always() {
            return new BinaryAlways<T1, T2>();
        }

        /**
         * Creates a predicate always yielding false.
         *
         * @param <T1> the first element type parameter
         * @param <T2> the second element type parameter
         * @return the predicate
         */
        public static <T1, T2> BiPredicate<T1, T2> never() {
            return new BinaryNever<T1, T2>();
        }
    }

    /**
     * and, or, not, always, never.
     */
    public abstract static class Ternary {

        /**
         * Creates a composite AND predicate from the given predicates.
         *
         * @param <T1> the first element type parameter
         * @param <T2> the second element type parameter
         * @param <T3> the third element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T1, T2, T3> TriPredicate<T1, T2, T3> and(Iterable<TriPredicate<T1, T2, T3>> predicates) {
            return new AllMatchingTernaryPredicate<T1, T2, T3>(predicates);
        }

        /**
         * Creates a composite AND predicate from the given predicates.
         *
         * @param <T1> the first element type parameter
         * @param <T2> the second element type parameter
         * @param <T3> the third element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T1, T2, T3> TriPredicate<T1, T2, T3> and(Iterator<TriPredicate<T1, T2, T3>> predicates) {
            return new AllMatchingTernaryPredicate<T1, T2, T3>(Consumers.all(predicates));
        }

        /**
         * Creates a composite OR predicate from the given predicates.
         *
         * @param <T1> the first element type parameter
         * @param <T2> the second element type parameter
         * @param <T3> the third element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T1, T2, T3> TriPredicate<T1, T2, T3> or(Iterable<TriPredicate<T1, T2, T3>> predicates) {
            return new FirstMatchingTernaryPredicate<T1, T2, T3>(predicates);
        }

        /**
         * Creates a composite OR predicate from the given predicates.
         *
         * @param <T1> the first element type parameter
         * @param <T2> the second element type parameter
         * @param <T3> the third element type parameter
         * @param predicates the predicates to be composed
         * @return the composite predicate
         */
        public static <T1, T2, T3> TriPredicate<T1, T2, T3> or(Iterator<TriPredicate<T1, T2, T3>> predicates) {
            return new FirstMatchingTernaryPredicate<T1, T2, T3>(Consumers.all(predicates));
        }

        /**
         * Creates a predicate always yielding true.
         *
         * @param <T1> the first element type parameter
         * @param <T2> the second element type parameter
         * @param <T3> the third element type parameter
         * @return the predicate
         */
        public static <T1, T2, T3> TriPredicate<T1, T2, T3> always() {
            return new TernaryAlways<T1, T2, T3>();
        }

        /**
         * Creates a predicate always yielding false.
         *
         * @param <T1> the first element type parameter
         * @param <T2> the second element type parameter
         * @param <T3> the third element type parameter
         * @return the predicate
         */
        public static <T1, T2, T3> TriPredicate<T1, T2, T3> never() {
            return new TernaryNever<T1, T2, T3>();
        }
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
     * @param <T> the element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T> Predicate<T> and(Predicate<T> first, Predicate<T> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Logic.Unary.and(Iterations.iterable(first, second));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
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
        return Logic.Unary.and(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
     * @param <T> the element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T> Predicate<T> and(Predicate<T>... predicates) {
        dbc.precondition(predicates != null, "predicates is null");
        return Logic.Unary.and(Iterations.iterable(predicates));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> and(BiPredicate<T1, T2> first, BiPredicate<T1, T2> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Logic.Binary.and(Iterations.iterable(first, second));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param first
     * @param second
     * @param third
     * @return the composite predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> and(BiPredicate<T1, T2> first, BiPredicate<T1, T2> second, BiPredicate<T1, T2> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Logic.Binary.and(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param predicates
     * @return the composite predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> and(BiPredicate<T1, T2>... predicates) {
        dbc.precondition(predicates != null, "predicates is null");
        return Logic.Binary.and(Iterations.iterable(predicates));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> and(TriPredicate<T1, T2, T3> first, TriPredicate<T1, T2, T3> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Logic.Ternary.and(Iterations.iterable(first, second));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param first
     * @param second
     * @param third
     * @return the composite predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> and(TriPredicate<T1, T2, T3> first, TriPredicate<T1, T2, T3> second, TriPredicate<T1, T2, T3> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Logic.Ternary.and(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     *
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicates
     * @return the composite predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> and(TriPredicate<T1, T2, T3>... predicates) {
        dbc.precondition(predicates != null, "predicates is null");
        return Logic.Ternary.and(Iterations.iterable(predicates));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
     * @param <T> the element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T> Predicate<T> or(Predicate<T> first, Predicate<T> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Logic.Unary.or(Iterations.iterable(first, second));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
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
        return Logic.Unary.or(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
     * @param <T> the element type parameter
     * @param predicates
     * @return the composite predicate
     */
    public static <T> Predicate<T> or(Predicate<T>... predicates) {
        dbc.precondition(predicates != null, "first predicate is null");
        return Logic.Unary.or(Iterations.iterable(predicates));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> or(BiPredicate<T1, T2> first, BiPredicate<T1, T2> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Logic.Binary.or(Iterations.iterable(first, second));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param first
     * @param second
     * @param third
     * @return the composite predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> or(BiPredicate<T1, T2> first, BiPredicate<T1, T2> second, BiPredicate<T1, T2> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Logic.Binary.or(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param predicates
     * @return the composite predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> or(BiPredicate<T1, T2>... predicates) {
        dbc.precondition(predicates != null, "predicates is null");
        return Logic.Binary.or(Iterations.iterable(predicates));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param first
     * @param second
     * @return the composite predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> or(TriPredicate<T1, T2, T3> first, TriPredicate<T1, T2, T3> second) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        return Logic.Ternary.or(Iterations.iterable(first, second));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param first
     * @param second
     * @param third
     * @return the composite predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> or(TriPredicate<T1, T2, T3> first, TriPredicate<T1, T2, T3> second, TriPredicate<T1, T2, T3> third) {
        dbc.precondition(first != null, "first predicate is null");
        dbc.precondition(second != null, "second predicate is null");
        dbc.precondition(third != null, "third predicate is null");
        return Logic.Ternary.or(Iterations.iterable(first, second, third));
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     *
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicates
     * @return the composite predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> or(TriPredicate<T1, T2, T3>... predicates) {
        dbc.precondition(predicates != null, "predicates is null");
        return Logic.Ternary.or(Iterations.iterable(predicates));
    }

    /**
     * Negates the given predicate.
     *
     * @param <T> the element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return predicate.negate();
    }

    /**
     * Negates the given predicate.
     *
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T1, T2> BiPredicate<T1, T2> not(BiPredicate<T1, T2> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return new BinaryNegator<T1, T2>(predicate);
    }

    /**
     * Negates the given predicate.
     *
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T1, T2, T3> TriPredicate<T1, T2, T3> not(TriPredicate<T1, T2, T3> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return predicate.negate();
    }
}
