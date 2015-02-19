package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Never;
import java.util.function.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    LogicTest.UnaryTest.class,
    LogicTest.BinaryTest.class,
    LogicTest.TernaryTest.class,
    LogicTest.AndTest.class,
    LogicTest.OrTest.class,
    LogicTest.AndDegenerationsTest.class,
    LogicTest.OrDegenerationsTest.class,
    LogicTest.NotTest.class,
    LogicTest.AlwaysTest.class,
    LogicTest.NeverTest.class,
    LogicTest.FacadeTest.class
})
public class LogicTest {

    public static class UnaryTest {

        @Test
        public void canComposeIteratorOfPredicatesWithAnd() {
            final Iterable<Predicate<O>> preds = Iterations.iterable(new Always<O>(), new Never<O>());
            Assert.assertFalse(Logic.Unary.and(preds.iterator()).test(O.IGNORED));
        }

        @Test
        public void canComposeIterableOfPredicatesWithAnd() {
            final Iterable<Predicate<O>> preds = Iterations.iterable(new Always<O>(), new Never<O>());
            Assert.assertFalse(Logic.Unary.and(preds).test(O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfPredicatesWithOr() {
            final Iterable<Predicate<O>> preds = Iterations.iterable(new Always<O>(), new Never<O>());
            Assert.assertTrue(Logic.Unary.or(preds.iterator()).test(O.IGNORED));
        }

        @Test
        public void canComposeIterableOfPredicatesWithOr() {
            final Iterable<Predicate<O>> preds = Iterations.iterable(new Always<O>(), new Never<O>());
            Assert.assertTrue(Logic.Unary.or(preds).test(O.IGNORED));
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfPredicatesWithAndYieldsException() {
            final Iterator<Predicate<O>> preds = null;
            Logic.Unary.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfPredicatesWithAndYieldsException() {
            final Iterable<Predicate<O>> preds = null;
            Logic.Unary.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfPredicatesWithOrYieldsException() {
            final Iterator<Predicate<O>> preds = null;
            Logic.Unary.or(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfPredicatesWithOrYieldsException() {
            final Iterable<Predicate<O>> preds = null;
            Logic.Unary.or(preds);
        }
    }

    public static class BinaryTest {

        @Test
        public void canComposeIteratorOfBinaryPredicatesWithAnd() {
            final Iterable<BinaryPredicate<O, O>> preds = Iterations.iterable(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertFalse(Logic.Binary.and(preds.iterator()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfBinaryPredicatesWithAnd() {
            final Iterable<BinaryPredicate<O, O>> preds = Iterations.iterable(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertFalse(Logic.Binary.and(preds).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfBinaryPredicatesWithOr() {
            final Iterable<BinaryPredicate<O, O>> preds = Iterations.iterable(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertTrue(Logic.Binary.or(preds.iterator()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfBinaryPredicatesWithOr() {
            final Iterable<BinaryPredicate<O, O>> preds = Iterations.iterable(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertTrue(Logic.Binary.or(preds).accept(O.IGNORED, O.IGNORED));
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfBinaryPredicatesWithAndYieldsException() {
            final Iterator<BinaryPredicate<O, O>> preds = null;
            Logic.Binary.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfBinaryPredicatesWithAndYieldsException() {
            final Iterable<BinaryPredicate<O, O>> preds = null;
            Logic.Binary.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfBinaryPredicatesWithOrYieldsException() {
            final Iterator<BinaryPredicate<O, O>> preds = null;
            Logic.Binary.or(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfBinaryPredicatesWithOrYieldsException() {
            final Iterable<BinaryPredicate<O, O>> preds = null;
            Logic.Binary.or(preds);
        }
    }

    public static class TernaryTest {

        @Test
        public void canComposeIteratorOfTernaryPredicatesWithAnd() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Iterations.iterable(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertFalse(Logic.Ternary.and(preds.iterator()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfTernaryPredicatesWithAnd() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Iterations.iterable(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertFalse(Logic.Ternary.and(preds).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfTernaryPredicatesWithOr() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Iterations.iterable(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertTrue(Logic.Ternary.or(preds.iterator()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfTernaryPredicatesWithOr() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Iterations.iterable(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertTrue(Logic.Ternary.or(preds).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfTernaryPredicatesWithAndYieldsException() {
            final Iterator<TernaryPredicate<O, O, O>> preds = null;
            Logic.Ternary.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfTernaryPredicatesWithAndYieldsException() {
            final Iterable<TernaryPredicate<O, O, O>> preds = null;
            Logic.Ternary.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfTernaryPredicatesWithOrYieldsException() {
            final Iterator<TernaryPredicate<O, O, O>> preds = null;
            Logic.Ternary.or(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfTernaryPredicatesWithOrYieldsException() {
            final Iterable<TernaryPredicate<O, O, O>> preds = null;
            Logic.Ternary.or(preds);
        }
    }

    public static class AndTest {

        @Test
        public void canComposeTwoPredicates() {
            Assert.assertFalse(Logic.and(new Always<O>(), new Never<O>()).test(O.IGNORED));
        }

        @Test
        public void canComposeThreePredicates() {
            Assert.assertFalse(Logic.and(new Always<O>(), new Never<O>(), new Never<O>()).test(O.IGNORED));
        }

        @Test
        public void canComposeManyPredicates() {
            final Never<O> never = new Never<O>();
            Assert.assertFalse(Logic.and(new Always<O>(), never, never, never).test(O.IGNORED));
        }

        @Test
        public void canComposeTwoBinaryPredicates() {
            Assert.assertFalse(Logic.and(new BinaryAlways<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeThreeBinaryPredicates() {
            Assert.assertFalse(Logic.and(new BinaryAlways<O, O>(), new BinaryNever<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeManyBinaryPredicates() {
            final BinaryNever<O, O> never = new BinaryNever<O, O>();
            Assert.assertFalse(Logic.and(new BinaryAlways<O, O>(), never, never, never).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeTwoTernaryPredicates() {
            Assert.assertFalse(Logic.and(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeThreeTernaryPredicates() {
            Assert.assertFalse(Logic.and(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeManyTernaryPredicates() {
            final TernaryNever<O, O, O> never = new TernaryNever<O, O, O>();
            Assert.assertFalse(Logic.and(new TernaryAlways<O, O, O>(), never, never, never).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }
    }

    public static class OrTest {

        @Test
        public void canComposeTwoPredicates() {
            Assert.assertTrue(Logic.or(new Always<O>(), new Never<O>()).test(O.IGNORED));
        }

        @Test
        public void canComposeThreePredicates() {
            Assert.assertTrue(Logic.or(new Always<O>(), new Always<O>(), new Never<O>()).test(O.IGNORED));
        }

        @Test
        public void canComposeManyPredicates() {
            final Never<O> never = new Never<O>();
            Assert.assertTrue(Logic.or(new Always<O>(), new Always<O>(), never, never).test(O.IGNORED));
        }

        @Test
        public void canComposeTwoBinaryPredicates() {
            Assert.assertTrue(Logic.or(new BinaryAlways<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeThreeBinaryPredicates() {
            Assert.assertTrue(Logic.or(new BinaryAlways<O, O>(), new BinaryAlways<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeManyBinaryPredicates() {
            final BinaryNever<O, O> never = new BinaryNever<O, O>();
            Assert.assertTrue(Logic.or(new BinaryAlways<O, O>(), new BinaryAlways<O, O>(), never, never).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeTwoTernaryPredicates() {
            Assert.assertTrue(Logic.or(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeThreeTernaryPredicates() {
            Assert.assertTrue(Logic.or(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeManyTernaryPredicates() {
            final TernaryNever<O, O, O> never = new TernaryNever<O, O, O>();
            Assert.assertTrue(Logic.or(new TernaryAlways<O, O, O>(), never, never, never).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }
    }

    public static class AndDegenerationsTest {

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoPredicatesYieldsExceptionWhenFormerIsNull() {
            final Predicate<O> pred = null;
            Logic.and(pred, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoPredicatesYieldsExceptionWhenLatterIsNull() {
            final Predicate<O> pred = null;
            Logic.and(new Always<O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenFirstIsNull() {
            final Predicate<O> pred = null;
            Logic.and(pred, new Always<O>(), new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenSecondIsNull() {
            final Predicate<O> pred = null;
            Logic.and(new Always<O>(), pred, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenThirdIsNull() {
            final Predicate<O> pred = null;
            Logic.and(new Always<O>(), new Always<O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoBinaryPredicatesYieldsExceptionWhenFormerIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.and(pred, new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoBinaryPredicatesYieldsExceptionWhenLatterIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.and(new BinaryAlways<O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenFirstIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.and(pred, new BinaryAlways<O, O>(), new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenSecondIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.and(new BinaryAlways<O, O>(), pred, new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenThirdIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.and(new BinaryAlways<O, O>(), new BinaryAlways<O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoTernaryPredicatesYieldsExceptionWhenFormerIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.and(pred, new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoTernaryPredicatesYieldsExceptionWhenLatterIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.and(new TernaryAlways<O, O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenFirstIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.and(pred, new TernaryAlways<O, O, O>(), new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenSecondIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.and(new TernaryAlways<O, O, O>(), pred, new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenThirdIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.and(new TernaryAlways<O, O, O>(), new TernaryAlways<O, O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingManyPredicatesYieldsExceptionWhenArrayIsNull() {
            final Predicate<O>[] pred = null;
            Logic.and(pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingManyBinaryPredicatesYieldsExceptionWhenArrayIsNull() {
            final BinaryPredicate<O, O>[] pred = null;
            Logic.and(pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingManyTernaryPredicatesYieldsExceptionWhenArrayIsNull() {
            final TernaryPredicate<O, O, O>[] pred = null;
            Logic.and(pred);
        }
    }

    public static class OrDegenerationsTest {

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoPredicatesYieldsExceptionWhenFormerIsNull() {
            final Predicate<O> pred = null;
            Logic.or(pred, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoPredicatesYieldsExceptionWhenLatterIsNull() {
            final Predicate<O> pred = null;
            Logic.or(new Always<O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenFirstIsNull() {
            final Predicate<O> pred = null;
            Logic.or(pred, new Always<O>(), new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenSecondIsNull() {
            final Predicate<O> pred = null;
            Logic.or(new Always<O>(), pred, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenThirdIsNull() {
            final Predicate<O> pred = null;
            Logic.or(new Always<O>(), new Always<O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoBinaryPredicatesYieldsExceptionWhenFormerIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.or(pred, new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoBinaryPredicatesYieldsExceptionWhenLatterIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.or(new BinaryAlways<O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenFirstIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.or(pred, new BinaryAlways<O, O>(), new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenSecondIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.or(new BinaryAlways<O, O>(), pred, new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenThirdIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Logic.or(new BinaryAlways<O, O>(), new BinaryAlways<O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoTernaryPredicatesYieldsExceptionWhenFormerIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.or(pred, new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoTernaryPredicatesYieldsExceptionWhenLatterIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.or(new TernaryAlways<O, O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenFirstIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.or(pred, new TernaryAlways<O, O, O>(), new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenSecondIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.or(new TernaryAlways<O, O, O>(), pred, new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenThirdIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.or(new TernaryAlways<O, O, O>(), new TernaryAlways<O, O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingManyPredicatesYieldsExceptionWhenArrayIsNull() {
            final Predicate<O>[] pred = null;
            Logic.or(pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingManyBinaryPredicatesYieldsExceptionWhenArrayIsNull() {
            final BinaryPredicate<O, O>[] pred = null;
            Logic.or(pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingManyTernaryPredicatesYieldsExceptionWhenArrayIsNull() {
            final TernaryPredicate<O, O, O>[] pred = null;
            Logic.or(pred);
        }
    }

    public static class NotTest {

        @Test
        public void canNegatePredicate() {
            final Predicate<O> negated = Logic.not(new Always<O>());
            Assert.assertFalse(negated.test(null));
        }

        @Test
        public void canNegateBinaryPredicate() {
            final BinaryPredicate<O, O> negated = Logic.not(new BinaryAlways<O, O>());
            Assert.assertFalse(negated.accept(null, null));
        }

        @Test
        public void canNegateTernaryPredicate() {
            final TernaryPredicate<O, O, O> negated = Logic.not(new TernaryAlways<O, O, O>());
            Assert.assertFalse(negated.accept(null, null, null));
        }

        @Test(expected = IllegalArgumentException.class)
        public void negatingNullPredicateYieldsException() {
            final Predicate<O> pred = null;
            Logic.not(pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negatingBinaryNullPredicateYieldsException() {
            final BinaryPredicate<O, O> pred = null;
            Logic.not(pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negatingTernaryNullPredicateYieldsException() {
            final TernaryPredicate<O, O, O> pred = null;
            Logic.not(pred);
        }
    }

    public static class AlwaysTest {

        @Test
        public void canCreateAlways() {
            Assert.assertNotNull(Logic.Unary.always());
        }

        @Test
        public void canCreateBinaryAlways() {
            Assert.assertNotNull(Logic.Binary.always());
        }

        @Test
        public void canCreateTernaryAlways() {
            Assert.assertNotNull(Logic.Ternary.always());
        }
    }

    public static class NeverTest {

        @Test
        public void canCreateNever() {
            Assert.assertNotNull(Logic.Unary.never());
        }

        @Test
        public void canCreateBinaryNever() {
            Assert.assertNotNull(Logic.Binary.never());
        }

        @Test
        public void canCreateTernaryNever() {
            Assert.assertNotNull(Logic.Ternary.never());
        }
    }

    public static class FacadeTest {

        @Test
        public void logicIsNotFinal() {
            new Logic() {
            };
        }

        @Test
        public void unaryIsNotFinal() {
            new Logic.Unary() {
            };
        }

        @Test
        public void binaryIsNotFinal() {
            new Logic.Binary() {
            };
        }

        @Test
        public void ternaryIsNotFinal() {
            new Logic.Ternary() {
            };
        }
    }
}
