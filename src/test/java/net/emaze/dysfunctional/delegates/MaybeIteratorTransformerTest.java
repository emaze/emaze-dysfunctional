package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.iterations.ConstantIterator;
import net.emaze.dysfunctional.options.MaybeIterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MaybeIteratorTransformerTest {

    @Test(expected=IllegalArgumentException.class)
    public void trasformingNullYieldsException() {
        new MaybeIteratorTransformer<String>().perform(null);
    }
    
    @Test
    public void canTransformNonNullIterator() {
        MaybeIterator<String> got = new MaybeIteratorTransformer<String>().perform(new ConstantIterator<String>("a"));
        Assert.assertNotNull(got);
    }

}