package net.emaze.dysfunctional;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Slacker;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.Proposition;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Yes;
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
    DispatchingTest.Facade.class
})
public class DispatchingTest {

    public static class Adapting {

        @Test
        public void canAdaptRunnableToProvider() {
            final Provider<Void> adapted = Dispatching.provider(new Slacker());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptPropositionToProvider() {
            final Provider<Boolean> adapted = Dispatching.provider(new Yes());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptProviderToRunnable() {
            final Runnable adapted = Dispatching.runnable(new ConstantProvider<O>(O.ONE));
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptProviderToProposition() {
            final Proposition adapted = Dispatching.proposition(new ConstantProvider<Boolean>(Boolean.TRUE));
            Assert.assertNotNull(adapted);
        }

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
        public void canCurryAction() {
            final Runnable runnable = Dispatching.curry(new Noop<O>(), O.ONE);
            Assert.assertNotNull(runnable);
        }

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
        public void canCurryPredicate() {
            final Proposition adapted = Dispatching.curry(new Always<O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

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
        public void canIgnoreParameterForProposition() {
            Predicate<O> ignoring = Dispatching.ignore(new Yes(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreParameterForRunnable() {
            final Action<O> ignoring = Dispatching.ignore(new Slacker(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreParameterForProvider() {
            final Delegate<O, O> ignoring = Dispatching.ignore(new ConstantProvider<O>(O.ONE), O.class);
            Assert.assertNotNull(ignoring);
        }

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


    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Dispatching() {
            };
        }
    }
}
