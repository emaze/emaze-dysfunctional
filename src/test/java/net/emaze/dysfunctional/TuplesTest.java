package net.emaze.dysfunctional;

import java.util.function.*;
import net.emaze.dysfunctional.Tuples.Pairs;
import net.emaze.dysfunctional.Tuples.Triples;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.options.Box;
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
    TuplesTest.Tupled.class,
    TuplesTest.PairsUntupled.class,
    TuplesTest.TriplesUntupled.class,
    TuplesTest.Facades.class})
public class TuplesTest {

    public static class Tupled {

        @Test
        public void canAdaptBiConsumer() {
            final Box<O> first = Box.empty();
            final Box<O> second = Box.empty();
            final BiConsumer<O, O> spy = Spies.spy(new BinaryNoop<O, O>(), first, second);
            final Consumer<Pair<O, O>> consumer = Tuples.tupled(spy);
            consumer.accept(Pair.of(O.ONE, O.ANOTHER));
            Assert.assertEquals(O.ONE, first.getContent());
            Assert.assertEquals(O.ANOTHER, second.getContent());
        }

        @Test
        public void canAdaptTriConsumer() {
            final Box<O> first = Box.empty();
            final Box<O> second = Box.empty();
            final Box<O> third = Box.empty();
            final TriConsumer<O, O, O> spy = Spies.spy(new TernaryNoop<O, O, O>(), first, second, third);
            final Consumer<Triple<O, O, O>> consumer = Tuples.tupled(spy);
            consumer.accept(Triple.of(O.ONE, O.ANOTHER, O.YET_ANOTHER));
            Assert.assertEquals(O.ONE, first.getContent());
            Assert.assertEquals(O.ANOTHER, second.getContent());
            Assert.assertEquals(O.YET_ANOTHER, third.getContent());
        }

        @Test
        public void canAdaptBiFunction() {
            final Function<Pair<O, O>, Pair<O, O>> function = Tuples.tupled(new BinaryIdentity<O, O>());
            final Pair<O, O> expected = Pair.of(O.ONE, O.ANOTHER);
            final Pair<O, O> got = function.apply(expected);
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canAdaptTriFunction() {
            final Function<Triple<O, O, O>, Triple<O, O, O>> function = Tuples.tupled(new TernaryIdentity<>());
            final Triple<O, O, O> expected = Triple.of(O.ONE, O.ANOTHER, O.ANOTHER);
            final Triple<O, O, O> got = function.apply(expected);
            Assert.assertEquals(expected, got);
        }

        @Test
        public void canAdaptBiPredicate() {
            final Predicate<Pair<O, O>> predicate = Tuples.tupled(new BinaryAlways<O, O>());
            Assert.assertTrue(predicate.test(Pair.of(O.IGNORED, O.IGNORED)));
        }

        @Test
        public void canAdaptTernaryPredicate() {
            final Predicate<Triple<O, O, O>> predicate = Tuples.tupled(new TernaryAlways<O, O, O>());
            Assert.assertTrue(predicate.test(Triple.of(O.IGNORED, O.IGNORED, O.IGNORED)));
        }
    }

    public static class PairsUntupled {

        @Test
        public void canAdaptConsumer() {
            final Box<Pair<O, O>> box = Box.empty();
            final Consumer<Pair<O, O>> consumer = Spies.spy(new Noop<Pair<O, O>>(), box);
            final BiConsumer<O, O> adapted = Tuples.Pairs.untupled(consumer);
            adapted.accept(O.ONE, O.ANOTHER);
            Assert.assertEquals(Pair.of(O.ONE, O.ANOTHER), box.getContent());
        }

        @Test
        public void canAdaptPredicate() {
            final Predicate<Pair<O, O>> predicate = new Always<>();
            final BiPredicate<O, O> adapted = Tuples.Pairs.untupled(predicate);
            Assert.assertTrue(adapted.test(O.IGNORED, O.IGNORED));
        }

        @Test
        public void canAdaptFunction() {
            final Function<Pair<O, O>, Pair<O, O>> function = Function.identity();
            final BiFunction<O, O, Pair<O, O>> adapted = Tuples.Pairs.untupled(function);
            Pair<O, O> got = adapted.apply(O.ONE, O.ONE);
            Assert.assertEquals(Pair.of(O.ONE, O.ONE), got);
        }
    }

    public static class TriplesUntupled {

        @Test
        public void canAdaptConsumer() {
            final Box<Triple<O, O, O>> box = Box.empty();
            final Consumer<Triple<O, O, O>> consumer = Spies.spy(new Noop<Triple<O, O, O>>(), box);
            final TriConsumer<O, O, O> adapted = Tuples.Triples.untupled(consumer);
            adapted.accept(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertEquals(Triple.of(O.ONE, O.ANOTHER, O.YET_ANOTHER), box.getContent());
        }

        @Test
        public void canAdaptPredicate() {
            final Predicate<Triple<O, O, O>> predicate = new Always<Triple<O, O, O>>();
            final TriPredicate<O, O, O> adapted = Tuples.Triples.untupled(predicate);
            Assert.assertTrue(adapted.test(O.IGNORED, O.IGNORED, O.IGNORED));
        }

        @Test
        public void canAdaptFunction() {
            final Function<Triple<O, O, O>, Triple<O, O, O>> function = Function.identity();
            final TriFunction<O, O, O, Triple<O, O, O>> adapted = Tuples.Triples.untupled(function);
            Triple<O, O, O> got = adapted.apply(O.ONE, O.ONE, O.ONE);
            Assert.assertEquals(Triple.of(O.ONE, O.ONE, O.ONE), got);
        }
    }

    public static class Facades {

        @Test
        public void tuplesIsNotFinal() {
            new Tuples() {
            };
        }

        @Test
        public void pairsIsNotFinal() {
            new Pairs() {
            };
        }

        @Test
        public void triplesIsNotFinal() {
            new Triples() {
            };
        }
    }
}
