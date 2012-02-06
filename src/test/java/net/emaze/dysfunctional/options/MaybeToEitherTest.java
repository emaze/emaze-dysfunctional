package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MaybeToEitherTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new MaybeToEither<O, O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingOnNullMaybeYieldsException() {
        new MaybeToEither<O, O>(new ConstantProvider<O>(O.ANOTHER)).perform(null);
    }

    @Test
    public void transformingNothingYieldsLeft() {
        final Delegate<Either<O, O>, Maybe<O>> maybeToEither = new MaybeToEither<O, O>(new ConstantProvider<O>(O.ANOTHER));
        final Either<O, O> got = maybeToEither.perform(Maybe.<O>nothing());
        Assert.assertEquals(Either.left(O.ANOTHER), got);
    }

    @Test
    public void transformingJustYieldsRight() {
        final Delegate<Either<O, O>, Maybe<O>> maybeToEither = new MaybeToEither<O, O>(new ConstantProvider<O>(O.ANOTHER));
        final Either<O, O> got = maybeToEither.perform(Maybe.<O>just(O.ONE));
        Assert.assertEquals(Either.right(O.ONE), got);
    }
}
