package net.emaze.disfunctional.ranges;

import net.emaze.disfunctional.iterations.RangeIterator;
import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class DenseRange <T extends Comparable<T>> implements Range<T> {

    private final T lower;
    private final T upper;
    private final RangePolicy<T> policy;

    public DenseRange(RangePolicy<T> policy, T lower, T upper){
        this.policy = policy;
        this.lower = lower;
        this.upper = upper;
    }


    public boolean contains(T element) {
        return policy.contains(this,element);
    }

    public T lower() {
        return lower;
    }

    public T upper() {
        return upper;
    }

    public int compareTo(Range<T> other) {
        return policy.compare(this, other);
    }

    public Iterator<T> iterator() {
        return new RangeIterator(policy, lower, upper);
    }

}
