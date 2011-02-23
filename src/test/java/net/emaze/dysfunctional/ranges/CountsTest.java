package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CountsTest {

    @Test
    public void canGetCountedIterator() {
        List<String> bucket = Arrays.asList("a", "b");
        Iterable<Pair<Integer, String>> iterable = Counts.counted(bucket);
        Assert.assertEquals(Arrays.asList(Pair.of(0, "a"), Pair.of(1, "b")), Consumers.all(iterable));
    }

    @Test
    public void canGetCountedIteratorWithRange() {
        Range<Integer> range = new DenseRange<Integer>(new IntegerSequencingPolicy(), new ComparableComparator<Integer>(), 1, 10);
        List<String> bucket = Arrays.asList("a", "b");
        Iterable<Pair<Integer, String>> iterable = Counts.counted(bucket, range);
        Assert.assertEquals(Arrays.asList(Pair.of(1, "a"), Pair.of(2, "b")), Consumers.all(iterable));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallCountedWithANullIterable() {
        final Iterable<Object> iterable = null;
        Counts.counted(iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallCountedWithANullIterableAndARange() {
        final Iterable<Object> iterable = null;
        final StubRange range = new StubRange();
        Counts.counted(iterable, range);
    }

    private static class StubRange implements Range<Object> {

        @Override
        public boolean contains(Object element) {
            return false;
        }

        @Override
        public boolean overlaps(Range<Object> rhs) {
            return false;
        }

        @Override
        public Object lower() {
            return null;
        }

        @Override
        public Object upper() {
            return null;
        }

        @Override
        public List<DenseRange<Object>> densified() {
            return null;
        }

        @Override
        public Iterator<Object> iterator() {
            return null;
        }

        @Override
        public int compareTo(Range<Object> o) {
            return 0;
        }
    }
}
