package net.emaze.dysfunctional.reductions;

import net.emaze.dysfunctional.Reductions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.dispatching.logic.IsTrue;
import net.emaze.dysfunctional.order.ComparableComparator;
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
    ReductionsTest.Reduce.class,
    ReductionsTest.CountLong.class,
    ReductionsTest.CountInteger.class,
    ReductionsTest.Maximum.class,
    ReductionsTest.Minimum.class,
    ReductionsTest.Any.class,
    ReductionsTest.Every.class,
    ReductionsTest.Facade.class
})
public class ReductionsTest {

    private static List<Integer> list = Arrays.asList(1, 2);
    private static Integer[] array = new Integer[]{1, 2};

    public static class Reduce {

        @Test
        public void canReduceFromIterator() {
            Assert.assertEquals(Long.valueOf(2l), Reductions.reduce(list.iterator(), new Count<Integer>(), 0l));
        }

        @Test
        public void canReduceFromIterable() {
            Assert.assertEquals(Long.valueOf(2l), Reductions.reduce(list, new Count<Integer>(), 0l));
        }

        @Test
        public void canReduceFromArray() {
            Assert.assertEquals(Long.valueOf(2l), Reductions.reduce(array, new Count<Integer>(), 0l));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallReduceWithNullIterable() {
            final Iterable<Object> iterable = null;
            Reductions.reduce(iterable, new Count<Object>(), 0L);
        }
    }

    public static class CountLong {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallCountWithNullIterable() {
            final Iterable<Object> iterable = null;
            Reductions.count(iterable);
        }

        @Test
        public void canCountFromIterator() {
            Assert.assertEquals(2l, Reductions.count(list.iterator()));
        }

        @Test
        public void canCountFromIterable() {
            Assert.assertEquals(2l, Reductions.count(list));
        }
    }

    public static class CountInteger {

        @Test
        public void canCountAsIntegerFromIterator() {
            Assert.assertEquals(2l, Reductions.counti(list.iterator()));
        }

        @Test
        public void canCountAsIntegerFromIterable() {
            Assert.assertEquals(2l, Reductions.counti(list));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallCountiWithNullIterable() {
            final Iterable<Object> iterable = null;
            Reductions.counti(iterable);
        }
    }

    public static class Maximum {

        @Test
        public void canExtractMaximumWithComparator() {
            int max = Reductions.maximum(list.iterator(), new ComparableComparator<Integer>(), 0);
            Assert.assertEquals(2, max);
        }

        @Test
        public void canExtractMaximumWithComparable() {
            int max = Reductions.maximum(list.iterator(), 0);
            Assert.assertEquals(2, max);
        }
    }

    public static class Minimum {

        @Test
        public void canExtractMinimumWithComparator() {
            int min = Reductions.minimum(list.iterator(), new ComparableComparator<Integer>(), 2);
            Assert.assertEquals(1, min);
        }

        @Test
        public void canExtractMinimumWithComparable() {
            int min = Reductions.minimum(list.iterator(), 2);
            Assert.assertEquals(1, min);
        }
    }
    public static class Any {

        @Test
        public void anyMatchesIfAtLeastOnePredicateMatches() {
            boolean got = Reductions.any(Arrays.asList(false, true), new IsTrue());
            Assert.assertTrue(got);
        }

        @Test
        public void anyDoesntMatchIfNoPredicateMatches() {
            boolean got = Reductions.any(Arrays.asList(false), new IsTrue());
            Assert.assertFalse(got);
        }

        @Test
        public void canUseAnyWithIterators() {
            boolean got = Reductions.any(Arrays.asList(false).iterator(), new IsTrue());
            Assert.assertFalse(got);
        }

        @Test
        public void canUseAnyWithArrays() {
            boolean got = Reductions.any(new Boolean[]{false}, new IsTrue());
            Assert.assertFalse(got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAnyWithNullIterable() {
            final Iterable iterable = null;
            Reductions.any(iterable, new IsTrue());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAnyWithNullPredicate() {
            Reductions.any(new ArrayList(), null);
        }
    }

    public static class Every {

        @Test
        public void everyMatchesIfEveryPredicateMatches() {
            boolean got = Reductions.every(Arrays.asList(true, true), new IsTrue());
            Assert.assertTrue(got);
        }

        @Test
        public void everyDoesntMatchIfOnePredicateDoesntMatch() {
            boolean got = Reductions.every(Arrays.asList(true, false), new IsTrue());
            Assert.assertFalse(got);
        }

        @Test
        public void canUseEveryWithIterators() {
            boolean got = Reductions.every(Arrays.asList(false).iterator(), new IsTrue());
            Assert.assertFalse(got);
        }

        @Test
        public void canUseEveryWithArrays() {
            boolean got = Reductions.every(new Boolean[]{false}, new IsTrue());
            Assert.assertFalse(got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEveryWithNullIterable() {
            final Iterable iterable = null;
            Reductions.every(iterable, new IsTrue());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEveryWithNullPredicate() {
            Reductions.every(new ArrayList(), null);
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Reductions() {
            };
        }
    }
}
