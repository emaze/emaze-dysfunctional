package net.emaze.dysfunctional.dispatching;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.BinaryIdentity;
import net.emaze.dysfunctional.tuples.Pair;
import net.emaze.dysfunctional.tuples.TernaryIdentity;
import net.emaze.dysfunctional.tuples.Triple;
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
    DispatchingTest.Adapting.class,
    DispatchingTest.CurryDelegates.class,
    DispatchingTest.CurryPredicates.class,
    DispatchingTest.CurryActions.class,
    DispatchingTest.Ignoring.class,
    DispatchingTest.Composing.class,
    DispatchingTest.AndTest.class,
    DispatchingTest.OrTest.class,
    DispatchingTest.AndDegenerationsTest.class,
    DispatchingTest.OrDegenerationsTest.class,
    DispatchingTest.NotTest.class,
    DispatchingTest.AlwaysTest.class,
    DispatchingTest.NeverTest.class,
    DispatchingTest.Facade.class
})
public class DispatchingTest {

    public static class Adapting {

        @Test
        public void canAdaptActionToDelegate() {
            final Delegate<Void, O> adapted = Dispatching.delegate(new Noop<O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptBinaryActionToBinaryDelegate() {
            final BinaryDelegate<Void, O, O> adapted = Dispatching.delegate(new BinaryNoop<O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptTernaryActionToTernaryDelegate() {
            final TernaryDelegate<Void, O, O, O> adapted = Dispatching.delegate(new TernaryNoop<O, O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptPredicateToDelegate() {
            final Delegate<Boolean, O> adapted = Dispatching.delegate(new Always<O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptBinaryPredicateToBinaryDelegate() {
            final BinaryDelegate<Boolean, O, O> adapted = Dispatching.delegate(new BinaryAlways<O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptTernaryPredicateToTernaryDelegate() {
            final TernaryDelegate<Boolean, O, O, O> adapted = Dispatching.delegate(new TernaryAlways<O, O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptDelegateToAction() {
            final Action<O> adapted = Dispatching.action(new Identity<O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptBinaryDelegateToBinaryAction() {
            final BinaryAction<O, O> adapted = Dispatching.action(new FirstParam<O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptTernaryDelegateToTernaryAction() {
            final TernaryAction<O, O, O> adapted = Dispatching.action(new FirstParamOfThree<O, O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptDelegateToPredicate() {
            final Predicate<O> adapted = Dispatching.predicate(new Delegate<Boolean, O>() {

                @Override
                public Boolean perform(O t) {
                    return true;
                }
            });
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptBinaryDelegateToBinaryPredicate() {
            final BinaryPredicate<O, O> adapted = Dispatching.predicate(new BinaryDelegate<Boolean, O, O>() {

                @Override
                public Boolean perform(O f, O s) {
                    return true;
                }
            });
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptTernaryDelegateToBinaryPredicate() {
            final TernaryPredicate<O, O, O> adapted = Dispatching.predicate(new TernaryDelegate<Boolean, O, O, O>() {

                @Override
                public Boolean perform(O f, O s, O t) {
                    return true;
                }
            });
            Assert.assertNotNull(adapted);
        }
    }

    public static class CurryDelegates {

        @Test
        public void canCurryDelegate() {
            final Provider<O> adapted = Dispatching.curry(new Identity<O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canCurryBinaryDelegate() {
            final Delegate<Pair<O, O>, O> adapted = Dispatching.curry(new BinaryIdentity<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryBinaryDelegate() {
            final Delegate<Pair<O, O>, O> adapted = Dispatching.rcurry(new BinaryIdentity<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canCurryTernaryDelegate() {
            final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Dispatching.curry(new TernaryIdentity<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canMidCurryTernaryDelegate() {
            final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Dispatching.mcurry(new TernaryIdentity<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryTernaryDelegate() {
            final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Dispatching.rcurry(new TernaryIdentity<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }
    }

    public static class CurryActions {

        @Test
        public void canCurryBinaryAction() {
            final Action<O> adapted = Dispatching.curry(new BinaryNoop<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryBinaryAction() {
            final Action<O> adapted = Dispatching.rcurry(new BinaryNoop<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canCurryTernaryAction() {
            final BinaryAction<O, O> adapted = Dispatching.curry(new TernaryNoop<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canMidCurryTernaryAction() {
            final BinaryAction<O, O> adapted = Dispatching.mcurry(new TernaryNoop<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryTernaryAction() {
            final BinaryAction<O, O> adapted = Dispatching.rcurry(new TernaryNoop<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }
    }

    public static class CurryPredicates {

        @Test
        public void canCurryBinaryPredicate() {
            final Predicate<O> adapted = Dispatching.curry(new BinaryAlways<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryBinaryDelegate() {
            final Predicate<O> adapted = Dispatching.rcurry(new BinaryAlways<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canCurryTernaryDelegate() {
            final BinaryPredicate<O, O> adapted = Dispatching.curry(new TernaryAlways<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canMidCurryTernaryDelegate() {
            final BinaryPredicate<O, O> adapted = Dispatching.mcurry(new TernaryAlways<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryTernaryDelegate() {
            final BinaryPredicate<O, O> adapted = Dispatching.rcurry(new TernaryAlways<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }
    }

    public static class Ignoring {

        @Test
        public void canIgnoreFirstForPredicates() {
            BinaryPredicate<O, O> ignoring = Dispatching.ignore1st(new Always<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForBinaryPredicates() {
            TernaryPredicate<O, O, O> ignoring = Dispatching.ignore1st(new BinaryAlways<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForPredicates() {
            BinaryPredicate<O, O> ignoring = Dispatching.ignore2nd(new Always<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForBinaryPredicates() {
            TernaryPredicate<O, O, O> ignoring = Dispatching.ignore2nd(new BinaryAlways<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreThirdForBinaryPredicates() {
            TernaryPredicate<O, O, O> ignoring = Dispatching.ignore3rd(new BinaryAlways<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForActions() {
            BinaryAction<O, O> ignoring = Dispatching.ignore1st(new Noop<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForBinaryActions() {
            TernaryAction<O, O, O> ignoring = Dispatching.ignore1st(new BinaryNoop<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForActions() {
            BinaryAction<O, O> ignoring = Dispatching.ignore2nd(new Noop<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForBinaryActions() {
            TernaryAction<O, O, O> ignoring = Dispatching.ignore2nd(new BinaryNoop<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreThirdForBinaryActions() {
            TernaryAction<O, O, O> ignoring = Dispatching.ignore3rd(new BinaryNoop<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForDelegates() {
            BinaryDelegate<O, O, O> ignoring = Dispatching.ignore1st(new Identity<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForBinaryDelegates() {
            TernaryDelegate<O, O, O, O> ignoring = Dispatching.ignore1st(new FirstParam<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForDelegates() {
            BinaryDelegate<O, O, O> ignoring = Dispatching.ignore2nd(new Identity<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForBinaryDelegates() {
            TernaryDelegate<O, O, O, O> ignoring = Dispatching.ignore2nd(new FirstParam<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreThirdForBinaryDelegates() {
            TernaryDelegate<O, O, O, O> ignoring = Dispatching.ignore3rd(new FirstParam<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }
    }

    public static class Composing {

        @Test
        public void canComposeTwoDelegates() {
            Delegate<O, O> composed = Dispatching.compose(new Identity<O>(), new Identity<O>());
            Assert.assertNotNull(composed);
        }

        @Test
        public void canComposeThreeDelegates() {
            final Identity<O> i = new Identity<O>();
            Delegate<O, O> composed = Dispatching.compose(i, i, i);
            Assert.assertNotNull(composed);
        }

        @Test
        public void canComposePredicatesAndDelegates() {
            Predicate<O> composed = Dispatching.compose(new Always<O>(), new Identity<O>());
            Assert.assertNotNull(composed);
        }

        @Test
        public void canComposePredicateAndTwoDelegates() {
            final Identity<O> i = new Identity<O>();
            Predicate<O> composed = Dispatching.compose(new Always<O>(), i, i);
            Assert.assertNotNull(composed);
        }

        @Test
        public void canComposePredicatesAndBinaryDelegates() {
            BinaryPredicate<O, O> composed = Dispatching.compose(new Always<O>(), new FirstParam<O, O>());
            Assert.assertNotNull(composed);
        }

        @Test
        public void canComposePredicateAndTernaryDelegates() {
            TernaryPredicate<O, O, O> composed = Dispatching.compose(new Always<O>(), new FirstParamOfThree<O, O, O>());
            Assert.assertNotNull(composed);
        }
    }


    public static class AndTest {

        @Test
        public void canComposeIteratorOfPredicates() {
            final Iterable<Predicate<O>> preds = Arrays.asList(new Always<O>(), new Never<O>());
            Assert.assertFalse(Dispatching.and(preds.iterator()).accept(O.IGNORED));
        }

        @Test
        public void canComposeIterableOfPredicates() {
            final Iterable<Predicate<O>> preds = Arrays.asList(new Always<O>(), new Never<O>());
            Assert.assertFalse(Dispatching.and(preds).accept(O.IGNORED));
        }

        @Test
        public void canComposeTwoPredicates() {
            Assert.assertFalse(Dispatching.and(new Always<O>(), new Never<O>()).accept(O.IGNORED));
        }

        @Test
        public void canComposeThreePredicates() {
            Assert.assertFalse(Dispatching.and(new Always<O>(), new Never<O>(), new Never<O>()).accept(O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfBinaryPredicates() {
            final Iterable<BinaryPredicate<O, O>> preds = Arrays.asList(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertFalse(Dispatching.and2(preds.iterator()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfBinaryPredicates() {
            final Iterable<BinaryPredicate<O, O>> preds = Arrays.asList(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertFalse(Dispatching.and2(preds).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeTwoBinaryPredicates() {
            Assert.assertFalse(Dispatching.and2(new BinaryAlways<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeThreeBinaryPredicates() {
            Assert.assertFalse(Dispatching.and2(new BinaryAlways<O, O>(), new BinaryNever<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfTernaryPredicates() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Arrays.asList(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertFalse(Dispatching.and3(preds.iterator()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfTernaryPredicates() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Arrays.asList(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertFalse(Dispatching.and3(preds).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeTwoTernaryPredicates() {
            Assert.assertFalse(Dispatching.and3(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeThreeTernaryPredicates() {
            Assert.assertFalse(Dispatching.and3(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }
    }

    public static class OrTest {

        @Test
        public void canComposeIteratorOfPredicates() {
            final Iterable<Predicate<O>> preds = Arrays.asList(new Always<O>(), new Never<O>());
            Assert.assertTrue(Dispatching.or(preds.iterator()).accept(O.IGNORED));
        }

        @Test
        public void canComposeIterableOfPredicates() {
            final Iterable<Predicate<O>> preds = Arrays.asList(new Always<O>(), new Never<O>());
            Assert.assertTrue(Dispatching.or(preds).accept(O.IGNORED));
        }

        @Test
        public void canComposeTwoPredicates() {
            Assert.assertTrue(Dispatching.or(new Always<O>(), new Never<O>()).accept(O.IGNORED));
        }

        @Test
        public void canComposeThreePredicates() {
            Assert.assertTrue(Dispatching.or(new Always<O>(), new Always<O>(), new Never<O>()).accept(O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfBinaryPredicates() {
            final Iterable<BinaryPredicate<O, O>> preds = Arrays.asList(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertTrue(Dispatching.or2(preds.iterator()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfBinaryPredicates() {
            final Iterable<BinaryPredicate<O, O>> preds = Arrays.asList(new BinaryAlways<O, O>(), new BinaryNever<O, O>());
            Assert.assertTrue(Dispatching.or2(preds).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeTwoBinaryPredicates() {
            Assert.assertTrue(Dispatching.or2(new BinaryAlways<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeThreeBinaryPredicates() {
            Assert.assertTrue(Dispatching.or2(new BinaryAlways<O, O>(), new BinaryAlways<O, O>(), new BinaryNever<O, O>()).accept(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIteratorOfTernaryPredicates() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Arrays.asList(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertTrue(Dispatching.or3(preds.iterator()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeIterableOfTernaryPredicates() {
            final Iterable<TernaryPredicate<O, O, O>> preds = Arrays.asList(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>());
            Assert.assertTrue(Dispatching.or3(preds).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeTwoTernaryPredicates() {
            Assert.assertTrue(Dispatching.or3(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canComposeThreeTernaryPredicates() {
            Assert.assertTrue(Dispatching.or3(new TernaryAlways<O, O, O>(), new TernaryNever<O, O, O>(), new TernaryNever<O, O, O>()).accept(O.IGNORED, O.IGNORED, O.IGNORED));
        }
    }

    public static class AndDegenerationsTest {

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfPredicatesYieldsException() {
            final Iterator<Predicate<O>> preds = null;
            Dispatching.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfPredicatesYieldsException() {
            final Iterable<Predicate<O>> preds = null;
            Dispatching.and(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfBinaryPredicatesYieldsException() {
            final Iterator<BinaryPredicate<O, O>> preds = null;
            Dispatching.and2(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfBinaryPredicatesYieldsException() {
            final Iterable<BinaryPredicate<O, O>> preds = null;
            Dispatching.and2(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfTernaryPredicatesYieldsException() {
            final Iterator<TernaryPredicate<O, O, O>> preds = null;
            Dispatching.and3(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfTernaryPredicatesYieldsException() {
            final Iterable<TernaryPredicate<O, O, O>> preds = null;
            Dispatching.and3(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoPredicatesYieldsExceptionWhenFormerIsNull() {
            final Predicate<O> pred = null;
            Dispatching.and(pred, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoPredicatesYieldsExceptionWhenLatterIsNull() {
            final Predicate<O> pred = null;
            Dispatching.and(new Always<O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenFirstIsNull() {
            final Predicate<O> pred = null;
            Dispatching.and(pred, new Always<O>(), new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenSecondIsNull() {
            final Predicate<O> pred = null;
            Dispatching.and(new Always<O>(), pred, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenThirdIsNull() {
            final Predicate<O> pred = null;
            Dispatching.and(new Always<O>(), new Always<O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoBinaryPredicatesYieldsExceptionWhenFormerIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.and2(pred, new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoBinaryPredicatesYieldsExceptionWhenLatterIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.and2(new BinaryAlways<O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenFirstIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.and2(pred, new BinaryAlways<O, O>(), new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenSecondIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.and2(new BinaryAlways<O, O>(), pred, new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenThirdIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.and2(new BinaryAlways<O, O>(), new BinaryAlways<O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoTernaryPredicatesYieldsExceptionWhenFormerIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.and3(pred, new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoTernaryPredicatesYieldsExceptionWhenLatterIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.and3(new TernaryAlways<O, O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenFirstIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.and3(pred, new TernaryAlways<O, O, O>(), new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenSecondIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.and3(new TernaryAlways<O, O, O>(), pred, new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenThirdIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.and3(new TernaryAlways<O, O, O>(), new TernaryAlways<O, O, O>(), pred);
        }
    }

    public static class OrDegenerationsTest {

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfPredicatesYieldsException() {
            final Iterator<Predicate<O>> preds = null;
            Dispatching.or(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfPredicatesYieldsException() {
            final Iterable<Predicate<O>> preds = null;
            Dispatching.or(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfBinaryPredicatesYieldsException() {
            final Iterator<BinaryPredicate<O, O>> preds = null;
            Dispatching.or2(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfBinaryPredicatesYieldsException() {
            final Iterable<BinaryPredicate<O, O>> preds = null;
            Dispatching.or2(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIteratorOfTernaryPredicatesYieldsException() {
            final Iterator<TernaryPredicate<O, O, O>> preds = null;
            Dispatching.or3(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingNullIterableOfTernaryPredicatesYieldsException() {
            final Iterable<TernaryPredicate<O, O, O>> preds = null;
            Dispatching.or3(preds);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoPredicatesYieldsExceptionWhenFormerIsNull() {
            final Predicate<O> pred = null;
            Dispatching.or(pred, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoPredicatesYieldsExceptionWhenLatterIsNull() {
            final Predicate<O> pred = null;
            Dispatching.or(new Always<O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenFirstIsNull() {
            final Predicate<O> pred = null;
            Dispatching.or(pred, new Always<O>(), new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenSecondIsNull() {
            final Predicate<O> pred = null;
            Dispatching.or(new Always<O>(), pred, new Always<O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreePredicatesYieldsExceptionWhenThirdIsNull() {
            final Predicate<O> pred = null;
            Dispatching.or(new Always<O>(), new Always<O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoBinaryPredicatesYieldsExceptionWhenFormerIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.or2(pred, new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoBinaryPredicatesYieldsExceptionWhenLatterIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.or2(new BinaryAlways<O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenFirstIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.or2(pred, new BinaryAlways<O, O>(), new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenSecondIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.or2(new BinaryAlways<O, O>(), pred, new BinaryAlways<O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeBinaryPredicatesYieldsExceptionWhenThirdIsNull() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.or2(new BinaryAlways<O, O>(), new BinaryAlways<O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoTernaryPredicatesYieldsExceptionWhenFormerIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.or3(pred, new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingTwoTernaryPredicatesYieldsExceptionWhenLatterIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.or3(new TernaryAlways<O, O, O>(), pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenFirstIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.or3(pred, new TernaryAlways<O, O, O>(), new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenSecondIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.or3(new TernaryAlways<O, O, O>(), pred, new TernaryAlways<O, O, O>());
        }

        @Test(expected = IllegalArgumentException.class)
        public void composingThreeTernaryPredicatesYieldsExceptionWhenThirdIsNull() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.or3(new TernaryAlways<O, O, O>(), new TernaryAlways<O, O, O>(), pred);
        }
    }

    public static class NotTest {

        @Test
        public void canNegatePredicate() {
            final Predicate<O> negated = Dispatching.not(new Always<O>());
            Assert.assertFalse(negated.accept(null));
        }

        @Test
        public void canNegateBinaryPredicate() {
            final BinaryPredicate<O, O> negated = Dispatching.not(new BinaryAlways<O, O>());
            Assert.assertFalse(negated.accept(null, null));
        }

        @Test
        public void canNegateTernaryPredicate() {
            final TernaryPredicate<O, O, O> negated = Dispatching.not(new TernaryAlways<O, O, O>());
            Assert.assertFalse(negated.accept(null, null, null));
        }

        @Test(expected = IllegalArgumentException.class)
        public void negatingNullPredicateYieldsException() {
            final Predicate<O> pred = null;
            Dispatching.not(pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negatingBinaryNullPredicateYieldsException() {
            final BinaryPredicate<O, O> pred = null;
            Dispatching.not(pred);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negatingTernaryNullPredicateYieldsException() {
            final TernaryPredicate<O, O, O> pred = null;
            Dispatching.not(pred);
        }
    }

    public static class AlwaysTest {

        @Test
        public void canCreateAlways() {
            Assert.assertNotNull(Dispatching.always());
        }

        @Test
        public void canCreateBinaryAlways() {
            Assert.assertNotNull(Dispatching.always2());
        }

        @Test
        public void canCreateTernaryAlways() {
            Assert.assertNotNull(Dispatching.always3());
        }
    }

    public static class NeverTest {

        @Test
        public void canCreateNever() {
            Assert.assertNotNull(Dispatching.never());
        }

        @Test
        public void canCreateBinaryNever() {
            Assert.assertNotNull(Dispatching.never2());
        }

        @Test
        public void canCreateTernaryNever() {
            Assert.assertNotNull(Dispatching.never3());
        }
    }
    
    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Dispatching() {
            };
        }
    }
}
