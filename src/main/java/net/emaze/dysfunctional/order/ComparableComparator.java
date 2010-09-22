package net.emaze.dysfunctional.order;

import java.util.Comparator;

/**
 * Redispatching comparator for Comparables
 * @author rferranti
 */
public class ComparableComparator<T extends Comparable> implements Comparator<T>{

    @Override
    public int compare(T lhs, T rhs) {
        return lhs.compareTo(rhs);
    }

}
