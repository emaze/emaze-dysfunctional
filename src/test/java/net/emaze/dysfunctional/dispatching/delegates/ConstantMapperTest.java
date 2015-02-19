package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Collections;
import java.util.Map;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ConstantMapperTest {

    @Test(expected = IllegalArgumentException.class)
    public void usingConstantMapperWithNullMapWillThrow() {
        new ConstantMapper<O, O>().apply(null, O.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingMappingYieldsException() {
        new ConstantMapper<O, O>().apply(Collections.<O, O>emptyMap(), O.ONE);
    }

    @Test
    public void presentMappingYieldsValue() {
        final Map<O, O> mapping = Collections.singletonMap(O.ONE, O.ANOTHER);
        final ConstantMapper<O, O> mapper = new ConstantMapper<O, O>();
        Assert.assertEquals(O.ANOTHER, mapper.apply(mapping, O.ONE));
    }
}