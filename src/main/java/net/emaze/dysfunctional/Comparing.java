package net.emaze.dysfunctional;

import java.util.Comparator;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.MakeOrder;
import net.emaze.dysfunctional.order.Max;
import net.emaze.dysfunctional.order.Min;
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
        return new Max<T>(comparator).perform(lhs, rhs);
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
        return new Max<T>(new ComparableComparator<T>()).perform(lhs, rhs);
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
        return new Min<T>(comparator).perform(lhs, rhs);
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
        return new Min<T>(new ComparableComparator<T>()).perform(lhs, rhs);
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
        return new MakeOrder<T>(comparator).perform(lhs, rhs);
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
        return new MakeOrder<T>(new ComparableComparator<T>()).perform(lhs, rhs);
    }
}
