package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class EndoDelegatesComposerTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new EndoDelegatesComposer<O>().perform(null);
    }

    @Test
    public void callingYieldsComposedDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Identity<O> identity = new Identity<O>();
        final Iterator<Delegate<O, O>> delegates = Iterations.<Delegate<O, O>>iterator(Spies.monitor(identity, calls), Spies.monitor(identity, calls));
        final Delegate<O, O> delegate = new EndoDelegatesComposer<O>().perform(delegates);
        delegate.perform(O.ONE);
        Assert.assertEquals(2, calls.get());
    }
}