package net.emaze.dysfunctional.iterations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.logic.IsTrue;
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
    IterationsTest.Map.class,
    IterationsTest.Transform.class,
    IterationsTest.Any.class,
    IterationsTest.Every.class,
    IterationsTest.Each.class,
    IterationsTest.OneTime.class})
public class IterationsTest {

    public static class Map {

        @Test
        public void canMapAnIterable() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            List<Integer> got = Iterations.map(source, new Identity<Integer>());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnIterator() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            List<Integer> got = Iterations.map(source.iterator(), new Identity<Integer>());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnArray() {
            Integer[] source = new Integer[]{1, 2, 3};
            List<Integer> got = Iterations.map(source, new Identity<Integer>());
            Assert.assertEquals(Arrays.asList(source), got);
        }
    }

    public static class Transform {

        @Test
        public void canTransformAnIterable() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            Iterator<Integer> got = Iterations.transform(source, new Identity<Integer>());
            Assert.assertEquals(source, Consumers.all(got));
        }

        @Test
        public void canTransformAnIterator() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            Iterator<Integer> got = Iterations.transform(source.iterator(), new Identity<Integer>());
            Assert.assertEquals(source, Consumers.all(got));
        }

        @Test
        public void canTransformAnArray() {
            Integer[] source = new Integer[]{1, 2, 3};
            Iterator<Integer> got = Iterations.transform(source, new Identity<Integer>());
            Assert.assertEquals(Arrays.asList(source), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallTransformWithNullIterable() {
            final Iterable<Object> iterable = null;
            Iterations.transform(iterable, new Identity<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallTransformWithNullDelegate() {
            Iterations.transform(new ArrayList(), null);
        }
    }

    public static class OneTime {

        @Test
        public void canCreateAOneTimeIterable() {
            Assert.assertNotNull(Iterations.oneTime(Arrays.asList(1).iterator()));
        }
    }

    public static class Any {

        @Test
        public void anyMatchesIfAtLeastOnePredicateMatches() {
            boolean got = Iterations.any(Arrays.asList(false, true), new IsTrue());
            Assert.assertTrue(got);
        }

        @Test
        public void anyDoesntMatchIfNoPredicateMatches() {
            boolean got = Iterations.any(Arrays.asList(false), new IsTrue());
            Assert.assertFalse(got);
        }

        @Test
        public void canUseAnyWithIterators() {
            boolean got = Iterations.any(Arrays.asList(false).iterator(), new IsTrue());
            Assert.assertFalse(got);
        }

        @Test
        public void canUseAnyWithArrays() {
            boolean got = Iterations.any(new Boolean[]{false}, new IsTrue());
            Assert.assertFalse(got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAnyWithNullIterable() {
            final Iterable iterable = null;
            Iterations.any(iterable, new IsTrue());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallAnyWithNullPredicate() {
            Iterations.any(new ArrayList(), null);
        }
    }

    public static class Every {

        @Test
        public void everyMatchesIfEveryPredicateMatches() {
            boolean got = Iterations.every(Arrays.asList(true, true), new IsTrue());
            Assert.assertTrue(got);
        }

        @Test
        public void everyDoesntMatchIfOnePredicateDoesntMatch() {
            boolean got = Iterations.every(Arrays.asList(true, false), new IsTrue());
            Assert.assertFalse(got);
        }

        @Test
        public void canUseEveryWithIterators() {
            boolean got = Iterations.every(Arrays.asList(false).iterator(), new IsTrue());
            Assert.assertFalse(got);
        }

        @Test
        public void canUseEveryWithArrays() {
            boolean got = Iterations.every(new Boolean[]{false}, new IsTrue());
            Assert.assertFalse(got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEveryWithNullIterable() {
            final Iterable iterable = null;
            Iterations.every(iterable, new IsTrue());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEveryWithNullPredicate() {
            Iterations.every(new ArrayList(), null);
        }
    }

    public static class Each {

        public static class CountingAction<T> implements Action<T> {

            public int count = 0;

            @Override
            public void perform(T element) {
                ++count;
            }
        }

        @Test
        public void eachPerformsActionForEachElementInIterable() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Iterations.each(Arrays.asList(new Object(), new Object()), counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test
        public void canUseEeachWithIterators() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Iterations.each(Arrays.asList(new Object(), new Object()).iterator(), counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test
        public void canUseAnyWithArrays() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Iterations.each(new Object[]{new Object(), new Object()}, counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullIterable() {
            final Iterable<Object> iterable = null;
            Iterations.each(iterable, new Noop<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullAction() {
            final Iterable<Object> iterable = new ArrayList<Object>();
            Iterations.each(iterable, null);
        }
    }
}
