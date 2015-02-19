package net.emaze.dysfunctional.options;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FmapEitherTest {

    private static final Function<O, O> ID = UnaryOperator.identity();

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
        final Function<O, O> spy = Spies.monitor(UnaryOperator.identity(), calls);
        final FmapEither<O, O, O, O> fmap = new FmapEither<O, O, O, O>(ID, spy);
        final Either<O, O> left = Either.left(O.ONE);
        fmap.apply(left);
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingFmapOnRightDoesntCallLeftDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> spy = Spies.monitor(UnaryOperator.identity(), calls);
        final FmapEither<O, O, O, O> fmap = new FmapEither<O, O, O, O>(spy, ID);
        final Either<O, O> right = Either.right(O.ONE);
        fmap.apply(right);
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingFmapOnLeftCallsLeftDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> spy = Spies.monitor(UnaryOperator.identity(), calls);
        final FmapEither<O, O, O, O> fmap = new FmapEither<O, O, O, O>(spy, ID);
        final Either<O, O> left = Either.left(O.ONE);
        fmap.apply(left);
        Assert.assertEquals(1l, calls.get());
    }

    @Test
    public void callingFmapOnRightCallsRightDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> spy = Spies.monitor(UnaryOperator.identity(), calls);
        final FmapEither<O, O, O, O> fmap = new FmapEither<O, O, O, O>(ID, spy);
        final Either<O, O> right = Either.right(O.ONE);
        fmap.apply(right);
        Assert.assertEquals(1l, calls.get());
    }
}