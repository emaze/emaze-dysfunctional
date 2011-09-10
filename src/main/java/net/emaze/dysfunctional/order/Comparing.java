package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public abstract class Comparing {

    public static <T extends Comparable<T>> boolean lhsIsLesser(T lhs, T rhs) {
        dbc.precondition(lhs != null, "cannot call lhsIsLesser with a null lhs");
        return Order.from(lhs.compareTo(rhs)) == Order.LT;
    }

    public static <T> boolean lhsIsLesser(T lhs, T rhs, Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot call lhsIsLesser with a null comparator");
        return Order.from(comparator.compare(lhs, rhs)) == Order.LT;
    }

    public static <T> boolean lhsIsLesserThanEquals(T lhs, T rhs, Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot call lhsIsLesserThanEquals with a null comparator");
        return Order.from(comparator.compare(lhs, rhs)).isLte();
    }

    public static <T extends Comparable<T>> boolean lhsIsLesserThanEquals(T lhs, T rhs) {
        dbc.precondition(lhs != null, "cannot call lhsIsLesserThanEquals with a null lhs");
        return Order.from(lhs.compareTo(rhs)).isLte();
    }

    public static <T extends Comparable<T>> boolean lhsIsGreater(T lhs, T rhs) {
        dbc.precondition(lhs != null, "cannot call lhsIsGreater with a null lhs");
        return Order.from(lhs.compareTo(rhs)) == Order.GT;
    }

    public static <T> boolean lhsIsGreater(T lhs, T rhs, Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot call lhsIsGreater with a null comparator");
        return Order.from(comparator.compare(lhs, rhs)) == Order.GT;
    }

    public static <T> boolean lhsIsGreaterThanEquals(T lhs, T rhs, Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot call lhsIsGreaterThanEquals with a null comparator");
        return Order.from(comparator.compare(lhs, rhs)).isGte();
    }

    public static <T extends Comparable<T>> boolean lhsIsGreaterThanEquals(T lhs, T rhs) {
        dbc.precondition(lhs != null, "cannot call lhsIsGreaterThanEquals with a null lhs");
        return Order.from(lhs.compareTo(rhs)).isGte();
    }

    public static <T> boolean sameOrder(T lhs, T rhs, Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot call sameOrder with a null comparator");
        return Order.from(comparator.compare(lhs, rhs)) == Order.EQ;
    }

    public static <T extends Comparable<T>> boolean sameOrder(T lhs, T rhs) {
        dbc.precondition(lhs != null, "cannot call sameOrder with a null lhs");
        return Order.from(lhs.compareTo(rhs)) == Order.EQ;
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
