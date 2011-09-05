package net.emaze.dysfunctional.dispatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TransformingTest.Map.class,
    TransformingTest.Each.class,
    TransformingTest.Facade.class
})
public class TransformingTest {

    public static class Transform {

        @Test
        public void canTransformAnIterable() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            Iterator<Integer> got = Transforming.transform(source, new Identity<Integer>());
            Assert.assertEquals(source, Consumers.all(got));
        }

        @Test
        public void canTransformAnIterator() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            Iterator<Integer> got = Transforming.transform(source.iterator(), new Identity<Integer>());
            Assert.assertEquals(source, Consumers.all(got));
        }

        @Test
        public void canTransformAnArray() {
            Integer[] source = new Integer[]{1, 2, 3};
            Iterator<Integer> got = Transforming.transform(source, new Identity<Integer>());
            Assert.assertEquals(Arrays.asList(source), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallTransformWithNullIterable() {
            final Iterable<Object> iterable = null;
            Transforming.transform(iterable, new Identity<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallTransformWithNullDelegate() {
            Transforming.transform(new ArrayList(), null);
        }
    }

    public static class Map {

        @Test
        public void canMapAnIterable() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            List<Integer> got = Transforming.map(source, new Identity<Integer>());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnIterator() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            List<Integer> got = Transforming.map(source.iterator(), new Identity<Integer>());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnArray() {
            Integer[] source = new Integer[]{1, 2, 3};
            List<Integer> got = Transforming.map(source, new Identity<Integer>());
            Assert.assertEquals(Arrays.asList(source), got);
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
            Transforming.each(Arrays.asList(new Object(), new Object()), counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test
        public void canUseEeachWithIterators() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Transforming.each(Arrays.asList(new Object(), new Object()).iterator(), counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test
        public void canUseAnyWithArrays() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Transforming.each(new Object[]{new Object(), new Object()}, counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullIterable() {
            final Iterable<Object> iterable = null;
            Transforming.each(iterable, new Noop<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullAction() {
            final Iterable<Object> iterable = new ArrayList<Object>();
            Transforming.each(iterable, null);
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Transforming() {
            };
        }
    }
}
