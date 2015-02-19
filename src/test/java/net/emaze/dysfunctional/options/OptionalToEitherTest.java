package net.emaze.dysfunctional.options;

import java.util.Optional;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import java.util.function.Function;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class OptionalToEitherTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new OptionalToEither<O, O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingOnNullMaybeYieldsException() {
        new OptionalToEither<O, O>(new ConstantProvider<O>(O.ANOTHER)).apply(null);
    }

    @Test
    public void transformingNothingYieldsLeft() {
        final Function<Optional<O>, Either<O, O>> maybeToEither = new OptionalToEither<O, O>(new ConstantProvider<O>(O.ANOTHER));
        final Either<O, O> got = maybeToEither.apply(Optional.<O>empty());
        Assert.assertEquals(Either.left(O.ANOTHER), got);
    }

    @Test
    public void transformingJustYieldsRight() {
        final Function<Optional<O>, Either<O, O>> maybeToEither = new OptionalToEither<O, O>(new ConstantProvider<O>(O.ANOTHER));
        final Either<O, O> got = maybeToEither.apply(Optional.<O>of(O.ONE));
        Assert.assertEquals(Either.right(O.ONE), got);
    }
}
