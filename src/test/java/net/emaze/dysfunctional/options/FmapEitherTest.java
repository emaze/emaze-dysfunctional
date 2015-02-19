package net.emaze.dysfunctional.options;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FmapEitherTest {

    private static final Function<O, O> ID = new Identity<O>();

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullLeftDelegateYieldsException() {
        new FmapEither<O, O, O, O>(null, ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullRightDelegateYieldsException() {
        new FmapEither<O, O, O, O>(ID, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullEitherYieldsException() {
        new FmapEither<O, O, O, O>(ID, ID).apply(null);
    }

    @Test
    public void callingFmapOnLeftDoesntCallRightDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> spy = Spies.monitor(new Identity<O>(), calls);
        final FmapEither<O, O, O, O> fmap = new FmapEither<O, O, O, O>(ID, spy);
        final Either<O, O> left = Either.left(O.ONE);
        fmap.apply(left);
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingFmapOnRightDoesntCallLeftDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> spy = Spies.monitor(new Identity<O>(), calls);
        final FmapEither<O, O, O, O> fmap = new FmapEither<O, O, O, O>(spy, ID);
        final Either<O, O> right = Either.right(O.ONE);
        fmap.apply(right);
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingFmapOnLeftCallsLeftDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> spy = Spies.monitor(new Identity<O>(), calls);
        final FmapEither<O, O, O, O> fmap = new FmapEither<O, O, O, O>(spy, ID);
        final Either<O, O> left = Either.left(O.ONE);
        fmap.apply(left);
        Assert.assertEquals(1l, calls.get());
    }

    @Test
    public void callingFmapOnRightCallsRightDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> spy = Spies.monitor(new Identity<O>(), calls);
        final FmapEither<O, O, O, O> fmap = new FmapEither<O, O, O, O>(ID, spy);
        final Either<O, O> right = Either.right(O.ONE);
        fmap.apply(right);
        Assert.assertEquals(1l, calls.get());
    }
}