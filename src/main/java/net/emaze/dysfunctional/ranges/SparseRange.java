package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.iterations.ChainIterator;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 *
 * @author rferranti
 */
public class SparseRange<T extends Comparable<T>> implements Range<T> {

    private final List<Range<T>> ranges = new ArrayList<Range<T>>();
    private final RangePolicy<T> policy;
    
    public SparseRange(RangePolicy<T> policy, Range<T>... ranges) {
        if (ranges.length == 0) {
            throw new IllegalArgumentException("SparseRange<T> must be constructed with at least one argument");
        }
        this.policy = policy;
        this.ranges.addAll(policy.normalize(ranges));
    }

    @Override
    public boolean contains(final T element) {
        return Iterations.any(ranges, new Predicate<Range<T>>() {

            @Override
            public boolean test(Range<T> range) {
                return range.contains(element);
            }
        });
    }

    @Override
    public T lower() {
        return ranges.get(0).lower();
    }

    @Override
    public T upper() {
        return ranges.get(ranges.size() - 1).upper();
    }
    @Override
    public int compareTo(Range<T> other) {
        return policy.compare(this, other);
    }

    @Override
    public Iterator<T> iterator() {
        return new ChainIterator<T>(Iterations.map(ranges , new Delegate< Iterator<T>,Range<T>>(){
            @Override
            public Iterator<T> perform(Range<T> iterable) {
                return iterable.iterator();
            }
        }));
    }

}
