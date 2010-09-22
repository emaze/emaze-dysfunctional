package net.emaze.dysfunctional.order;

import java.util.Comparator;

/**
 *
 * @author rferranti
 */
public class Comparing {

    public static final int LHS_IS_LESSER = -1;
    public static final int LHS_IS_GREATER = 1;
    public static final int SAME_ORDER = 0;

    public static boolean lhsIsLesser(Comparable lhs, Comparable rhs) {
        return lhs.compareTo(rhs) == LHS_IS_LESSER;
    }

    public static <T> boolean lhsIsLesser(T lhs, T rhs, Comparator<T> comparator) {
        return comparator.compare(lhs, rhs) == LHS_IS_LESSER;
    }

    public static boolean lhsIsGreater(Comparable lhs, Comparable rhs) {
        return lhs.compareTo(rhs) == LHS_IS_GREATER;
    }

    public static <T> boolean lhsIsGreater(T lhs, T rhs, Comparator<T> comparator) {
        return comparator.compare(lhs, rhs) == LHS_IS_GREATER;

    }
    public static <T> boolean sameOrder(T lhs, T rhs, Comparator<T> comparator) {
        return comparator.compare(lhs, rhs) == SAME_ORDER;

    }
    /* ... */
}
