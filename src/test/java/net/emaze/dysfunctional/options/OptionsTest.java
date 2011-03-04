package net.emaze.dysfunctional.options;

import java.util.Collections;
import java.util.Iterator;
import net.emaze.dysfunctional.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class OptionsTest {

    @Test
    public void canTransformAnIteratorOfJusts() {
        final Iterable<Maybe<O>> iter = Collections.singletonList(Maybe.just(O.ONE));
        final Iterator<Maybe<O>> got = Options.transform(iter.iterator(), new Identity<O>());
        Assert.assertEquals(O.ONE, got.next().value());
    }

    @Test
    public void canTransformAnIteratorOfNothing() {
        final Iterable<Maybe<O>> iter = Collections.singletonList(Maybe.<O>nothing());
        final Iterator<Maybe<O>> got = Options.transform(iter.iterator(), new Identity<O>());
        Assert.assertFalse(got.next().hasValue());
    }

    @Test
    public void canTransformAnIterable() {
        final Iterable<Maybe<O>> iter = Collections.singletonList(Maybe.just(O.ONE));
        final Iterator<Maybe<O>> got = Options.transform(iter, new Identity<O>());
        Assert.assertEquals(O.ONE, got.next().value());
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformNullIterableYieldsException() {
        final Iterable<Maybe<O>> iterable = null;
        Options.transform(iterable, new Identity<O>());
    }

    @Test
    public void canTransformAnArray() {
        final Maybe[] array = Collections.singletonList(Maybe.just(O.ONE)).toArray(new Maybe[]{});
        final Iterator<Maybe<O>> got = Options.transform(array, new Identity<O>());
        Assert.assertEquals(O.ONE, got.next().value());
    }

    @Test
    public void canFetchJustsFromIterable() {
        final Iterable<Maybe<O>> iterable = Collections.singletonList(Maybe.just(O.ONE));
        final Iterator<O> got = Options.justs(iterable);
        Assert.assertEquals(O.ONE, got.next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchingJustsFromNullIterableYieldsException() {
        final Iterable<Maybe<O>> iterable = null;
        Options.justs(iterable);
    }

    @Test
    public void canFetchJustsFromIterator() {
        final Iterable<Maybe<O>> iterable = Collections.singletonList(Maybe.just(O.ONE));
        final Iterator<O> got = Options.justs(iterable.iterator());
        Assert.assertEquals(O.ONE, got.next());
    }

    @Test
    public void canFetchJustsFromArray() {
        final Maybe[] array = Collections.singletonList(Maybe.just(O.ONE)).toArray(new Maybe[]{});
        final Iterator<O> got = Options.justs(array);
        Assert.assertEquals(O.ONE, got.next());
    }
}
