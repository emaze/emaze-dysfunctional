package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Collections;
import java.util.Map;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ConstantMapperTest {

    
    @Test(expected=IllegalArgumentException.class)
    public void creatingConstantMapperWithNullMapWillThrow() {
        new ConstantMapper<O, O>(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void missingMappingYieldsException() {
        new ConstantMapper<O, O>(Collections.<O, O>emptyMap()).perform(O.ONE);
    }

    @Test
    public void presentMappingYieldsValue() {
        final Map<O, O> mapping = Collections.singletonMap(O.ONE, O.ANOTHER);
        final ConstantMapper<O, O> mapper = new ConstantMapper<O, O>(mapping);
        Assert.assertEquals(O.ANOTHER, mapper.perform(O.ONE));
    }

}