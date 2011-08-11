package net.emaze.dysfunctional.eagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.actions.Noop;

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
    EagerlyTest.Map.class,
    EagerlyTest.Each.class,
    EagerlyTest.Facade.class})
public class EagerlyTest {

    public static class Map {

        @Test
        public void canMapAnIterable() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            List<Integer> got = Eagerly.map(source, new Identity<Integer>());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnIterator() {
            List<Integer> source = Arrays.asList(1, 2, 3);
            List<Integer> got = Eagerly.map(source.iterator(), new Identity<Integer>());
            Assert.assertEquals(source, got);
        }

        @Test
        public void canMapAnArray() {
            Integer[] source = new Integer[]{1, 2, 3};
            List<Integer> got = Eagerly.map(source, new Identity<Integer>());
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
            Eagerly.each(Arrays.asList(new Object(), new Object()), counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test
        public void canUseEeachWithIterators() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Eagerly.each(Arrays.asList(new Object(), new Object()).iterator(), counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test
        public void canUseAnyWithArrays() {
            CountingAction<Object> counter = new CountingAction<Object>();
            Eagerly.each(new Object[]{new Object(), new Object()}, counter);
            Assert.assertEquals(2, counter.count);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullIterable() {
            final Iterable<Object> iterable = null;
            Eagerly.each(iterable, new Noop<Object>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotCallEachWithNullAction() {
            final Iterable<Object> iterable = new ArrayList<Object>();
            Eagerly.each(iterable, null);
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Eagerly() {
            };
        }
    }
}
