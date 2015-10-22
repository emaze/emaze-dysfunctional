package net.emaze.dysfunctional.sequences;

import java.util.*;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CollectingTest.Flattening.class,
    CollectingTest.Reversing.class
})
public class CollectingTest {

    public static class Flattening {

        @Test
        public void flatStreamIntoList() {
            final Stream<List<Integer>> stream = Stream.of(
                    Arrays.asList(1, 2, 3),
                    Arrays.asList(4, 5),
                    Arrays.<Integer>asList(),
                    Arrays.asList(6));
            final List<Integer> got = stream.collect(Collecting.flat());
            Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), got);
        }

        @Test
        public void flatStreamIntoCollection() {
            final Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(3, 4, 5));
            final Set<Integer> got = stream.collect(Collecting.flat(HashSet::new));
            Assert.assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)), got);
        }
    }

    public static class Reversing {

        @Test
        public void reversingSequentialStream() {
            final Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
            final Deque<Integer> got = stream.collect(Collecting.reverse());
            Assert.assertEquals(Arrays.asList(6, 5, 4, 3, 2, 1), got);
        }

        @Test
        public void reversingParallelStream() {
            final Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallel();
            final Deque<Integer> got = stream.collect(Collecting.reverse());
            Assert.assertEquals(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), got);
        }
    }
}
