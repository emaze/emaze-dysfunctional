package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ApplicationsTest.Transform.class,
    ApplicationsTest.Map.class,
    ApplicationsTest.Tap.class,
    ApplicationsTest.Each.class,
    ApplicationsTest.Facade.class
})
public class ApplicationsTest {

    public static class Transform {

        @Test
        public void canTransformAnIterable() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            Iterator<Integer> got = Applications.transform(source, new Identity<Integer>());
            Assert.assertEquals(source, Consumers.all(got));
        }

        @Test
        public void canTransformAnIterator() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            Iterator<Integer> got = Applications.transform(source.iterator(), new Identity<Integer>());
            Assert.assertEquals(source, Consumers.all(got));
        }

        @Test
        public void canTransformAnArray() {
            Integer[] source = new Integer[]{1, 2, 3};
            Iterator<Integer> got = Applications.transform(source, new Identity<Integer>());
            Assert.assertEquals(Arrays.asList(source), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallTransformWithNullIterable() {
            final Iterable<Object> iterable = null;
            Applications.transform(iterable, new Identity<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallTransformWithNullDelegate() {
            Applications.transform(new ArrayList(), null);
        }
    }

    public static class Map {

        @Test
        public void canMapAnIterable() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            List<Integer> got = Applications.map(source, new Identity<Integer>());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnIterator() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            List<Integer> got = Applications.map(source.iterator(), new Identity<Integer>());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnArray() {
            Integer[] source = new Integer[]{1, 2, 3};
            List<Integer> got = Applications.map(source, new Identity<Integer>());
            Assert.assertEquals(Arrays.asList(source), got);
        }
    }

    public static class Tap {

        @Test
        public void canTapAnIterable() {
            final Iterable<O> source = Iterations.iterable(O.ONE);
            final Box<O> box = new Box<O>();
            final Iterator<O> tap = Applications.tap(source, Spies.spy(new Noop<O>(), box));
            Consumers.all(tap);
            Assert.assertEquals(O.ONE, box.getContent());
        }

        @Test
        public void canTapAnIterator() {
            final Iterator<O> source = Iterations.iterator(O.ONE);
            final Box<O> box = new Box<O>();
            final Iterator<O> tap = Applications.tap(source, Spies.spy(new Noop<O>(), box));
            Consumers.all(tap);
            Assert.assertEquals(O.ONE, box.getContent());
        }

        @Test
        public void canTapAnArray() {
            final O[] source = {O.ONE};
            final Box<O> box = new Box<O>();
            final Iterator<O> tap = Applications.tap(source, Spies.spy(new Noop<O>(), box));
            Consumers.all(tap);
            Assert.assertEquals(O.ONE, box.getContent());
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
            Applications.each(Arrays.asList(new Object(), new Object()), counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test
        public void canUseEeachWithIterators() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Applications.each(Arrays.asList(new Object(), new Object()).iterator(), counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test
        public void canUseAnyWithArrays() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Applications.each(new Object[]{new Object(), new Object()}, counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullIterable() {
            final Iterable<Object> iterable = null;
            Applications.each(iterable, new Noop<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullAction() {
            final Iterable<Object> iterable = new ArrayList<Object>();
            Applications.each(iterable, null);
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Applications() {
            };
        }
    }
}
