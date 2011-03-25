package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Identity;
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
    public void withValueWithJustYieldsJustDelegateResult() {
        final Maybe<Integer> expected = Maybe.just(1);
        final Maybe<Integer> got = expected.withValue(new Identity<Integer>());
        Assert.assertEquals(expected, got);
    }

    @Test
    public void withValueWithJustNothingYieldsNothing() {
        final Maybe<Integer> source = Maybe.nothing();
        final Maybe<Integer> got = source.withValue(new Identity<Integer>());
        Assert.assertFalse(got.hasValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void withValueWithNullDelegateYieldsException() {
        Maybe.just(1).withValue(null);
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
}
