package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.BinaryIdentity;
import net.emaze.dysfunctional.tuples.Pair;
import net.emaze.dysfunctional.tuples.TernaryIdentity;
import net.emaze.dysfunctional.tuples.Triple;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DispatchingTest {

    @Test
    public void canAdaptActionToDelegate() {
        final Delegate<Void, O> adapted = Dispatching.delegate(new Noop<O>());
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canAdaptBinActionToBinDelegate() {
        final BinaryDelegate<Void, O, O> adapted = Dispatching.delegate(new BinaryNoop<O, O>());
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canAdaptTerActionToTerDelegate() {
        final TernaryDelegate<Void, O, O, O> adapted = Dispatching.delegate(new TernaryNoop<O, O, O>());
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBind() {
        final Provider<O> adapted = Dispatching.curry(new Identity<O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindFirstOfTwo() {
        final Delegate<Pair<O, O>, O> adapted = Dispatching.curry(new BinaryIdentity<O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindFirstOfThree() {
        final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Dispatching.curry(new TernaryIdentity<O, O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindSecondOfTwo() {
        final Delegate<Pair<O, O>, O> adapted = Dispatching.mcurry(new BinaryIdentity<O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindSecondOfThree() {
        final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Dispatching.mcurry(new TernaryIdentity<O, O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindThirdOfThree() {
        final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Dispatching.rcurry(new TernaryIdentity<O, O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }
}
