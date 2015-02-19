package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
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
        Assert.assertTrue(maybeInt.hasValue());
    }

    @Test(expected = IllegalStateException.class)
    public void fetchingValueFromNothingYieldException() {
        Maybe.nothing().value();
    }

    @Test
    public void canFetchValueFromJust() {
        int got = Maybe.just(1).value();
        Assert.assertEquals(1, got);
    }

    @Test
    public void fmapWithJustYieldsJustDelegateResult() {
        final Maybe<Integer> expected = Maybe.just(1);
        final Maybe<Integer> got = expected.fmap(new Identity<Integer>());
        Assert.assertEquals(expected, got);
    }

    @Test
    public void fmapWithJustNothingYieldsNothing() {
        final Maybe<Integer> source = Maybe.nothing();
        final Maybe<Integer> got = source.fmap(new Identity<Integer>());
        Assert.assertFalse(got.hasValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullDelegateYieldsException() {
        final Function<Integer, ?> delegate = null;
        Maybe.just(1).fmap(delegate);
    }

    @Test
    public void foldWithJustYieldsDelegateResult() {
        final Integer expected = 1;
        final Integer got = Maybe.just(expected).fold(null, new Identity<Integer>());
        Assert.assertEquals(expected, got);
    }

    @Test
    public void foldWithNothingYieldsProviderResult() {
        final Maybe<Integer> source = Maybe.nothing();
        final Integer expected = 1;
        final Integer got = source.fold(new ConstantProvider<Integer>(expected), new Identity<Integer>());
        Assert.assertEquals(expected, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void foldWithNullDelegateYieldsException() {
        final Function<Integer, ?> delegate = null;
        Maybe.just(1).fold(null, delegate);
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
        final Either<Integer, Object> either = Maybe.nothing().either(new ConstantProvider<Integer>(marker));
        final Maybe<Integer> perform = new MaybeLeft<Integer, Object>().apply(either);
        Assert.assertEquals(marker, perform.value().intValue());
    }

    @Test
    public void transformingJustSomethingToEitherYieldsRight() {
        final int left = 0;
        final int right = 1;
        final Either<Integer, Integer> either = Maybe.just(right).either(new ConstantProvider<Integer>(left));
        final Maybe<Integer> perform = new MaybeRight<Integer, Integer>().apply(either);
        Assert.assertEquals(right, perform.value().intValue());
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
