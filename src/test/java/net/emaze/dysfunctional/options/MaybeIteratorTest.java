package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.iterations.ArrayIterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MaybeIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMaybeIteratorWithNullIteratorYieldException() {
        new MaybeIterator<Integer>(null);
    }

    @Test
    public void maybeIterHasNextIfInnerIteratorHasNext() {
        MaybeIterator<Integer> maybeIter = new MaybeIterator<Integer>(new ArrayIterator<Integer>(new Integer[]{1}));
        Assert.assertTrue(maybeIter.hasNext());
    }

    @Test
    public void maybeIterHasNoNextIfInnerIteratorHasNoNext() {
        MaybeIterator<Integer> maybeIter = new MaybeIterator<Integer>(new ArrayIterator<Integer>(new Integer[]{}));
        Assert.assertFalse(maybeIter.hasNext());
    }

    @Test
    public void canFetchNextFromNonEmptyIterator() {
        MaybeIterator<Integer> maybeIter = new MaybeIterator<Integer>(new ArrayIterator<Integer>(new Integer[]{1}));
        Assert.assertEquals(Maybe.just(1), maybeIter.next());
    }

    @Test
    public void canFetchNextFromEmptyIterator() {
        MaybeIterator<Integer> maybeIter = new MaybeIterator<Integer>(new ArrayIterator<Integer>(new Integer[]{}));
        Assert.assertEquals(Maybe.nothing(), maybeIter.next());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removingFromMaybeIteratorYieldsException() {
        MaybeIterator<Integer> maybeIter = new MaybeIterator<Integer>(new ArrayIterator<Integer>(new Integer[]{1}));
        maybeIter.next();
        maybeIter.remove();
    }
}
