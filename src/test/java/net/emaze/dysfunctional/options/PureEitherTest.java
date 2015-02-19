package net.emaze.dysfunctional.options;

import java.util.Optional;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class PureEitherTest {

    @Test
    public void pureYieldsRightEither() {
        final O value = O.ONE;
        final Either<String, O> got = new PureEither<String, O>().apply(value);
        Assert.assertEquals(Optional.of(O.ONE), got.optional());
    }
}