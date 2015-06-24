package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Function;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.delegates.ConstantFunction;
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
            new Either<Integer, Object>(Optional.of(1), Optional.empty());
        }

        @Test
        public void canCreateEitherWithJustLeftValue() {
            new Either<Object, Integer>(Optional.empty(), Optional.of(1));
        }

        @Test
        public void eitherWithSameValuesHaveSameHashCode() {
            final Either<Object, Integer> a = new Either<Object, Integer>(Optional.empty(), Optional.of(1));
            final Either<Object, Integer> b = new Either<Object, Integer>(Optional.empty(), Optional.of(1));
            Assert.assertEquals(a.hashCode(), b.hashCode());
        }

        @Test
        public void eitherWithSameValuesAreEqual() {
            final Either<Object, Integer> a = new Either<Object, Integer>(Optional.empty(), Optional.of(1));
            final Either<Object, Integer> b = new Either<Object, Integer>(Optional.empty(), Optional.of(1));
            Assert.assertTrue(a.equals(b));
        }

        @Test
        public void eitherWithDifferentValuesAreNotEqual() {
            final Either<Object, Integer> a = new Either<Object, Integer>(Optional.empty(), Optional.of(1));
            final Either<Object, Integer> b = new Either<Object, Integer>(Optional.empty(), Optional.of(2));
            Assert.assertFalse(a.equals(b));
        }

        @Test
        public void eitherIsNotEqualsToUnknownObject() {
            final Either<Object, Integer> either = new Either<Object, Integer>(Optional.empty(), Optional.of(1));
            Assert.assertFalse(either.equals(null));
        }

        @Test
        public void fmapCallsRightDelegateWithRightValue() {
            final Integer rightValue = 1;
            final Either<Object, Integer> either = new Either<Object, Integer>(Optional.empty(), Optional.of(rightValue));
            final Box<Integer> box = new Box<Integer>();
            either.map(Function.identity(), Spies.spy1st(Function.identity(), box));
            Assert.assertEquals(rightValue, box.getContent());
        }

        @Test
        public void fmapCallsLeftDelegateWithLeftValue() {
            final Integer leftValue = 1;
            final Either<Integer, Object> either = new Either<Integer, Object>(Optional.of(leftValue), Optional.empty());
            final Box<Integer> box = new Box<Integer>();
            either.map(Spies.spy1st(Function.identity(), box), Function.identity());
            Assert.assertEquals(leftValue, box.getContent());
        }

        @Test
        public void foldCallsRightDelegateWithRightValue() {
            final Integer rightValue = 1;
            final Either<Object, Integer> either = new Either<Object, Integer>(Optional.empty(), Optional.of(rightValue));
            final Box<Integer> box = new Box<Integer>();
            either.fold(Function.identity(), Spies.spy1st(new ConstantFunction<>(null), box));
            Assert.assertEquals(rightValue, box.getContent());
        }

        @Test
        public void foldCallsLeftDelegateWithLeftValue() {
            final Integer leftValue = 1;
            final Either<Integer, Object> either = new Either<Integer, Object>(Optional.of(leftValue), Optional.empty());
            final Box<Integer> box = new Box<Integer>();
            either.fold(Spies.spy1st(new ConstantFunction<>(null), box), Function.identity());
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
            Assert.assertEquals(Optional.of(1), Either.right(1).right());
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
        public void creatingEitherWithBothValuesAsNothingYieldsException() {
            new Either<Object, Object>(Optional.empty(), Optional.empty());
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingEitherWithBothValuesAsJustSomethingYieldsException() {
            new Either<Integer, Integer>(Optional.of(1), Optional.of(1));
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingEitherWithNullLeftYieldsException() {
            new Either<Integer, Integer>(null, Optional.of(1));
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingEitherWithNullRightYieldsException() {
            new Either<Integer, Integer>(Optional.of(1), null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void fmapWithNullLeftDelegateYieldsException() {
            final Either<O, O> either = Either.right(O.ONE);
            either.map(null, Function.identity());
        }

        @Test(expected = IllegalArgumentException.class)
        public void fmapWithNullRightDelegateYieldsException() {
            final Either<O, O> either = Either.right(O.ONE);
            either.map(Function.identity(), null);
        }
    }
}
