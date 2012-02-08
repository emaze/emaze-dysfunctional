package net.emaze.dysfunctional.order;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Redispatching comparator for comparables.
 *
 * @param <T> the comparable type
 * @author rferranti
 */
public class ComparableComparator<T extends Comparable<T>> implements Comparator<T>, Serializable {

    private static final long serialVersionUID = 1l;

    @Override
    public int compare(T lhs, T rhs) {
        return lhs.compareTo(rhs);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ComparableComparator;
    }

    @Override
    public int hashCode() {
        return ComparableComparator.class.hashCode();
    }
}
