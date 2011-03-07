package net.emaze.dysfunctional.delegates;

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
public class AdaptersTest {

    @Test
    public void canAdaptActionToDelegate() {
        final Delegate<Void, O> adapted = Adapters.action2delegate(new Noop<O>());
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canAdaptBinActionToBinDelegate() {
        final BinaryDelegate<Void, O, O> adapted = Adapters.action2delegate(new BinaryNoop<O, O>());
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canAdaptTerActionToTerDelegate() {
        final TernaryDelegate<Void, O, O, O> adapted = Adapters.action2delegate(new TernaryNoop<O, O, O>());
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBind() {
        final Provider<O> adapted = Adapters.bind(new Identity<O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindFirstOfTwo() {
        final Delegate<Pair<O, O>, O> adapted = Adapters.bind1st(new BinaryIdentity<O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindFirstOfThree() {
        final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Adapters.bind1st(new TernaryIdentity<O, O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindSecondOfTwo() {
        final Delegate<Pair<O, O>, O> adapted = Adapters.bind2nd(new BinaryIdentity<O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindSecondOfThree() {
        final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Adapters.bind2nd(new TernaryIdentity<O, O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }

    @Test
    public void canBindThirdOfThree() {
        final BinaryDelegate<Triple<O, O, O>, O, O> adapted = Adapters.bind3rd(new TernaryIdentity<O, O, O>(), O.ONE);
        Assert.assertNotNull(adapted);
    }
}
