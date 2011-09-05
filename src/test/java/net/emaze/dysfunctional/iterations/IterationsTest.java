package net.emaze.dysfunctional.iterations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.Consumers;
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
    IterationsTest.Iter.class
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

    public static class Iter {

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
    }
}
