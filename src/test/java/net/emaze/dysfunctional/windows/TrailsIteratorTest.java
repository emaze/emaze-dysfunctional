package net.emaze.dysfunctional.windows;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Function;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.EmptyIterator;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TrailsIteratorTest {

    @Test
    public void emptyIteratorHasNoNext() {
        final Iterator<O> source = new EmptyIterator<O>();
        final Iterator<Queue<Optional<O>>> iter = new TrailsIterator<>(source, 1, Function.identity());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void lenghtOfIteratorIsSameOfNested() {
        final Iterator<O> source = ArrayIterator.of(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        final Iterator<Queue<Optional<O>>> iter = new TrailsIterator<>(source, 3, Function.identity());
        Assert.assertEquals(3, Consumers.all(iter).size());
    }

    @Test
    public void firstElementIsPrecededByNothing() {
        final Iterator<O> source = ArrayIterator.of(O.ONE);
        final Iterator<Queue<Optional<O>>> iter = new TrailsIterator<>(source, 2, Function.identity());
        final Queue<Optional<O>> got = iter.next();
        Assert.assertEquals(Arrays.asList(Optional.<O>empty(), Optional.<O>of(O.ONE)), got);
    }

    @Test(expected = NoSuchElementException.class)
    public void callingNextOnConsumedIteratorYieldsException() {
        final Iterator<O> source = ArrayIterator.of(O.ONE);
        final Iterator<Queue<Optional<O>>> iter = new TrailsIterator<>(source, 2, Function.identity());
        iter.next();
        iter.next();
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException() {
        final Iterator<O> source = null;
        new TrailsIterator<>(source, 1, Function.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithZeroTrailSizeYieldsException() {
        final Iterator<O> source = new EmptyIterator<O>();
        new TrailsIterator<>(source, 0, Function.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNegativeTrailSizeYieldsException() {
        final Iterator<O> source = new EmptyIterator<O>();
        new TrailsIterator<>(source, -1, Function.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullCopyDelegateYieldsException() {
        final Iterator<O> source = new EmptyIterator<O>();
        new TrailsIterator<O, Queue<Optional<O>>>(source, 1, null);
    }
}