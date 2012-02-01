package net.emaze.dysfunctional;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SpiesTest {

    @Test
    public void canSpyADelegate() {
        final Box<O> result = new Box<O>();
        final Box<O> param = new Box<O>();
        final Delegate<O, O> spied = Spies.spy(new Identity<O>(), result, param);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyResultOfDelegate() {
        final Box<O> result = new Box<O>();
        final Delegate<O, O> spied = Spies.spyRes(new Identity<O>(), result);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyFirstOfDelegate() {
        final Box<O> param = new Box<O>();
        final Delegate<O, O> spied = Spies.spy1st(new Identity<O>(), param);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyBinaryDelegate() {
        final Box<O> result = new Box<O>();
        final Box<O> param1 = new Box<O>();
        final Box<O> param2 = new Box<O>();
        final BinaryDelegate<O, O, O> spied = Spies.spy(new FirstParam<O, O>(), result, param1, param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyResultOfBinaryDelegate() {
        final Box<O> result = new Box<O>();
        final BinaryDelegate<O, O, O> spied = Spies.spyRes(new FirstParam<O, O>(), result);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyFirstParamOfBinaryDelegate() {
        final Box<O> param1 = new Box<O>();
        final BinaryDelegate<O, O, O> spied = Spies.spy1st(new FirstParam<O, O>(), param1);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpySecondParamOfBinaryDelegate() {
        final Box<O> param2 = new Box<O>();
        final BinaryDelegate<O, O, O> spied = Spies.spy2nd(new FirstParam<O, O>(), param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyTernaryDelegate() {
        final Box<O> result = new Box<O>();
        final Box<O> param1 = new Box<O>();
        final Box<O> param2 = new Box<O>();
        final Box<O> param3 = new Box<O>();
        final TernaryDelegate<O, O, O, O> spied = Spies.spy(new FirstParamOfThree<O, O, O>(), result, param1, param2, param3);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyResultOfTernaryDelegate() {
        final Box<O> result = new Box<O>();
        final TernaryDelegate<O, O, O, O> spied = Spies.spyRes(new FirstParamOfThree<O, O, O>(), result);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyFirstParamOfTernaryDelegate() {
        final Box<O> param1 = new Box<O>();
        final TernaryDelegate<O, O, O, O> spied = Spies.spy1st(new FirstParamOfThree<O, O, O>(), param1);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpySecondParamOfTernaryDelegate() {
        final Box<O> param2 = new Box<O>();
        final TernaryDelegate<O, O, O, O> spied = Spies.spy2nd(new FirstParamOfThree<O, O, O>(), param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyThirdParamOfTernaryDelegate() {
        final Box<O> param3 = new Box<O>();
        final TernaryDelegate<O, O, O, O> spied = Spies.spy3rd(new FirstParamOfThree<O, O, O>(), param3);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyAPredicate() {
        final Box<Boolean> result = new Box<Boolean>();
        final Box<O> param = new Box<O>();
        final Predicate<O> spied = Spies.spy(new Always<O>(), result, param);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyResultOfPredicate() {
        final Box<Boolean> result = new Box<Boolean>();
        final Predicate<O> spied = Spies.spyRes(new Always<O>(), result);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyFirstParamOfPredicate() {
        final Box<O> param = new Box<O>();
        final Predicate<O> spied = Spies.spy1st(new Always<O>(), param);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyBinaryPredicate() {
        final Box<Boolean> result = new Box<Boolean>();
        final Box<O> param1 = new Box<O>();
        final Box<O> param2 = new Box<O>();
        final BinaryPredicate<O, O> spied = Spies.spy(new BinaryAlways<O, O>(), result, param1, param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyResultOfBinaryPredicate() {
        final Box<Boolean> result = new Box<Boolean>();
        final BinaryPredicate<O, O> spied = Spies.spyRes(new BinaryAlways<O, O>(), result);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyFirstParamOfBinaryPredicate() {
        final Box<O> param1 = new Box<O>();
        final BinaryPredicate<O, O> spied = Spies.spy1st(new BinaryAlways<O, O>(), param1);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpySecondParamOfBinaryPredicate() {
        final Box<O> param2 = new Box<O>();
        final BinaryPredicate<O, O> spied = Spies.spy2nd(new BinaryAlways<O, O>(), param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyTernaryPredicate() {
        final Box<Boolean> result = new Box<Boolean>();
        final Box<O> param1 = new Box<O>();
        final Box<O> param2 = new Box<O>();
        final Box<O> param3 = new Box<O>();
        final TernaryPredicate<O, O, O> spied = Spies.spy(new TernaryAlways<O, O, O>(), result, param1, param2, param3);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyResultOfTernaryPredicate() {
        final Box<Boolean> result = new Box<Boolean>();
        final TernaryPredicate<O, O, O> spied = Spies.spyRes(new TernaryAlways<O, O, O>(), result);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyFirstParamOfTernaryPredicate() {
        final Box<O> param1 = new Box<O>();
        final TernaryPredicate<O, O, O> spied = Spies.spy1st(new TernaryAlways<O, O, O>(), param1);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpySecondParamOfTernaryPredicate() {
        final Box<O> param2 = new Box<O>();
        final TernaryPredicate<O, O, O> spied = Spies.spy2nd(new TernaryAlways<O, O, O>(), param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyThirdParamOfTernaryPredicate() {
        final Box<O> param3 = new Box<O>();
        final TernaryPredicate<O, O, O> spied = Spies.spy3rd(new TernaryAlways<O, O, O>(), param3);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyAnAction() {
        final Box<O> param = new Box<O>();
        final Action<O> spied = Spies.spy(new Noop<O>(), param);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyParamOfAnAction() {
        final Box<O> param = new Box<O>();
        final Action<O> spied = Spies.spy1st(new Noop<O>(), param);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyBinaryAction() {
        final Box<O> param1 = new Box<O>();
        final Box<O> param2 = new Box<O>();
        final BinaryAction<O, O> spied = Spies.spy(new BinaryNoop<O, O>(), param1, param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyFirstParmaOfBinaryAction() {
        final Box<O> param1 = new Box<O>();
        final BinaryAction<O, O> spied = Spies.spy1st(new BinaryNoop<O, O>(), param1);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpySecondParmaOfBinaryAction() {
        final Box<O> param2 = new Box<O>();
        final BinaryAction<O, O> spied = Spies.spy2nd(new BinaryNoop<O, O>(), param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyTernaryAction() {
        final Box<O> param1 = new Box<O>();
        final Box<O> param2 = new Box<O>();
        final Box<O> param3 = new Box<O>();
        final TernaryAction<O, O, O> spied = Spies.spy(new TernaryNoop<O, O, O>(), param1, param2, param3);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyFirstParamOfTernaryAction() {
        final Box<O> param1 = new Box<O>();
        final TernaryAction<O, O, O> spied = Spies.spy1st(new TernaryNoop<O, O, O>(), param1);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpySecondParamOfTernaryAction() {
        final Box<O> param2 = new Box<O>();
        final TernaryAction<O, O, O> spied = Spies.spy2nd(new TernaryNoop<O, O, O>(), param2);
        Assert.assertNotNull(spied);
    }

    @Test
    public void canSpyThirdParamOfTernaryAction() {
        final Box<O> param3 = new Box<O>();
        final TernaryAction<O, O, O> spied = Spies.spy3rd(new TernaryNoop<O, O, O>(), param3);
        Assert.assertNotNull(spied);
    }

    @Test
    public void facadeIsNotFinal() {
        new Spies() {
        };
    }
}