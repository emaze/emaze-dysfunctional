/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import java.util.function.Supplier;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CapturingSupplierTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingSpyWithNullAdaptedYieldsException() {
        new CapturingSupplier<O>(null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingSpyWithNullBoxYieldsException() {
        new CapturingSupplier<O>(new ConstantSupplier<O>(O.ONE), null);
    }

    @Test
    public void callingCapturesTheResult() {
        final Box<O> result = Box.empty();
        final Supplier<O> spy = new CapturingSupplier<O>(new ConstantSupplier<O>(O.ONE), result);
        spy.get();
        Assert.assertEquals(Box.of(O.ONE), result);
    }
}
