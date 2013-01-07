package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.RangesTest.RangeDifferenceTest;
import net.emaze.dysfunctional.RangesTest.RangeFactoryTest;
import net.emaze.dysfunctional.RangesTest.RangeIntersectTest;
import net.emaze.dysfunctional.RangesTest.RangeSymmetricDifferenceTest;
import net.emaze.dysfunctional.RangesTest.RangeUnionTest;
import net.emaze.dysfunctional.RangesTest.RangesCreationTest;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.NextIntegerSequencingPolicy;
import net.emaze.dysfunctional.ranges.Range;
import net.emaze.dysfunctional.ranges.Range.Endpoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    RangeFactoryTest.class,
    RangesCreationTest.class,
    RangeUnionTest.class,
    RangeIntersectTest.class,
    RangeSymmetricDifferenceTest.class,
    RangeDifferenceTest.class
})
public class RangesTest {

    public static class RangesCreationTest {

        @Test
        public void canCreateRangesWithNullEmptyValue() {
            new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullComparatorYieldsException() {
            new Ranges<Integer>(null, new NextIntegerSequencingPolicy(), 0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullSequencerYieldsException() {
            new Ranges<Integer>(new ComparableComparator<Integer>(), null, 0);
        }
    }

    public static class RangeFactoryTest {

        @Test
        public void canCreateEmptyRange() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> empty = ranges.empty();
            Assert.assertFalse(empty.iterator().hasNext());
        }

        @Test
        public void canCreateAnOpenRange() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> range = ranges.open(1, 3);
            Assert.assertEquals(Arrays.asList(2), Consumers.all(range));
        }

        @Test
        public void canCreateAClosedRange() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> range = ranges.closed(1, 3);
            Assert.assertEquals(Arrays.asList(1, 2, 3), Consumers.all(range));
        }

        @Test
        public void canCreateSingletonRange() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> range = ranges.degenerate(1);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(range));
        }

        @Test
        public void canCreateRange() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> range = ranges.of(Endpoint.Include, 0, Maybe.just(2), Endpoint.Include);
            Assert.assertEquals(Arrays.asList(0, 1, 2), Consumers.all(range));
        }
    }

    public static class RangeUnionTest {

        @Test
        public void canEvaluateUnionBetweenTwoRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.union(lhs, rhs);
            Assert.assertEquals(Arrays.asList(0, 1, 2), Consumers.all(got));
        }

        @Test
        public void canEvaluateUnionBetweenThreeRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> first = ranges.closed(1, 2);
            final Range<Integer> second = ranges.closed(0, 1);
            final Range<Integer> third = ranges.closed(3, 4);
            final Range<Integer> got = ranges.union(first, second, third);
            Assert.assertEquals(Arrays.asList(0, 1, 2, 3, 4), Consumers.all(got));
        }

        @Test
        public void canEvaluateUnionOfAnIteratorOfRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.union(Iterations.iterator(lhs, rhs));
            Assert.assertEquals(Arrays.asList(0, 1, 2), Consumers.all(got));
        }

        @Test
        public void canEvaluateUnionOfAnIterableOfRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.union(Iterations.iterable(lhs, rhs));
            Assert.assertEquals(Arrays.asList(0, 1, 2), Consumers.all(got));
        }

        @Test
        public void canEvaluateUnionOfAnManyRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.union(lhs, rhs, rhs, rhs, rhs, rhs);
            Assert.assertEquals(Arrays.asList(0, 1, 2), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingUnionOfAnEmptyIteratorYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterator<Range<Integer>> iterator = Iterations.iterator();
            ranges.union(iterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingUnionOfNullIteratorYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterator<Range<Integer>> iterator = null;
            ranges.union(iterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingUnionOfAnEmptyIterableYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterable<Range<Integer>> iterable = Iterations.iterable();
            ranges.union(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingUnionOfNullIterableYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterable<Range<Integer>> iterable = null;
            ranges.union(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingUnionOfAnEmptyArrayYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer>[] array = (Range<Integer>[]) Arrays.asList().toArray(new Range[0]);
            ranges.union(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingUnionOfNullArrayYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer>[] array = null;
            ranges.union(array);
        }
    }

    public static class RangeIntersectTest {

        @Test
        public void canEvaluateIntersectBetweenTwoRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.intersect(lhs, rhs);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(got));
        }

        @Test
        public void canEvaluateIntersectBetweenThreeRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> first = ranges.closed(1, 2);
            final Range<Integer> second = ranges.closed(0, 1);
            final Range<Integer> third = ranges.closed(0, 4);
            final Range<Integer> got = ranges.intersect(first, second, third);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(got));
        }

        @Test
        public void canEvaluateIntersectOfAnIteratorOfRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.intersect(Iterations.iterator(lhs, rhs));
            Assert.assertEquals(Arrays.asList(1), Consumers.all(got));
        }

        @Test
        public void canEvaluateIntersectOfAnIterableOfRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.intersect(Iterations.iterable(lhs, rhs));
            Assert.assertEquals(Arrays.asList(1), Consumers.all(got));
        }

        @Test
        public void canEvaluateIntersectOfAnManyRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.intersect(lhs, rhs, rhs, rhs, rhs, rhs);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingIntersectOfAnEmptyIteratorYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterator<Range<Integer>> iterator = Iterations.iterator();
            ranges.intersect(iterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingIntersectOfNullIteratorYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterator<Range<Integer>> iterator = null;
            ranges.intersect(iterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingIntersectOfAnEmptyIterableYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterable<Range<Integer>> iterable = Iterations.iterable();
            ranges.intersect(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingIntersectOfNullIterableYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterable<Range<Integer>> iterable = null;
            ranges.intersect(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingIntersectOfAnEmptyArrayYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer>[] array = (Range<Integer>[]) Arrays.asList().toArray(new Range[0]);
            ranges.intersect(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingIntersectOfNullArrayYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer>[] array = null;
            ranges.intersect(array);
        }
    }

    public static class RangeSymmetricDifferenceTest {

        @Test
        public void canEvaluateSymmetricDifferenceBetweenTwoRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.symmetricDifference(lhs, rhs);
            Assert.assertEquals(Arrays.asList(0, 2), Consumers.all(got));
        }

        @Test
        public void canEvaluateSymmetricDifferenceBetweenThreeRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> first = ranges.closed(1, 2);
            final Range<Integer> second = ranges.closed(0, 1);
            final Range<Integer> third = ranges.closed(0, 1);
            final Range<Integer> got = ranges.symmetricDifference(first, second, third);
            Assert.assertEquals(Arrays.asList(1, 2), Consumers.all(got));
        }

        @Test
        public void canEvaluateSymmetricDifferenceOfAnIteratorOfRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.symmetricDifference(Iterations.iterator(lhs, rhs));
            Assert.assertEquals(Arrays.asList(0, 2), Consumers.all(got));
        }

        @Test
        public void canEvaluateSymmetricDifferenceOfAnIterableOfRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.symmetricDifference(Iterations.iterable(lhs, rhs));
            Assert.assertEquals(Arrays.asList(0, 2), Consumers.all(got));
        }

        @Test
        public void canEvaluateSymmetricDifferenceOfAnManyRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.symmetricDifference(lhs, rhs, rhs, rhs, rhs, rhs);
            Assert.assertEquals(Arrays.asList(0, 2), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingSymmetricDifferenceOfAnEmptyIteratorYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterator<Range<Integer>> iterator = Iterations.iterator();
            ranges.symmetricDifference(iterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingSymmetricDifferenceOfNullIteratorYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterator<Range<Integer>> iterator = null;
            ranges.symmetricDifference(iterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingSymmetricDifferenceOfAnEmptyIterableYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterable<Range<Integer>> iterable = Iterations.iterable();
            ranges.symmetricDifference(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingSymmetricDifferenceOfNullIterableYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterable<Range<Integer>> iterable = null;
            ranges.symmetricDifference(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingSymmetricDifferenceOfAnEmptyArrayYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer>[] array = (Range<Integer>[]) new Range[0];
            ranges.symmetricDifference(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingSymmetricDifferenceOfNullArrayYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer>[] array = null;
            ranges.symmetricDifference(array);
        }
    }

    public static class RangeDifferenceTest {

        @Test
        public void canEvaluateDifferenceBetweenTwoRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(1, 2);
            final Range<Integer> got = ranges.difference(lhs, rhs);
            Assert.assertEquals(Arrays.asList(), Consumers.all(got));
        }

        @Test
        public void canEvaluateDifferenceBetweenThreeRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> first = ranges.closed(0, 4);
            final Range<Integer> second = ranges.closed(0, 1);
            final Range<Integer> third = ranges.closed(3, 4);
            final Range<Integer> got = ranges.difference(first, second, third);
            Assert.assertEquals(Arrays.asList(2), Consumers.all(got));
        }

        @Test
        public void canEvaluateDifferenceOfAnIteratorOfRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(1, 2);
            final Range<Integer> got = ranges.difference(Iterations.iterator(lhs, rhs));
            Assert.assertEquals(Arrays.asList(), Consumers.all(got));
        }

        @Test
        public void canEvaluateDifferenceOfAnIterableOfRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 2);
            final Range<Integer> rhs = ranges.closed(1, 2);
            final Range<Integer> got = ranges.difference(Iterations.iterable(lhs, rhs));
            Assert.assertEquals(Arrays.asList(), Consumers.all(got));
        }

        @Test
        public void canEvaluateDifferenceOfAnManyRanges() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer> lhs = ranges.closed(1, 4);
            final Range<Integer> rhs = ranges.closed(0, 1);
            final Range<Integer> got = ranges.difference(lhs, rhs, rhs, rhs, rhs, rhs);
            Assert.assertEquals(Arrays.asList(2, 3, 4), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingDifferenceOfAnEmptyIteratorYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterator<Range<Integer>> iterator = Iterations.iterator();
            ranges.difference(iterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingDifferenceOfNullIteratorYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterator<Range<Integer>> iterator = null;
            ranges.difference(iterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingDifferenceOfAnEmptyIterableYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterable<Range<Integer>> iterable = Iterations.iterable();
            ranges.difference(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingDifferenceOfNullIterableYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Iterable<Range<Integer>> iterable = null;
            ranges.difference(iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingDifferenceOfAnEmptyArrayYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer>[] array = (Range<Integer>[]) new Range[0];
            ranges.difference(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void evaluatingDifferenceOfNullArrayYieldsException() {
            final Ranges<Integer> ranges = new Ranges<Integer>(new ComparableComparator<Integer>(), new NextIntegerSequencingPolicy(), 0);
            final Range<Integer>[] array = null;
            ranges.difference(array);
        }
    }
}