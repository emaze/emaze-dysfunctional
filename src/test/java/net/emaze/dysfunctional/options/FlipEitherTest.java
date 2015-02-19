package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FlipEitherTest {

    @Test(expected = IllegalArgumentException.class)
    public void flippingNullEitherYieldsException() {
        new FlipEither<String, O>().apply(null);
    }

    @Test
    public void flippingRightYieldsLeft() {
        final Either<String, O> source = Either.right(O.ONE);
        final Either<O, String> got = new FlipEither<String, O>().apply(source);
        Assert.assertEquals(Either.left(O.ONE), got);
    }

    @Test
    public void flippingLeftYieldsRight() {
        final Either<String, O> source = Either.left("an error");
        final Either<O, String> got = new FlipEither<String, O>().apply(source);
        Assert.assertEquals(Either.right("an error"), got);
    }
}