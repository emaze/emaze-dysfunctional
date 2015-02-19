package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class EitherToMaybeTest {

    @Test(expected = IllegalArgumentException.class)
    public void transformingNullEitherYieldsException() {
        new EitherToMaybe<O, O>().apply(null);
    }

    @Test
    public void transformingRightYieldsJustValue() {
        final Either<String, O> right = Either.right(O.ONE);
        final Maybe<O> got = new EitherToMaybe<String, O>().apply(right);
        Assert.assertEquals(Maybe.just(O.ONE), got);
    }

    @Test
    public void transformingLeftYieldsNothing() {
        final Either<String, O> left = Either.left("an error");
        final Maybe<O> got = new EitherToMaybe<String, O>().apply(left);
        Assert.assertEquals(Maybe.<O>nothing(), got);
    }
}