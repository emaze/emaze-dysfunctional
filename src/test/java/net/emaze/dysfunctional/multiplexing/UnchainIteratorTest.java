package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import java.util.function.Supplier;
import java.util.Optional;
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
    UnchainIteratorTest.Functions.class,
    UnchainIteratorTest.Degenerations.class
})
public class UnchainIteratorTest {

    final static Supplier<List<Integer>> LIST_FACTORY = Compositions.compose(new Vary<ArrayList<Integer>, List<Integer>>(), new ArrayListFactory<Integer>());
    final static Supplier<Optional<Integer>> SIZE_IS_ALWAYS_1 = new ConstantProvider<Optional<Integer>>(Optional.of(1));
    final static Supplier<Optional<Integer>> SIZE_IS_ALWAYS_2 = new ConstantProvider<Optional<Integer>>(Optional.of(2));

    public static class Functions {

        @Test
        public void canVerifyIfAnIteratorHasNext() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new UnchainIterator<List<Integer>, Integer>(SIZE_IS_ALWAYS_1, backingList.iterator(), LIST_FACTORY);
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingHasNextDoesNotConsumeIterator() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new UnchainIterator<List<Integer>, Integer>(SIZE_IS_ALWAYS_1, backingList.iterator(), LIST_FACTORY);
            iter.hasNext();
            Assert.assertTrue(iter.hasNext());
        }

        @Test
        public void callingHasNextOnTooShortIteratorYieldsFalse() {
            List<Integer> backingList = Arrays.asList(1);
            Iterator<List<Integer>> iter = new UnchainIterator<List<Integer>, Integer>(SIZE_IS_ALWAYS_2, backingList.iterator(), LIST_FACTORY);
            Assert.assertFalse(iter.hasNext());
        }

        @Test
        public void canConsumeAnElementCallingHasNextBefore() {
            List<Integer> backingList = Arrays.asList(1, 2);
            Iterator<List<Integer>> iter = new UnchainIterator<List<Integer>, Integer>(SIZE_IS_ALWAYS_2, backingList.iterator(), LIST_FACTORY);
            iter.hasNext();
            Assert.assertEquals(backingList, iter.next());
        }

        @Test
        public void canConsumeElementsWithoutCallingHasNext() {
            List<Integer> backingList = Arrays.asList(1, 2);
            Iterator<List<Integer>> iter = new UnchainIterator<List<Integer>, Integer>(SIZE_IS_ALWAYS_2, backingList.iterator(), LIST_FACTORY);
            Assert.assertEquals(backingList, iter.next());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullIteratorYieldsException() {
            new UnchainIterator<List<Integer>, Integer>(SIZE_IS_ALWAYS_1, null, LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullChannelProviderYieldsException() {
            new UnchainIterator<List<Integer>, Integer>(null, Collections.<Integer>emptyList().iterator(), LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingWithNullListFactoryYieldsException() {
            new UnchainIterator<List<Integer>, Integer>(SIZE_IS_ALWAYS_2, Collections.<Integer>emptyList().iterator(), null);
        }

        @Test(expected = NoSuchElementException.class)
        public void consumingNonSquaredIteratoryYieldsException() {
            Iterator<List<Integer>> iter = new UnchainIterator<List<Integer>, Integer>(SIZE_IS_ALWAYS_2, Arrays.asList(1, 2, 2).iterator(), LIST_FACTORY);
            iter.next();
            iter.hasNext();
            iter.next();
        }
    }
}
