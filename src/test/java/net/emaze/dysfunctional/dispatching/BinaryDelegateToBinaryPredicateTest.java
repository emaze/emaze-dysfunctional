package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.delegates.BinaryCapturingDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.SecondParam;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
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
        BinaryCapturingDelegate<Boolean, Boolean, O> adaptee = new BinaryCapturingDelegate<Boolean, Boolean, O>(new FirstParam<Boolean, O>());
        final BinaryPredicate<Boolean, O> adapted = new BinaryDelegateToBinaryPredicate<Boolean, O>(adaptee);
        adapted.accept(true, O.ONE);
        Assert.assertEquals(O.ONE, adaptee.second.getContent());
    }
    
    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        BinaryCapturingDelegate<Boolean, O, Boolean> adaptee = new BinaryCapturingDelegate<Boolean, O, Boolean>(new SecondParam<O, Boolean>());
        final BinaryPredicate<O, Boolean> adapted = new BinaryDelegateToBinaryPredicate<O, Boolean>(adaptee);
        adapted.accept(O.ONE, true);
        Assert.assertEquals(O.ONE, adaptee.first.getContent());
    }
}
