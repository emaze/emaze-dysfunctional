package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.options.Either;
import net.emaze.dysfunctional.options.Maybe;
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
    OptionsTest.Lifts.class,
    OptionsTest.Drops.class,
    OptionsTest.Justs.class,
    OptionsTest.LiftAndDrop.class,
    OptionsTest.Facade.class,
    OptionsTest.LeftAndRight.class,
    OptionsTest.Joins.class,
    OptionsTest.Pures.class
})
public class OptionsTest {

    public static class Lifts {

        @Test
        public void canLiftAnIterator() {
            final Iterator<Maybe<O>> lifts = Options.Maybes.lifts(Iterations.iterator(O.ONE));
            Assert.assertEquals(O.ONE, lifts.next().value());
        }

        @Test
        public void canLiftAnIterable() {
            final Iterable<O> iter = Collections.singletonList(O.ONE);
            final Iterator<Maybe<O>> lifts = Options.Maybes.lifts(iter);
            Assert.assertEquals(O.ONE, lifts.next().value());
        }

        @Test
        public void canLiftTwoValues() {
            final Iterator<Maybe<O>> lifts = Options.Maybes.lifts(O.ONE, O.ANOTHER);
            final Iterable<Maybe<O>> expected = Iterations.iterable(Maybe.just(O.ONE), Maybe.just(O.ANOTHER));
            Assert.assertEquals(Consumers.all(expected), Consumers.all(lifts));
        }

        @Test
        public void canLiftThreeValues() {
            final Iterator<Maybe<O>> lifts = Options.Maybes.lifts(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            final Iterable<Maybe<O>> expected = Iterations.iterable(Maybe.just(O.ONE), Maybe.just(O.ANOTHER), Maybe.just(O.YET_ANOTHER));
            Assert.assertEquals(Consumers.all(expected), Consumers.all(lifts));
        }

        @Test(expected = IllegalArgumentException.class)
        public void liftingNullIterableYieldsException() {
            final Iterable<O> iterable = null;
            Options.Maybes.lifts(iterable);
        }
    }

    public static class Drops {

        @Test
        public void canDropAnIterator() {
            final Iterator<O> drops = Options.Maybes.drops(Iterations.iterator(Maybe.just(O.ONE)));
            Assert.assertEquals(O.ONE, drops.next());
        }

        @Test
        public void canDropAnIterable() {
            final Iterable<Maybe<O>> drops = Collections.singletonList(Maybe.just(O.ONE));
            final Iterator<O> lifts = Options.Maybes.drops(drops);
            Assert.assertEquals(O.ONE, lifts.next());
        }

        @Test
        public void canDropTwoValues() {
            final Iterator<O> drops = Options.Maybes.drops(Maybe.just(O.ONE), Maybe.just(O.ANOTHER));
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER);
            Assert.assertEquals(expected, Consumers.all(drops));
        }

        @Test
        public void canDropThreeValues() {
            final Iterator<O> drops = Options.Maybes.drops(Maybe.just(O.ONE), Maybe.just(O.ANOTHER), Maybe.just(O.YET_ANOTHER));
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertEquals(expected, Consumers.all(drops));
        }

        @Test(expected = IllegalArgumentException.class)
        public void droppingNullIterableYieldsException() {
            final Iterable<Maybe<O>> iterable = null;
            Options.Maybes.drops(iterable);
        }
    }

    public static class Justs {

        @Test
        public void canFetchJustsFromIterable() {
            final Iterable<Maybe<O>> iterable = Collections.singletonList(Maybe.just(O.ONE));
            final Iterator<O> got = Options.Maybes.justs(iterable);
            Assert.assertEquals(O.ONE, got.next());
        }

        @Test(expected = IllegalArgumentException.class)
        public void fetchingJustsFromNullIterableYieldsException() {
            final Iterable<Maybe<O>> iterable = null;
            Options.Maybes.justs(iterable);
        }

        @Test
        public void canFetchJustsFromIterator() {
            final Iterator<Maybe<O>> iterator = Iterations.iterator(Maybe.just(O.ONE));
            final Iterator<O> got = Options.Maybes.justs(iterator);
            Assert.assertEquals(O.ONE, got.next());
        }

        @Test
        public void canFetchJustsFromTwoMaybes() {
            final Iterator<O> got = Options.Maybes.justs(Maybe.just(O.ONE), Maybe.just(O.ONE));
            Assert.assertEquals(O.ONE, got.next());
        }

        @Test
        public void canFetchJustsFromThreeMaybes() {
            final Iterator<O> got = Options.Maybes.justs(Maybe.just(O.ONE), Maybe.just(O.ONE), Maybe.just(O.ONE));
            Assert.assertEquals(O.ONE, got.next());
        }
    }

    public static class LiftAndDrop {

        @Test
        public void canLift() {
            Object anObject = null;
            final Maybe<Object> lifted = Options.Maybes.lift(anObject);
            Assert.assertFalse(lifted.hasValue());
        }

        @Test
        public void canLiftDelegate() {
            Function<Integer, Integer> delegate = UnaryOperator.identity();
            Function<Maybe<Integer>, Maybe<Integer>> lifted = Options.Maybes.lift(delegate);
            Assert.assertNotNull(lifted);
        }

        @Test
        public void canDrop() {
            Object dropped = Options.Maybes.drop(Maybe.nothing());
            Assert.assertNull(dropped);
        }
    }

    public static class LeftAndRight {

        @Test(expected = IllegalArgumentException.class)
        public void leftsForNullIteratorWillThrow() {
            final Iterator<Either<O, O>> iter = null;
            Options.Eithers.lefts(iter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void rightsForNullIteratorWillThrow() {
            final Iterator<Either<O, O>> iter = null;
            Options.Eithers.rights(iter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void leftsForNullIterableWillThrow() {
            final Iterable<Either<O, O>> iter = null;
            Options.Eithers.lefts(iter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void rightsForNullIterableWillThrow() {
            final Iterable<Either<O, O>> iter = null;
            Options.Eithers.rights(iter);
        }

        @Test
        public void leftsForEmptyIteratorYieldEmptyIterator() {
            final Iterator<Either<O, O>> iterator = Iterations.iterator();
            Assert.assertFalse(Options.Eithers.lefts(iterator).hasNext());
        }

        @Test
        public void fetchingLeftsFromIteratorYieldsLeftType() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(leftHasValue);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(Options.Eithers.lefts(iterator)));
        }

        @Test
        public void fetchingLeftsFromIteratorDoesNotYieldRightTypes() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Either<Integer, Boolean> rightHasValue = Either.right(true);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(leftHasValue, rightHasValue);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(Options.Eithers.lefts(iterator)));
        }

        @Test
        public void rightsForEmptyIteratorYieldEmptyIterator() {
            final Iterator<Either<O, O>> iterator = Iterations.iterator();
            Assert.assertFalse(Options.Eithers.rights(iterator).hasNext());
        }

        @Test
        public void fetchingRightsFromIteratorYieldsRightType() {
            final Either<Integer, Boolean> rightHasValue = Either.right(false);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(rightHasValue);
            Assert.assertEquals(Arrays.asList(false), Consumers.all(Options.Eithers.rights(iterator)));
        }

        @Test
        public void fetchingRightsFromIteratorDoesNotYieldLeftTypes() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Either<Integer, Boolean> rightHasValue = Either.right(true);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(leftHasValue, rightHasValue);
            Assert.assertEquals(Arrays.asList(true), Consumers.all(Options.Eithers.rights(iterator)));
        }

        @Test
        public void leftsForEmptyIterableYieldEmptyIterator() {
            final Iterable<Either<O, O>> iterable = Iterations.iterable();
            Assert.assertFalse(Options.Eithers.lefts(iterable).hasNext());
        }

        @Test
        public void fetchingLeftsFromIterableYieldsLeftType() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Iterable<Either<Integer, Boolean>> iterable = Iterations.iterable(leftHasValue);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(Options.Eithers.lefts(iterable)));
        }

        @Test
        public void fetchingLeftsFromIterableDoesNotYieldRightTypes() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Either<Integer, Boolean> rightHasValue = Either.right(true);
            final Iterable<Either<Integer, Boolean>> iterable = Iterations.iterable(leftHasValue, rightHasValue);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(Options.Eithers.lefts(iterable)));
        }

        @Test
        public void rightsForEmptyIterableYieldEmptyIterator() {
            final Iterable<Either<O, O>> iterable = Iterations.iterable();
            Assert.assertFalse(Options.Eithers.rights(iterable).hasNext());
        }

        @Test
        public void fetchingRightsFromIterableYieldsRightType() {
            final Either<Integer, Boolean> rightHasValue = Either.right(false);
            final Iterable<Either<Integer, Boolean>> iterable = Iterations.iterable(rightHasValue);
            Assert.assertEquals(Arrays.asList(false), Consumers.all(Options.Eithers.rights(iterable)));
        }

        @Test
        public void fetchingRightsFromIterableDoesNotYieldLeftTypes() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Either<Integer, Boolean> rightHasValue = Either.right(true);
            final Iterable<Either<Integer, Boolean>> iterable = Iterations.iterable(leftHasValue, rightHasValue);
            Assert.assertEquals(Arrays.asList(true), Consumers.all(Options.Eithers.rights(iterable)));
        }

        @Test
        public void justNullIsValidLeftValue() {
            final Either<Integer, Boolean> leftHasValue = Either.left(null);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(leftHasValue);
            Assert.assertEquals(Arrays.asList((Integer) null), Consumers.all(Options.Eithers.lefts(iterator)));
        }

        @Test
        public void justNullIsValidRightValue() {
            final Either<Integer, Boolean> rightHasValue = Either.right(null);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(rightHasValue);
            Assert.assertEquals(Arrays.asList((Boolean) null), Consumers.all(Options.Eithers.rights(iterator)));
        }
    }

    public static class Pures {

        @Test(expected = IllegalArgumentException.class)
        public void maybePuresOfNullIterableYieldsException() {
            final Iterable<O> iterable = null;
            Options.Maybes.pures(iterable);
        }

        @Test
        public void canEvaluateMaybePuresOfAnIterator() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE);
            final Iterator<Maybe<O>> pures = Options.Maybes.pures(iterator);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateMaybePuresOfAnIterable() {
            final Iterable<O> iterator = Iterations.iterable(O.ONE);
            final Iterator<Maybe<O>> pures = Options.Maybes.pures(iterator);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateMaybePureOfAnElement() {
            final Maybe<O> pure = Options.Maybes.pure(O.ONE);
            Assert.assertNotNull(pure);
        }

        @Test
        public void canEvaluateMaybePuresOfAnElement() {
            final Iterator<Maybe<O>> pures = Options.Maybes.pures(O.ONE);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateMaybePuresOfTwoElements() {
            final Iterator<Maybe<O>> pures = Options.Maybes.pures(O.ONE, O.ANOTHER);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateMaybePuresOfThreeElements() {
            final Iterator<Maybe<O>> pures = Options.Maybes.pures(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateMaybePuresOfManyElements() {
            final Iterator<Maybe<Integer>> pures = Options.Maybes.pures(1, 2, 3, 4);
            Assert.assertNotNull(pures);
        }

        @Test(expected = IllegalArgumentException.class)
        public void boxPuresOfNullIterableYieldsException() {
            final Iterable<O> iterable = null;
            Options.Boxes.pures(iterable);
        }

        @Test
        public void canEvaluateBoxPuresOfAnIterator() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE);
            final Iterator<Box<O>> pures = Options.Boxes.pures(iterator);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateBoxPuresOfAnIterable() {
            final Iterable<O> iterator = Iterations.iterable(O.ONE);
            final Iterator<Box<O>> pures = Options.Boxes.pures(iterator);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateBoxPureOfAnElement() {
            final Box<O> pure = Options.Boxes.pure(O.ONE);
            Assert.assertNotNull(pure);
        }

        @Test
        public void canEvaluateBoxPuresOfAnElement() {
            final Iterator<Box<O>> pures = Options.Boxes.pures(O.ONE);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateBoxPuresOfTwoElements() {
            final Iterator<Box<O>> pures = Options.Boxes.pures(O.ONE, O.ANOTHER);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateBoxPuresOfThreeElements() {
            final Iterator<Box<O>> pures = Options.Boxes.pures(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateBoxPuresOfManyElements() {
            final Iterator<Box<Integer>> pures = Options.Boxes.pures(1, 2, 3, 4);
            Assert.assertNotNull(pures);
        }

        @Test(expected = IllegalArgumentException.class)
        public void eitherPuresOfNullIterableYieldsException() {
            final Iterable<O> iterable = null;
            Options.Eithers.pures(iterable);
        }

        @Test
        public void canEvaluateEitherPuresOfAnIterator() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE);
            final Iterator<Either<String, O>> pures = Options.Eithers.pures(iterator);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateEitherPuresOfAnIterable() {
            final Iterable<O> iterator = Iterations.iterable(O.ONE);
            final Iterator<Either<String, O>> pures = Options.Eithers.pures(iterator);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateEitherPureOfAnElement() {
            final Either<String, O> pure = Options.Eithers.pure(O.ONE);
            Assert.assertNotNull(pure);
        }

        @Test
        public void canEvaluateEitherPuresOfAnElement() {
            final Iterator<Either<String, O>> pures = Options.Eithers.pures(O.ONE);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateEitherPuresOfTwoElements() {
            final Iterator<Either<String, O>> pures = Options.Eithers.pures(O.ONE, O.ANOTHER);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateEitherPuresOfThreeElements() {
            final Iterator<Either<String, O>> pures = Options.Eithers.pures(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertNotNull(pures);
        }

        @Test
        public void canEvaluateEitherPuresOfManyElements() {
            final Iterator<Either<String, Integer>> pures = Options.Eithers.pures(1, 2, 3, 4);
            Assert.assertNotNull(pures);
        }
    }

    public static class Joins {

        @Test
        public void joiningEmptyMaybeYieldsNothing() {
            final Maybe<Maybe<O>> source = Maybe.nothing();
            Assert.assertEquals(Maybe.<O>nothing(), Options.Maybes.join(source));
        }

        @Test
        public void joiningNonEmptyInnerMaybeYieldsJustContent() {
            final Maybe<Maybe<O>> source = Maybe.just(Maybe.just(O.ONE));
            Assert.assertEquals(Maybe.just(O.ONE), Options.Maybes.join(source));
        }

        @Test
        public void joiningEmptyInnerMaybeYieldsNothing() {
            final Maybe<Maybe<O>> source = Maybe.just(Maybe.<O>nothing());
            Assert.assertEquals(Maybe.<O>nothing(), Options.Maybes.join(source));
        }

        @Test
        public void joiningEmptyBoxYieldsNothing() {
            final Box<Box<O>> source = Box.empty();
            Assert.assertEquals(Box.<O>empty(), Options.Boxes.join(source));
        }

        @Test
        public void joiningNonEmptyInnerBoxYieldsJustContent() {
            final Box<Box<O>> source = Box.of(Box.of(O.ONE));
            Assert.assertEquals(Box.of(O.ONE), Options.Boxes.join(source));
        }

        @Test
        public void joiningEmptyInnerBoxYieldsNothing() {
            final Box<Box<O>> source = Box.of(Box.<O>empty());
            Assert.assertEquals(Box.<O>empty(), Options.Boxes.join(source));
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Options() {
            };
        }

        @Test
        public void maybesFacadeIsNotFinal() {
            new Options.Maybes() {
            };
        }

        @Test
        public void boxesFacadeIsNotFinal() {
            new Options.Boxes() {
            };
        }

        @Test
        public void eithersFacadeIsNotFinal() {
            new Options.Eithers() {
            };
        }
    }
}
