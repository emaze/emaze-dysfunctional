package net.emaze.dysfunctional;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.Tuples.Pairs;
import net.emaze.dysfunctional.Tuples.Triples;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
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
        public void canAdaptBinaryAction() {
            Assert.assertNotNull(Tuples.tupled(new BinaryNoop<O, O>()));
        }

        @Test
        public void canAdaptTernaryAction() {
            Assert.assertNotNull(Tuples.tupled(new TernaryNoop<O, O, O>()));
        }

        @Test
        public void canAdaptBinaryDelegate() {
            Assert.assertNotNull(Tuples.tupled(new BinaryIdentity<O, O>()));
        }

        @Test
        public void canAdaptTernaryDelegate() {
            Assert.assertNotNull(Tuples.tupled(new TernaryIdentity<O, O, O>()));
        }

        @Test
        public void canAdaptBinaryPredicate() {
            Assert.assertNotNull(Tuples.tupled(Logic.Binary.<O, O>always()));
        }

        @Test
        public void canAdaptTernaryPredicate() {
            Assert.assertNotNull(Tuples.tupled(Logic.Ternary.<O, O, O>always()));
        }
    }

    public static class PairsUntupled {

        @Test
        public void canAdaptAction() {
            final Consumer<Pair<O, O>> consumer = new Noop<Pair<O, O>>();
            Assert.assertNotNull(Tuples.Pairs.untupled(consumer));
        }

        @Test
        public void canAdaptPredicate() {
            final Predicate<Pair<O, O>> predicate = Logic.Unary.<Pair<O, O>>always();
            Assert.assertNotNull(Tuples.Pairs.untupled(predicate));
        }

        @Test
        public void canAdaptDelegate() {
            final UnaryOperator<Pair<O, O>> function = UnaryOperator.identity();
            Assert.assertNotNull(Tuples.Pairs.untupled(function));
        }
    }

    public static class TriplesUntupled {

        @Test
        public void canAdaptAction() {
            final Consumer<Triple<O, O, O>> consumer = new Noop<Triple<O, O, O>>();
            Assert.assertNotNull(Tuples.Triples.untupled(consumer));
        }

        @Test
        public void canAdaptPredicate() {
            final Predicate<Triple<O, O, O>> predicate = Logic.Unary.<Triple<O, O, O>>always();
            Assert.assertNotNull(Tuples.Triples.untupled(predicate));
        }

        @Test
        public void canAdaptDelegate() {
            final UnaryOperator<Triple<O, O, O>> function = UnaryOperator.identity();
            Assert.assertNotNull(Tuples.Triples.untupled(function));
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
