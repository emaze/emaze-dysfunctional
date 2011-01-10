package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public abstract class Comparing {

    public static <T extends Comparable<T>> boolean lhsIsLesser(T lhs, T rhs) {
        return lhs.compareTo(rhs) == Order.LHS_IS_LESSER;
    }

    public static <T> boolean lhsIsLesser(T lhs, T rhs, Comparator<T> comparator) {
        return comparator.compare(lhs, rhs) == Order.LHS_IS_LESSER;
    }

    public static <T> boolean lhsIsLesserThanEquals(T lhs, T rhs, Comparator<T> comparator) {
        final int result = comparator.compare(lhs, rhs);
        return result == Order.LHS_IS_LESSER || result == Order.SAME_ORDER;
    }

    public static <T extends Comparable<T>> boolean lhsIsLesserThanEquals(T lhs, T rhs) {
        final int result = lhs.compareTo(rhs);
        return result == Order.LHS_IS_LESSER || result == Order.SAME_ORDER;
    }

    public static <T extends Comparable<T>> boolean lhsIsGreater(T lhs, T rhs) {
        return lhs.compareTo(rhs) == Order.LHS_IS_GREATER;
    }

    public static <T> boolean lhsIsGreater(T lhs, T rhs, Comparator<T> comparator) {
        return comparator.compare(lhs, rhs) == Order.LHS_IS_GREATER;
    }

    public static <T> boolean lhsIsGreaterThanEquals(T lhs, T rhs, Comparator<T> comparator) {
        final int result = comparator.compare(lhs, rhs);
        return result == Order.LHS_IS_GREATER || result == Order.SAME_ORDER;
    }

    public static <T extends Comparable<T>> boolean lhsIsGreaterThanEquals(T lhs, T rhs) {
        final int result = lhs.compareTo(rhs);
        return result == Order.LHS_IS_GREATER || result == Order.SAME_ORDER;
    }

    public static <T> boolean sameOrder(T lhs, T rhs, Comparator<T> comparator) {
        return comparator.compare(lhs, rhs) == Order.SAME_ORDER;
    }

    public static <T extends Comparable<T>> boolean sameOrder(T lhs, T rhs) {
        return lhs.compareTo(rhs) == Order.SAME_ORDER;
    }

    public static <T> T max(T lhs, T rhs, Comparator<T> comparator) {
        return new Max<T>(comparator).perform(lhs, rhs);
    }

    public static <T> T min(T lhs, T rhs, Comparator<T> comparator) {
        return new Min<T>(comparator).perform(lhs, rhs);
    }

    public static <T> Pair<T, T> ordered(T lhs, T rhs, Comparator<T> comparator) {
        return new MakeOrder<T>(comparator).perform(lhs, rhs);
    }
}
