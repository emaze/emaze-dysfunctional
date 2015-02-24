package net.emaze.dysfunctional.options;

import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MaybeTest {

    @Test
    public void justHasValue() {
        final Maybe<Integer> maybeInt = Maybe.just(1);
        Assert.assertTrue(maybeInt.isPresent());
    }

    @Test(expected = IllegalStateException.class)
    public void fetchingValueFromNothingYieldException() {
        Maybe.nothing().get();
    }

    @Test
    public void canFetchValueFromJust() {
        int got = Maybe.just(1).get();
        Assert.assertEquals(1, got);
    }

    @Test
    public void fmapWithJustYieldsJustDelegateResult() {
        final Maybe<Integer> expected = Maybe.just(1);
        final Maybe<Integer> got = expected.map(Function.identity());
        Assert.assertEquals(expected, got);
    }

    @Test
    public void fmapWithJustNothingYieldsNothing() {
        final Maybe<Integer> source = Maybe.nothing();
        final Maybe<Integer> got = source.map(Function.identity());
        Assert.assertFalse(got.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullDelegateYieldsException() {
        final Function<Integer, ?> function = null;
        Maybe.just(1).map(function);
    }

    @Test
    public void foldWithJustYieldsDelegateResult() {
        final Integer expected = 1;
        final Integer got = Maybe.just(expected).fold(null, Function.identity());
        Assert.assertEquals(expected, got);
    }

    @Test
    public void foldWithNothingYieldsProviderResult() {
        final Maybe<Integer> source = Maybe.nothing();
        final Integer expected = 1;
        final Integer got = source.fold(new ConstantSupplier<Integer>(expected), Function.identity());
        Assert.assertEquals(expected, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void foldWithNullDelegateYieldsException() {
        final Function<Integer, ?> function = null;
        Maybe.just(1).fold(null, function);
    }

    @Test
    public void maybeAndUnidentifiedObjectAreNotEquals() {
        Assert.assertFalse(Maybe.just(1).equals(new Object()));
    }

    @Test
    public void maybeWithSameValuesAreEquals() {
        Assert.assertTrue(Maybe.just(1).equals(Maybe.just(1)));
    }

    @Test
    public void maybeWithSameValuesHaveSameHashcode() {
        Assert.assertEquals(Maybe.just(1).hashCode(), Maybe.just(1).hashCode());
    }

    @Test
    public void toStringOfNothingIsNothing() {
        Assert.assertEquals("Nothing", Maybe.nothing().toString());
    }

    @Test
    public void toStringOfJustReflectsValue() {
        Assert.assertEquals("Just 1", Maybe.just(1).toString());
    }

    @Test
    public void transformingNothingToEitherYieldsLeft() {
        final int marker = 0;
        final Either<Integer, Object> either = Maybe.nothing().either(new ConstantSupplier<Integer>(marker));
        Assert.assertEquals(Either.left(marker), either);
    }

    @Test
    public void transformingJustSomethingToEitherYieldsRight() {
        final int left = 0;
        final int right = 1;
        final Either<Integer, Integer> either = Maybe.just(right).either(new ConstantSupplier<Integer>(left));
        Assert.assertEquals(Either.<Integer, Integer>right(right), either);
    }

    @Test
    public void callingOrElseOnJustYieldsJustValue() {
        final O got = Maybe.just(O.ONE).orElse(O.ANOTHER);
        Assert.assertEquals(O.ONE, got);
    }

    @Test
    public void callingOrElseOnNothingYieldsParameter() {
        final O got = Maybe.<O>nothing().orElse(O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, got);
    }

    @Test
    public void nothingYieldsEmptyIterator() {
        Assert.assertFalse(Maybe.nothing().iterator().hasNext());
    }

    @Test
    public void justYieldsSingletonIterator() {
        Assert.assertTrue(Maybe.just(O.ONE).iterator().hasNext());
    }
}
