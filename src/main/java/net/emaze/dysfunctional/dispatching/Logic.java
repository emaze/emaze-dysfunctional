package net.emaze.dysfunctional.dispatching;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
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
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Negator;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryNegator;
import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 *
 * @author rferranti
 */
public abstract class Logic {
    
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
        return Logic.and(Consumers.all(predicates));
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
        return Logic.and(Arrays.asList(first, second));
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
        return Logic.and(Arrays.asList(first, second, third));
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
        return Logic.and2(Consumers.all(predicates));
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
        return Logic.and2(Arrays.asList(first, second));
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
        return Logic.and2(Arrays.asList(first, second, third));
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
        return Logic.and3(Consumers.all(predicates));
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
        return Logic.and3(Arrays.asList(first, second));
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
        return Logic.and3(Arrays.asList(first, second, third));
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
        return Logic.or(Consumers.all(predicates));
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
        return Logic.or(Arrays.asList(first, second));
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
        return Logic.or(Arrays.asList(first, second, third));
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
        return Logic.or2(Consumers.all(predicates));
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
        return Logic.or2(Arrays.asList(first, second));
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
        return Logic.or2(Arrays.asList(first, second, third));
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
        return Logic.or3(Consumers.all(predicates));
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
        return Logic.or3(Arrays.asList(first, second));
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
        return Logic.or3(Arrays.asList(first, second, third));
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
