package net.emaze.dysfunctional.iterations;

import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class SingletonIterableTest {

    @Test
    public void canCreateSingletonIterableWithNullElement() {
        new SingletonIterable<O>(null);
    }

    @Test
    public void callingIteratorYieldsSingletongIterator() {
        final Iterable<O> iterable = new SingletonIterable<O>(O.ONE);
        final List<O> got = Consumers.all(iterable);
        Assert.assertEquals(Arrays.asList(O.ONE), got);
    }
}