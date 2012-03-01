package net.emaze.dysfunctional.consumers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import junit.framework.Assert;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.Maps;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConsumeIntoMapTest {

    @Test
    public void consumingEmptyIteratorYieldsEmptyMap() {
        final Iterator<Pair<O, O>> iterator = Iterations.<Pair<O, O>>iterator();
        final ConsumeIntoMap<HashMap<O, O>, O, O> cons = new ConsumeIntoMap<HashMap<O, O>, O, O>(new HashMapFactory<O, O>());
        final HashMap<O, O> got = cons.perform(iterator);
        Assert.assertEquals(new HashMap<O, O>(), got);
    }

    @Test
    public void consumingIteratorYieldsSameValuesAsInIterator() {
        final Iterator<Pair<O, O>> iterator = Iterations.iterator(Pair.of(O.ONE, O.ONE));
        final ConsumeIntoMap<HashMap<O, O>, O, O> cons = new ConsumeIntoMap<HashMap<O, O>, O, O>(new HashMapFactory<O, O>());
        final HashMap<O, O> got = cons.perform(iterator);
        final Map<O, O> expected = Maps.<O, O>builder().add(O.ONE, O.ONE).toMap();
        Assert.assertEquals(got, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        final ConsumeIntoMap<HashMap<O, O>, O, O> cons = new ConsumeIntoMap<HashMap<O, O>, O, O>(new HashMapFactory<O, O>());
        cons.perform(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingConsumerWithNullProviderYieldsException() {
        new ConsumeIntoMap<HashMap<O, O>, O, O>(null);

    }

    @Test(expected = ClassCastException.class)
    public void consumingFromErasureWithWrongTypeYieldsException() {
        final Delegate cons = new ConsumeIntoMap<HashMap<O, O>, O, O>(new HashMapFactory<O, O>());
        cons.perform(new Object());
    }
}
