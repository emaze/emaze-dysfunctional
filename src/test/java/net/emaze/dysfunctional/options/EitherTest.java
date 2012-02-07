package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.delegates.ConstantDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    EitherTest.Degenerations.class,
    EitherTest.Functions.class
})
public class EitherTest {

    public static class Functions {

        @Test
        public void canCreateEitherWithJustRightValue() {
            new Either<Integer, Object>(Maybe.just(1), Maybe.nothing());
        }

        @Test
        public void canCreateEitherWithJustLeftValue() {
            new Either<Object, Integer>(Maybe.nothing(), Maybe.just(1));
        }

        @Test
        public void eitherWithSameValuesHaveSameHashCode() {
            final Either<Object, Integer> a = new Either<Object, Integer>(Maybe.nothing(), Maybe.just(1));
            final Either<Object, Integer> b = new Either<Object, Integer>(Maybe.nothing(), Maybe.just(1));
            Assert.assertEquals(a.hashCode(), b.hashCode());
        }

        @Test
        public void eitherWithSameValuesAreEqual() {
            final Either<Object, Integer> a = new Either<Object, Integer>(Maybe.nothing(), Maybe.just(1));
            final Either<Object, Integer> b = new Either<Object, Integer>(Maybe.nothing(), Maybe.just(1));
            Assert.assertTrue(a.equals(b));
        }

        @Test
        public void eitherWithDifferentValuesAreNotEqual() {
            final Either<Object, Integer> a = new Either<Object, Integer>(Maybe.nothing(), Maybe.just(1));
            final Either<Object, Integer> b = new Either<Object, Integer>(Maybe.nothing(), Maybe.just(2));
            Assert.assertFalse(a.equals(b));
        }

        @Test
        public void eitherIsNotEqualsToUnknownObject() {
            final Either<Object, Integer> either = new Either<Object, Integer>(Maybe.nothing(), Maybe.just(1));
            Assert.assertFalse(either.equals(null));
        }

        @Test
        public void fmapCallsRightDelegateWithRightValue() {
            final Integer rightValue = 1;
            final Either<Object, Integer> either = new Either<Object, Integer>(Maybe.nothing(), Maybe.just(rightValue));
            final Box<Integer> box = new Box<Integer>();
            either.fmap(new Identity<Object>(), Spies.spy1st(new ConstantDelegate<Object, Integer>(null), box));
            Assert.assertEquals(rightValue, box.getContent());
        }

        @Test
        public void fmapCallsLeftDelegateWithLeftValue() {
            final Integer leftValue = 1;
            final Either<Integer, Object> either = new Either<Integer, Object>(Maybe.just(leftValue), Maybe.nothing());
            final Box<Integer> box = new Box<Integer>();
            either.fmap(Spies.spy1st(new ConstantDelegate<Object, Integer>(null), box), new Identity<Object>());
            Assert.assertEquals(leftValue, box.getContent());
        }

        @Test
        public void canFlipAnEither() {
            final Either<String, Integer> either = Either.right(1);
            final Either<Integer, String> flipped = either.flip();
            final Either<Integer, String> expected = Either.left(1);
            Assert.assertEquals(expected, flipped);
        }

        @Test
        public void transformingEitherToMaybeYieldsRightSide() {
            Assert.assertEquals(Maybe.just(1), Either.right(1).maybe());
        }

        @Test
        public void toStringForLeftYieldsLeft() {
            Assert.assertEquals("Left 1", Either.left(1).toString());
        }

        @Test
        public void toStringForRightYieldsRight() {
            Assert.assertEquals("Right 1", Either.right(1).toString());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingEitheWithBothValuesAsNothingYieldsException() {
            new Either<Object, Object>(Maybe.nothing(), Maybe.nothing());
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingEitheWithBothValuesAsJustSomethingYieldsException() {
            new Either<Integer, Integer>(Maybe.just(1), Maybe.just(1));
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingEitheWithNullLeftYieldsException() {
            new Either<Integer, Integer>(null, Maybe.just(1));
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingEitheWithNullRightYieldsException() {
            new Either<Integer, Integer>(Maybe.just(1), null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void fmapWithNullLeftDelegateYieldsException() {
            final Either<O, O> either = Either.right(O.ONE);
            either.fmap(null, new Identity<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void fmapWithNullRightDelegateYieldsException() {
            final Either<O, O> either = Either.right(O.ONE);
            either.fmap(new Identity<O>(), null);
        }
    }
}
