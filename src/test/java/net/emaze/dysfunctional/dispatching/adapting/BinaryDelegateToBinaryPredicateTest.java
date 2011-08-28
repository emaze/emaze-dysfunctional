package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.SecondParam;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryDelegateToBinaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new BinaryDelegateToBinaryPredicate<O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final BinaryDelegate<Boolean, Boolean, O> spy = Spies.spy2nd(new FirstParam<Boolean, O>(), param2);
        final BinaryPredicate<Boolean, O> adapted = new BinaryDelegateToBinaryPredicate<Boolean, O>(spy);
        adapted.accept(true, O.ONE);
        Assert.assertEquals(O.ONE, param2.getContent());
    }
    
    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final BinaryDelegate<Boolean, O, Boolean> spy = Spies.spy1st(new SecondParam<O, Boolean>(), param1);
        final BinaryPredicate<O, Boolean> adapted = new BinaryDelegateToBinaryPredicate<O, Boolean>(spy);
        adapted.accept(O.ONE, true);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
}
