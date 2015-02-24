package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.SecondParam;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryFunctionToPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new BinaryFunctionToPredicate<O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final BiFunction<Boolean, O, Boolean> spy = Spies.spy2nd(new FirstParam<Boolean, O>(), param2);
        final BiPredicate<Boolean, O> adapted = new BinaryFunctionToPredicate<Boolean, O>(spy);
        adapted.test(true, O.ONE);
        Assert.assertEquals(O.ONE, param2.getContent());
    }
    
    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final BiFunction<O, Boolean, Boolean> spy = Spies.spy1st(new SecondParam<O, Boolean>(), param1);
        final BiPredicate<O, Boolean> adapted = new BinaryFunctionToPredicate<O, Boolean>(spy);
        adapted.test(O.ONE, true);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
}
