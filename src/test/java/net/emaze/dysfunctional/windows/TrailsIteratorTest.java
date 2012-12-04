package net.emaze.dysfunctional.windows;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.EmptyIterator;
import net.emaze.dysfunctional.options.Maybe;
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
        final Iterator<Queue<Maybe<O>>> iter = new TrailsIterator<Queue<Maybe<O>>, O>(source, 1, new Identity<Queue<Maybe<O>>>());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void lenghtOfIteratorIsSameOfNested() {
        final Iterator<O> source = ArrayIterator.of(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        final Iterator<Queue<Maybe<O>>> iter = new TrailsIterator<Queue<Maybe<O>>, O>(source, 3, new Identity<Queue<Maybe<O>>>());
        Assert.assertEquals(3, Consumers.all(iter).size());
    }

    @Test
    public void firstElementIsPrecededByNothing() {
        final Iterator<O> source = ArrayIterator.of(O.ONE);
        final Iterator<Queue<Maybe<O>>> iter = new TrailsIterator<Queue<Maybe<O>>, O>(source, 2, new Identity<Queue<Maybe<O>>>());
        final Queue<Maybe<O>> got = iter.next();
        Assert.assertEquals(Arrays.asList(Maybe.<O>nothing(), Maybe.<O>just(O.ONE)), got);
    }

    @Test(expected = NoSuchElementException.class)
    public void callingNextOnConsumedIteratorYieldsException() {
        final Iterator<O> source = ArrayIterator.of(O.ONE);
        final Iterator<Queue<Maybe<O>>> iter = new TrailsIterator<Queue<Maybe<O>>, O>(source, 2, new Identity<Queue<Maybe<O>>>());
        iter.next();
        iter.next();
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException() {
        final Iterator<O> source = null;
        new TrailsIterator<Queue<Maybe<O>>, O>(source, 1, new Identity<Queue<Maybe<O>>>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithZeroTrailSizeYieldsException() {
        final Iterator<O> source = new EmptyIterator<O>();
        new TrailsIterator<Queue<Maybe<O>>, O>(source, 0, new Identity<Queue<Maybe<O>>>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNegativeTrailSizeYieldsException() {
        final Iterator<O> source = new EmptyIterator<O>();
        new TrailsIterator<Queue<Maybe<O>>, O>(source, -1, new Identity<Queue<Maybe<O>>>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullCopyDelegateYieldsException() {
        final Iterator<O> source = new EmptyIterator<O>();
        new TrailsIterator<Queue<Maybe<O>>, O>(source, 1, null);
    }
}