package net.emaze.dysfunctional;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.MakeOrder;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * max, min, ordered.
 *
 * @author rferranti
 */
public abstract class Comparing {

    /**
     * Evaluates max of two elements.
     *
     * @param <T> the element type
     * @param lhs the left element
     * @param rhs the right element
     * @param comparator the comparator used to compare elements
     * @return the greater element
     */
    public static <T> T max(T lhs, T rhs, Comparator<T> comparator) {
        return BinaryOperator.maxBy(comparator).apply(lhs, rhs);
    }

    /**
     * Evaluates max of two comparable elements.
     *
     * @param <T> the element type
     * @param lhs the left element
     * @param rhs the right element
     * @return the greater element
     */
    public static <T extends Comparable<T>> T max(T lhs, T rhs) {
        return BinaryOperator.maxBy(new ComparableComparator<T>()).apply(lhs, rhs);
    }

    /**
     * Evaluates min of two elements.
     *
     * @param <T> the element type
     * @param lhs the left element
     * @param rhs the right element
     * @param comparator the comparator used to compare elements
     * @return the lesser element
     */
    public static <T> T min(T lhs, T rhs, Comparator<T> comparator) {
        return BinaryOperator.minBy(comparator).apply(lhs, rhs);
    }

    /**
     * Evaluates min of two comparable elements.
     *
     * @param <T> the element type
     * @param lhs the left element
     * @param rhs the right element
     * @return the lesser element
     */
    public static <T extends Comparable<T>> T min(T lhs, T rhs) {
        return BinaryOperator.minBy(new ComparableComparator<T>()).apply(lhs, rhs);
    }

    /**
     * Returns the two elements ordered in a pair.
     *
     * @param <T> the element type
     * @param lhs the left element
     * @param rhs the right element
     * @param comparator the comparator used to compare elements
     * @return the two elements ordered in a pair
     */
    public static <T> Pair<T, T> ordered(T lhs, T rhs, Comparator<T> comparator) {
        return new MakeOrder<T>(comparator).apply(lhs, rhs);
    }

    /**
     * Returns the two comparable elements ordered in a pair.
     *
     * @param <T> the element type
     * @param lhs the left element
     * @param rhs the right element
     * @return the two elements ordered in a pair
     */
    public static <T extends Comparable<T>> Pair<T, T> ordered(T lhs, T rhs) {
        return new MakeOrder<T>(new ComparableComparator<T>()).apply(lhs, rhs);
    }
}
