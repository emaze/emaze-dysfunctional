package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.options.Maybe;
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
    IterationsTest.OneTime.class,
    IterationsTest.Facade.class,
    IterationsTest.IteratorTest.class,
    IterationsTest.IterableTest.class
})
public class IterationsTest {

    public static class OneTime {

        @Test
        public void canCreateAOneTimeIterable() {
            Assert.assertNotNull(Iterations.oneTime(Arrays.asList(1).iterator()));
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Iterations() {
            };
        }
    }

    public static class IteratorTest {

        @Test
        public void canCreateEmptyIterator() {
            final Iterator<O> iterator = Iterations.iterator();
            final List<O> expected = Arrays.asList();
            Assert.assertEquals(expected, Consumers.all(iterator));
        }

        @Test
        public void canCreateIteratorFromOneElement() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE);
            final List<O> expected = Arrays.asList(O.ONE);
            Assert.assertEquals(expected, Consumers.all(iterator));
        }

        @Test
        public void canCreateIteratorFromTwoElements() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER);
            Assert.assertEquals(expected, Consumers.all(iterator));
        }

        @Test
        public void canCreateIteratorFromThreeElements() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertEquals(expected, Consumers.all(iterator));
        }

        @Test
        public void canCreateIteratorFromManyElements() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER, O.YET_ANOTHER, O.YET_ANOTHER);
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER, O.YET_ANOTHER, O.YET_ANOTHER);
            Assert.assertEquals(expected, Consumers.all(iterator));
        }

        @Test
        public void canCreateIteratorFromProvider() {
            final Iterator<O> iterator = Iterations.iterator(new ConstantProvider<Maybe<O>>(Maybe.<O>nothing()));
            final List<O> expected = Arrays.asList();
            Assert.assertEquals(expected, Consumers.all(iterator));
        }
    }

    public static class IterableTest {

        @Test
        public void canCreateEmptyIterable() {
            final Iterable<O> iterable = Iterations.iterable();
            final List<O> expected = Arrays.asList();
            Assert.assertEquals(expected, Consumers.all(iterable));
        }

        @Test
        public void canCreateIteratorFromOneElement() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE);
            final List<O> expected = Arrays.asList(O.ONE);
            Assert.assertEquals(expected, Consumers.all(iterable));
        }

        @Test
        public void canCreateIteratorFromTwoElements() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER);
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER);
            Assert.assertEquals(expected, Consumers.all(iterable));
        }

        @Test
        public void canCreateIteratorFromThreeElements() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertEquals(expected, Consumers.all(iterable));
        }

        @Test
        public void canCreateIteratorFromManyElements() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE, O.ANOTHER, O.YET_ANOTHER, O.YET_ANOTHER);
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER, O.YET_ANOTHER, O.YET_ANOTHER);
            Assert.assertEquals(expected, Consumers.all(iterable));
        }
    }
}
