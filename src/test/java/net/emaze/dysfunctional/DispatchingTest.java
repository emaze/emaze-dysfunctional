package net.emaze.dysfunctional;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.Slacker;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
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
        public void canAdaptIteratorToProvider() {
            final Supplier<Optional<Integer>> adapted = Dispatching.supplier(Iterations.iterator(1));
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptRunnableToProvider() {
            final Supplier<Void> adapted = Dispatching.supplier(new Slacker());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptPropositionToProvider() {
            final Supplier<Boolean> adapted = Dispatching.supplier(new Yes());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptProviderToRunnable() {
            final Runnable adapted = Dispatching.runnable(new ConstantSupplier<O>(O.ONE));
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptProviderToProposition() {
            final BooleanSupplier adapted = Dispatching.proposition(new ConstantSupplier<Boolean>(Boolean.TRUE));
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptActionToDelegate() {
            final Function<O, Void> adapted = Dispatching.function(new Noop<O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptBinaryActionToBinaryDelegate() {
            final BiFunction<O, O, Void> adapted = Dispatching.function(new BinaryNoop<O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptTernaryActionToTernaryDelegate() {
            final TriFunction<O, O, O, Void> adapted = Dispatching.function(new TernaryNoop<O, O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptPredicateToDelegate() {
            final Function<O, Boolean> adapted = Dispatching.function(new Always<O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptBinaryPredicateToBinaryDelegate() {
            final BiFunction<O, O, Boolean> adapted = Dispatching.function(new BinaryAlways<O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptTernaryPredicateToTernaryDelegate() {
            final TriFunction<O, O, O, Boolean> adapted = Dispatching.function(new TernaryAlways<O, O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptDelegateToAction() {
            final Consumer<O> adapted = Dispatching.consumer(Function.identity());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptBinaryDelegateToBinaryAction() {
            final BiConsumer<O, O> adapted = Dispatching.consumer(new FirstParam<O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptTernaryDelegateToTernaryAction() {
            final TriConsumer<O, O, O> adapted = Dispatching.consumer(new FirstParamOfThree<O, O, O>());
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptDelegateToPredicate() {
            final Predicate<O> adapted = Dispatching.predicate(new Function<O, Boolean>() {
                @Override
                public Boolean apply(O t) {
                    return true;
                }
            });
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptBinaryDelegateToBinaryPredicate() {
            final BiPredicate<O, O> adapted = Dispatching.predicate((f, s) -> true);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canAdaptTernaryDelegateToBinaryPredicate() {
            final TriPredicate<O, O, O> adapted = Dispatching.predicate((a, b, c) -> true);
            Assert.assertNotNull(adapted);
        }
    }

    public static class CurryDelegates {

        @Test
        public void canCurryDelegate() {
            final Supplier<O> adapted = Dispatching.curry(Function.identity(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canCurryBinaryDelegate() {
            final Function<O, Pair<O, O>> adapted = Dispatching.curry(new BinaryIdentity<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryBinaryDelegate() {
            final Function<O, Pair<O, O>> adapted = Dispatching.rcurry(new BinaryIdentity<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canCurryTernaryDelegate() {
            final BiFunction<O, O, Triple<O, O, O>> adapted = Dispatching.curry(new TernaryIdentity<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canMidCurryTernaryDelegate() {
            final BiFunction<O, O, Triple<O, O, O>> adapted = Dispatching.mcurry(new TernaryIdentity<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryTernaryDelegate() {
            final BiFunction<O, O, Triple<O, O, O>> adapted = Dispatching.rcurry(new TernaryIdentity<O, O, O>(), O.ONE);
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
            final Consumer<O> adapted = Dispatching.curry(new BinaryNoop<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryBinaryAction() {
            final Consumer<O> adapted = Dispatching.rcurry(new BinaryNoop<O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canCurryTernaryAction() {
            final BiConsumer<O, O> adapted = Dispatching.curry(new TernaryNoop<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canMidCurryTernaryAction() {
            final BiConsumer<O, O> adapted = Dispatching.mcurry(new TernaryNoop<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryTernaryAction() {
            final BiConsumer<O, O> adapted = Dispatching.rcurry(new TernaryNoop<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }
    }

    public static class CurryPredicates {

        @Test
        public void canCurryPredicate() {
            final BooleanSupplier adapted = Dispatching.curry(new Always<O>(), O.ONE);
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
            final BiPredicate<O, O> adapted = Dispatching.curry(new TernaryAlways<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canMidCurryTernaryDelegate() {
            final BiPredicate<O, O> adapted = Dispatching.mcurry(new TernaryAlways<O, O, O>(), O.ONE);
            Assert.assertNotNull(adapted);
        }

        @Test
        public void canRightCurryTernaryDelegate() {
            final BiPredicate<O, O> adapted = Dispatching.rcurry(new TernaryAlways<O, O, O>(), O.ONE);
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
            final Consumer<O> ignoring = Dispatching.ignore(new Slacker(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreParameterForProvider() {
            final Function<O, O> ignoring = Dispatching.ignore(new ConstantSupplier<O>(O.ONE), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForPredicates() {
            final BiPredicate<O, O> ignoring = Dispatching.ignore1st(new Always<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForBinaryPredicates() {
            final TriPredicate<O, O, O> ignoring = Dispatching.ignore1st(new BinaryAlways<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForPredicates() {
            final BiPredicate<O, O> ignoring = Dispatching.ignore2nd(new Always<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForBinaryPredicates() {
            final TriPredicate<O, O, O> ignoring = Dispatching.ignore2nd(new BinaryAlways<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreThirdForBinaryPredicates() {
            final TriPredicate<O, O, O> ignoring = Dispatching.ignore3rd(new BinaryAlways<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForActions() {
            final BiConsumer<O, O> ignoring = Dispatching.ignore1st(new Noop<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForBinaryActions() {
            final TriConsumer<O, O, O> ignoring = Dispatching.ignore1st(new BinaryNoop<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForActions() {
            final BiConsumer<O, O> ignoring = Dispatching.ignore2nd(new Noop<O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForBinaryActions() {
            final TriConsumer<O, O, O> ignoring = Dispatching.ignore2nd(new BinaryNoop<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreThirdForBinaryActions() {
            final TriConsumer<O, O, O> ignoring = Dispatching.ignore3rd(new BinaryNoop<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForDelegates() {
            final BiFunction<O, O, O> ignoring = Dispatching.ignore1st(Function.identity(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreFirstForBinaryDelegates() {
            final TriFunction<O, O, O, O> ignoring = Dispatching.ignore1st(new FirstParam<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForDelegates() {
            final BiFunction<O, O, O> ignoring = Dispatching.ignore2nd(Function.identity(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreSecondForBinaryDelegates() {
            final TriFunction<O, O, O, O> ignoring = Dispatching.ignore2nd(new FirstParam<O, O>(), O.class);
            Assert.assertNotNull(ignoring);
        }

        @Test
        public void canIgnoreThirdForBinaryDelegates() {
            final TriFunction<O, O, O, O> ignoring = Dispatching.ignore3rd(new FirstParam<O, O>(), O.class);
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
