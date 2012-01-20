package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.Options;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.Iterations;
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
    OptionsTest.Transform.class,
    OptionsTest.Lifts.class,
    OptionsTest.Drops.class,
    OptionsTest.Justs.class,
    OptionsTest.LiftAndDrop.class,
    OptionsTest.Facade.class,
    OptionsTest.LeftAndRight.class
})
public class OptionsTest {

    public static class Transform {

        @Test
        public void canTransformAnIteratorOfJusts() {
            final Iterable<Maybe<O>> iter = Collections.singletonList(Maybe.just(O.ONE));
            final Iterator<Maybe<O>> got = Options.transform(iter.iterator(), new Identity<O>());
            Assert.assertEquals(O.ONE, got.next().value());
        }

        @Test
        public void canTransformAnIteratorOfNothing() {
            final Iterable<Maybe<O>> iter = Collections.singletonList(Maybe.<O>nothing());
            final Iterator<Maybe<O>> got = Options.transform(iter.iterator(), new Identity<O>());
            Assert.assertFalse(got.next().hasValue());
        }

        @Test
        public void canTransformAnIterable() {
            final Iterable<Maybe<O>> iter = Collections.singletonList(Maybe.just(O.ONE));
            final Iterator<Maybe<O>> got = Options.transform(iter, new Identity<O>());
            Assert.assertEquals(O.ONE, got.next().value());
        }

        @Test(expected = IllegalArgumentException.class)
        public void transformNullIterableYieldsException() {
            final Iterable<Maybe<O>> iterable = null;
            Options.transform(iterable, new Identity<O>());
        }

        @Test
        public void canTransformAnArray() {
            final Maybe[] array = Collections.singletonList(Maybe.just(O.ONE)).toArray(new Maybe[]{});
            final Iterator<Maybe<O>> got = Options.transform(array, new Identity<O>());
            Assert.assertEquals(O.ONE, got.next().value());
        }
    }

    public static class Lifts {

        @Test
        public void canLiftAnIterator() {
            final Iterator<Maybe<O>> lifts = Options.lifts(Iterations.iterator(O.ONE));
            Assert.assertEquals(O.ONE, lifts.next().value());
        }

        @Test
        public void canLiftAnIterable() {
            final Iterable<O> iter = Collections.singletonList(O.ONE);
            final Iterator<Maybe<O>> lifts = Options.lifts(iter);
            Assert.assertEquals(O.ONE, lifts.next().value());
        }

        @Test
        public void canLiftTwoValues() {
            final Iterator<Maybe<O>> lifts = Options.lifts(O.ONE, O.ANOTHER);
            final List<Maybe<O>> expected = Arrays.asList(Maybe.just(O.ONE), Maybe.just(O.ANOTHER));
            Assert.assertEquals(expected, Consumers.all(lifts));
        }

        @Test
        public void canLiftThreeValues() {
            final Iterator<Maybe<O>> lifts = Options.lifts(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            final List<Maybe<O>> expected = Arrays.asList(Maybe.just(O.ONE), Maybe.just(O.ANOTHER), Maybe.just(O.YET_ANOTHER));
            Assert.assertEquals(expected, Consumers.all(lifts));
        }
    }

    public static class Drops {

        @Test
        public void canDropAnIterator() {
            final Iterator<O> drops = Options.drops(Iterations.iterator(Maybe.just(O.ONE)));
            Assert.assertEquals(O.ONE, drops.next());
        }

        @Test
        public void canDropAnIterable() {
            final Iterable<Maybe<O>> drops = Collections.singletonList(Maybe.just(O.ONE));
            final Iterator<O> lifts = Options.drops(drops);
            Assert.assertEquals(O.ONE, lifts.next());
        }

        @Test
        public void canDropTwoValues() {
            final Iterator<O> drops = Options.drops(Maybe.just(O.ONE), Maybe.just(O.ANOTHER));
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER);
            Assert.assertEquals(expected, Consumers.all(drops));
        }

        @Test
        public void canDropThreeValues() {
            final Iterator<O> drops = Options.drops(Maybe.just(O.ONE), Maybe.just(O.ANOTHER), Maybe.just(O.YET_ANOTHER));
            final List<O> expected = Arrays.asList(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertEquals(expected, Consumers.all(drops));
        }
    }

    public static class Justs {

        @Test
        public void canFetchJustsFromIterable() {
            final Iterable<Maybe<O>> iterable = Collections.singletonList(Maybe.just(O.ONE));
            final Iterator<O> got = Options.justs(iterable);
            Assert.assertEquals(O.ONE, got.next());
        }

        @Test(expected = IllegalArgumentException.class)
        public void fetchingJustsFromNullIterableYieldsException() {
            final Iterable<Maybe<O>> iterable = null;
            Options.justs(iterable);
        }

        @Test
        public void canFetchJustsFromIterator() {
            final Iterator<Maybe<O>> iterator = Iterations.iterator(Maybe.just(O.ONE));
            final Iterator<O> got = Options.justs(iterator);
            Assert.assertEquals(O.ONE, got.next());
        }

        @Test
        public void canFetchJustsFromTwoMaybes() {
            final Iterator<O> got = Options.justs(Maybe.just(O.ONE), Maybe.just(O.ONE));
            Assert.assertEquals(O.ONE, got.next());
        }

        @Test
        public void canFetchJustsFromThreeMaybes() {
            final Iterator<O> got = Options.justs(Maybe.just(O.ONE), Maybe.just(O.ONE), Maybe.just(O.ONE));
            Assert.assertEquals(O.ONE, got.next());
        }
    }

    public static class LiftAndDrop {

        @Test
        public void canLift() {
            final Maybe<Object> lifted = Options.lift(null);
            Assert.assertFalse(lifted.hasValue());
        }

        @Test
        public void canDrop() {
            Object dropped = Options.drop(Maybe.nothing());
            Assert.assertNull(dropped);
        }
    }

    public static class LeftAndRight {

        @Test(expected = IllegalArgumentException.class)
        public void leftsForNullIteratorWillThrow() {
            final Iterator<Either<O, O>> iter = null;
            Options.lefts(iter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void rightsForNullIteratorWillThrow() {
            final Iterator<Either<O, O>> iter = null;
            Options.rights(iter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void leftsForNullIterableWillThrow() {
            final Iterable<Either<O, O>> iter = null;
            Options.lefts(iter);
        }

        @Test(expected = IllegalArgumentException.class)
        public void rightsForNullIterableWillThrow() {
            final Iterable<Either<O, O>> iter = null;
            Options.rights(iter);
        }

        @Test
        public void leftsForEmptyIteratorYieldEmptyIterator() {
            final Iterator<Either<O, O>> iterator = Iterations.iterator();
            Assert.assertFalse(Options.lefts(iterator).hasNext());
        }

        @Test
        public void fetchingLeftsFromIteratorYieldsLeftType() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(leftHasValue);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(Options.lefts(iterator)));
        }

        @Test
        public void fetchingLeftsFromIteratorDoesNotYieldRightTypes() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Either<Integer, Boolean> rightHasValue = Either.right(true);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(leftHasValue, rightHasValue);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(Options.lefts(iterator)));
        }

        @Test
        public void rightsForEmptyIteratorYieldEmptyIterator() {
            final Iterator<Either<O, O>> iterator = Iterations.iterator();
            Assert.assertFalse(Options.rights(iterator).hasNext());
        }

        @Test
        public void fetchingRightsFromIteratorYieldsRightType() {
            final Either<Integer, Boolean> rightHasValue = Either.right(false);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(rightHasValue);
            Assert.assertEquals(Arrays.asList(false), Consumers.all(Options.rights(iterator)));
        }

        @Test
        public void fetchingRightsFromIteratorDoesNotYieldLeftTypes() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Either<Integer, Boolean> rightHasValue = Either.right(true);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(leftHasValue, rightHasValue);
            Assert.assertEquals(Arrays.asList(true), Consumers.all(Options.rights(iterator)));
        }

        @Test
        public void leftsForEmptyIterableYieldEmptyIterator() {
            final Iterable<Either<O, O>> iterable = Iterations.iterable();
            Assert.assertFalse(Options.lefts(iterable).hasNext());
        }

        @Test
        public void fetchingLeftsFromIterableYieldsLeftType() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Iterable<Either<Integer, Boolean>> iterable = Iterations.iterable(leftHasValue);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(Options.lefts(iterable)));
        }

        @Test
        public void fetchingLeftsFromIterableDoesNotYieldRightTypes() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Either<Integer, Boolean> rightHasValue = Either.right(true);
            final Iterable<Either<Integer, Boolean>> iterable = Iterations.iterable(leftHasValue, rightHasValue);
            Assert.assertEquals(Arrays.asList(1), Consumers.all(Options.lefts(iterable)));
        }

        @Test
        public void rightsForEmptyIterableYieldEmptyIterator() {
            final Iterable<Either<O, O>> iterable = Iterations.iterable();
            Assert.assertFalse(Options.rights(iterable).hasNext());
        }

        @Test
        public void fetchingRightsFromIterableYieldsRightType() {
            final Either<Integer, Boolean> rightHasValue = Either.right(false);
            final Iterable<Either<Integer, Boolean>> iterable = Iterations.iterable(rightHasValue);
            Assert.assertEquals(Arrays.asList(false), Consumers.all(Options.rights(iterable)));
        }

        @Test
        public void fetchingRightsFromIterableDoesNotYieldLeftTypes() {
            final Either<Integer, Boolean> leftHasValue = Either.left(1);
            final Either<Integer, Boolean> rightHasValue = Either.right(true);
            final Iterable<Either<Integer, Boolean>> iterable = Iterations.iterable(leftHasValue, rightHasValue);
            Assert.assertEquals(Arrays.asList(true), Consumers.all(Options.rights(iterable)));
        }

        @Test
        public void justNullIsValidLeftValue() {
            final Either<Integer, Boolean> leftHasValue = Either.left(null);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(leftHasValue);
            Assert.assertEquals(Arrays.asList((Integer) null), Consumers.all(Options.lefts(iterator)));
        }

        @Test
        public void justNullIsValidRightValue() {
            final Either<Integer, Boolean> rightHasValue = Either.right(null);
            final Iterator<Either<Integer, Boolean>> iterator = Iterations.iterator(rightHasValue);
            Assert.assertEquals(Arrays.asList((Boolean) null), Consumers.all(Options.rights(iterator)));
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Options() {
            };
        }
    }
}
