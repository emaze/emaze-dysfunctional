package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
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
        List<String> bucket = Arrays.asList("a","b");
        Iterable<Pair<Integer,String>> iterable = Counts.counted(bucket);
        Assert.assertEquals(Arrays.asList(Pair.of(0,"a"), Pair.of(1,"b")), Consumers.all(iterable));
    }

    @Test
    public void canGetCountedIteratorWithRange() {
        Range<Integer> range = new DenseRange<Integer>(new IntegerSequencingPolicy(), new ComparableComparator<Integer>(), 1, 10);
        List<String> bucket = Arrays.asList("a","b");
        Iterable<Pair<Integer,String>> iterable = Counts.counted(bucket, range);
        Assert.assertEquals(Arrays.asList(Pair.of(1,"a"), Pair.of(2,"b")), Consumers.all(iterable));
    }


}