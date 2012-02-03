package net.emaze.dysfunctional;

import net.emaze.dysfunctional.Tuples.Pairs;
import net.emaze.dysfunctional.Tuples.Triples;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
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
            final Action<Pair<O, O>> action = new Noop<Pair<O, O>>();
            Assert.assertNotNull(Tuples.Pairs.untupled(action));
        }

        @Test
        public void canAdaptPredicate() {
            final Predicate<Pair<O, O>> predicate = Logic.Unary.<Pair<O, O>>always();
            Assert.assertNotNull(Tuples.Pairs.untupled(predicate));
        }

        @Test
        public void canAdaptDelegate() {
            final Identity<Pair<O, O>> delegate = new Identity<Pair<O, O>>();
            Assert.assertNotNull(Tuples.Pairs.untupled(delegate));
        }
    }

    public static class TriplesUntupled {

        @Test
        public void canAdaptAction() {
            final Action<Triple<O, O, O>> action = new Noop<Triple<O, O, O>>();
            Assert.assertNotNull(Tuples.Triples.untupled(action));
        }

        @Test
        public void canAdaptPredicate() {
            final Predicate<Triple<O, O, O>> predicate = Logic.Unary.<Triple<O, O, O>>always();
            Assert.assertNotNull(Tuples.Triples.untupled(predicate));
        }

        @Test
        public void canAdaptDelegate() {
            final Identity<Triple<O, O, O>> delegate = new Identity<Triple<O, O, O>>();
            Assert.assertNotNull(Tuples.Triples.untupled(delegate));
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
