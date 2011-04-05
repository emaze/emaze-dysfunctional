package net.emaze.dysfunctional.dispatching;

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
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
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
    DispatchingTest.CurryActions.class
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
}
