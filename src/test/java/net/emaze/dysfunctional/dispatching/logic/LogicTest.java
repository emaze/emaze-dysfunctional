package net.emaze.dysfunctional.dispatching.logic;

import java.util.Arrays;
import java.util.Iterator;
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
    LogicTest.AndTest.class,
    LogicTest.OrTest.class,
    LogicTest.AndDegenerationsTest.class,
    LogicTest.OrDegenerationsTest.class,
    LogicTest.NotTest.class,
    LogicTest.AlwaysTest.class,
    LogicTest.NeverTest.class
})
public class LogicTest {

    public static class AndTest {

        @Test
        public void canComposeIteratorOfPredicates() {
            final Iterable<Predicate<O>> preds = Arrays.asList(new Always<O>(), new Never<O>());
            Assert.assertFalse(Logic.and(preds.iterator()).accept(O.IGNORED));
        }

        @Test
        public void canComposeIterableOfPredicates() {
            final Iterable<Predicate<O>> preds = Arrays.asList(new Always<O>(), new Never<O>());
            Assert.assertFalse(Logic.and(preds).accept(O.IGNORED));
        }

        @Test
        public void canComposeArrayOfPredicates() {
            Assert.assertFalse(Logic.and(new Always<O>(), new Never<O>()).accept(O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfBinaryPredicates() {
            final Iterable<BinaryPredicate<O, O>> preds = Arrays.asList(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertFalse(Logic.and2(preds.iterator()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfBinaryPredicates() {
            final Iterable<BinaryPredicate<O, O>> preds = Arrays.asList(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertFalse(Logic.and2(preds).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeArrayOfBinaryPredicates() {
            Assert.assertFalse(Logic.and2(new BinaryAlways<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfTernaryPredicates() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Arrays.asList(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertFalse(Logic.and3(preds.iterator()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfTernaryPredicates() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Arrays.asList(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertFalse(Logic.and3(preds).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeArrayOfTernaryPredicates() {
            Assert.assertFalse(Logic.and3(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }
    }

    public static class OrTest {

        @Test
        public void canComposeIteratorOfPredicates() {
            final Iterable<Predicate<O>> preds = Arrays.asList(new Always<O>(), new Never<O>());
            Assert.assertTrue(Logic.or(preds.iterator()).accept(O.IGNORED));
        }

        @Test
        public void canComposeIterableOfPredicates() {
            final Iterable<Predicate<O>> preds = Arrays.asList(new Always<O>(), new Never<O>());
            Assert.assertTrue(Logic.or(preds).accept(O.IGNORED));
        }

        @Test
        public void canComposeArrayOfPredicates() {
            Assert.assertTrue(Logic.or(new Always<O>(), new Never<O>()).accept(O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfBinaryPredicates() {
            final Iterable<BinaryPredicate<O, O>> preds = Arrays.asList(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertTrue(Logic.or2(preds.iterator()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfBinaryPredicates() {
            final Iterable<BinaryPredicate<O, O>> preds = Arrays.asList(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertTrue(Logic.or2(preds).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeArrayOfBinaryPredicates() {
            Assert.assertTrue(Logic.or2(new BinaryAlways<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfTernaryPredicates() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Arrays.asList(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertTrue(Logic.or3(preds.iterator()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfTernaryPredicates() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Arrays.asList(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertTrue(Logic.or3(preds).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeArrayOfTernaryPredicates() {
            Assert.assertTrue(Logic.or3(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }
    }

    public static class AndDegenerationsTest {

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfPredicatesYieldsException() {
            final Iterator<Predicate<O>> preds = null;
            Logic.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfPredicatesYieldsException() {
            final Iterable<Predicate<O>> preds = null;
            Logic.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfBinaryPredicatesYieldsException() {
            final Iterator<BinaryPredicate<O, O>> preds = null;
            Logic.and2(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfBinaryPredicatesYieldsException() {
            final Iterable<BinaryPredicate<O, O>> preds = null;
            Logic.and2(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullPredicatesYieldsException() {
            Logic.and2(null, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfTernaryPredicatesYieldsException() {
            final Iterator<TernaryPredicate<O, O, O>> preds = null;
            Logic.and3(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfTernaryPredicatesYieldsException() {
            final Iterable<TernaryPredicate<O, O, O>> preds = null;
            Logic.and3(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullTernaryPredicatesYieldsException() {
            Logic.and3(null, null);
        }
    }

    public static class OrDegenerationsTest {

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfPredicatesYieldsException() {
            final Iterator<Predicate<O>> preds = null;
            Logic.or(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfPredicatesYieldsException() {
            final Iterable<Predicate<O>> preds = null;
            Logic.or(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullArrayOfPredicatesYieldsException() {
            Logic.or(null, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfBinaryPredicatesYieldsException() {
            final Iterator<BinaryPredicate<O, O>> preds = null;
            Logic.or2(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfBinaryPredicatesYieldsException() {
            final Iterable<BinaryPredicate<O, O>> preds = null;
            Logic.or2(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullArrayOfBinaryPredicatesYieldsException() {
            Logic.or2(null, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfTernaryPredicatesYieldsException() {
            final Iterator<TernaryPredicate<O, O, O>> preds = null;
            Logic.or3(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfTernaryPredicatesYieldsException() {
            final Iterable<TernaryPredicate<O, O, O>> preds = null;
            Logic.or3(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullArrayOfTernaryPredicatesYieldsException() {
            Logic.or3(null, null);
        }
    }

    public static class NotTest {

        @Test
        public void canNegatePredicate() {
            final Predicate<O> negated = Logic.not(new Always<O>());
            Assert.assertFalse(negated.accept(null));
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
            Assert.assertNotNull(Logic.always());
        }

        @Test
        public void canCreateBinaryAlways() {
            Assert.assertNotNull(Logic.always2());
        }

        @Test
        public void canCreateTernaryAlways() {
            Assert.assertNotNull(Logic.always3());
        }
    }

    public static class NeverTest {

        @Test
        public void canCreateNever() {
            Assert.assertNotNull(Logic.never());
        }

        @Test
        public void canCreateBinaryNever() {
            Assert.assertNotNull(Logic.never2());
        }

        @Test
        public void canCreateTernaryNever() {
            Assert.assertNotNull(Logic.never3());
        }
    }
}
