package net.emaze.dysfunctional.options;

import java.util.Optional;
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
        Assert.assertEquals(Optional.of(1), maybeIter.next());
    }

    @Test
    public void canFetchNextFromEmptyIterator() {
        MaybeIterator<Integer> maybeIter = new MaybeIterator<Integer>(new ArrayIterator<Integer>(new Integer[]{}));
        Assert.assertEquals(Optional.empty(), maybeIter.next());
    }
}
