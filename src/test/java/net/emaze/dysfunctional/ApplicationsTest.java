package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
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
            final Iterable<Integer> source = Arrays.asList(1, 2, 3);
            final Iterator<Integer> got = Applications.transform(source, UnaryOperator.identity());
            Assert.assertEquals(source, Consumers.all(got));
        }

        @Test
        public void canTransformAnIterator() {
            final Iterable<Integer> source = Arrays.asList(1, 2, 3);
            final Iterator<Integer> got = Applications.transform(source.iterator(), UnaryOperator.identity());
            Assert.assertEquals(source, Consumers.all(got));
        }

        @Test
        public void canTransformAnArray() {
            final Integer[] source = new Integer[]{1, 2, 3};
            final Iterator<Integer> got = Applications.transform(source, UnaryOperator.identity());
            Assert.assertEquals(Arrays.asList(source), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallTransformWithNullIterable() {
            final Iterable<Object> iterable = null;
            Applications.transform(iterable, UnaryOperator.identity());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallTransformWithNullDelegate() {
            Applications.transform(new ArrayList(), null);
        }
    }

    public static class Map {

        @Test(expected = IllegalArgumentException.class)
        public void mappingNullIterableYieldsException() {
            final Iterable<Integer> source = null;
            Applications.map(source, UnaryOperator.identity());
        }

        @Test
        public void canMapAnIterable() {
            final List<Integer> source = Arrays.asList(1, 2, 3);
            final List<Integer> got = Applications.map(source, UnaryOperator.identity());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnIterator() {
            final List<Integer> source = Arrays.asList(1, 2, 3);
            final List<Integer> got = Applications.map(source.iterator(), UnaryOperator.identity());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnArray() {
            final Integer[] source = new Integer[]{1, 2, 3};
            final List<Integer> got = Applications.map(source, UnaryOperator.identity());
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

        @Test(expected = IllegalArgumentException.class)
        public void tappingNullIterableYieldsException() {
            final Iterable<O> iterable = null;
            Applications.tap(iterable, new Noop<O>());
        }
    }

    public static class Each {

        @Test
        public void eachPerformsActionForEachElementInIterable() {
            final AtomicLong calls = new AtomicLong();
            final Consumer<Object> counter = Spies.monitor(new Noop<Object>(), calls);
            Applications.each(Arrays.asList(new Object(), new Object()), counter);
            Assert.assertEquals(2l, calls.get());
        }

        @Test
        public void canUseEeachWithIterators() {
            final AtomicLong calls = new AtomicLong();
            final Consumer<Object> counter = Spies.monitor(new Noop<Object>(), calls);
            Applications.each(Arrays.asList(new Object(), new Object()).iterator(), counter);
            Assert.assertEquals(2l, calls.get());
        }

        @Test
        public void canUseAnyWithArrays() {
            final AtomicLong calls = new AtomicLong();
            final Consumer<Object> counter = Spies.monitor(new Noop<Object>(), calls);
            Applications.each(new Object[]{new Object(), new Object()}, counter);
            Assert.assertEquals(2l, calls.get());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullIterable() {
            final Iterable<Object> iterable = null;
            Applications.each(iterable, new Noop<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullIterator() {
            final Iterator<Object> iterator = null;
            Applications.each(iterator, new Noop<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullArray() {
            final Object[] array = null;
            Applications.each(array, new Noop<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachOnIterableWithNullAction() {
            final Iterable<Object> iterable = Iterations.iterable();
            Applications.each(iterable, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachOnIteratorWithNullAction() {
            final Iterator<Object> iterator = Iterations.iterator();
            Applications.each(iterator, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachOnArrayWithNullAction() {
            final Object[] array = new Object[]{};
            Applications.each(array, null);
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
