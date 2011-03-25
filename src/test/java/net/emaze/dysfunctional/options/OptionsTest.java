package net.emaze.dysfunctional.options;

import java.util.Collections;
import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.iterations.Iterations;
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
        final Iterator<Maybe<O>> iterator = Iterations.iterator(Maybe.just(O.ONE));
        final Iterator<O> got = Options.justs(iterator);
        Assert.assertEquals(O.ONE, got.next());
    }

    @Test
    public void canFetchJustsFromArray() {
        final Maybe[] array = Collections.singletonList(Maybe.just(O.ONE)).toArray(new Maybe[]{});
        final Iterator<O> got = Options.justs(array);
        Assert.assertEquals(O.ONE, got.next());
    }

    @Test
    public void canLift(){
        final Maybe<Object> lifted = Options.lift(null);
        Assert.assertFalse(lifted.hasValue());
    }
    @Test
    public void canDrop(){
        Object dropped = Options.drop(Maybe.nothing());
        Assert.assertNull(dropped);
    }
}
