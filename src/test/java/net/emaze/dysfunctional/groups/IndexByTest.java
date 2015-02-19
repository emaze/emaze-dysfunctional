package net.emaze.dysfunctional.groups;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.collections.LinkedHashMapFactory;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class IndexByTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullGrouperYieldsException() {
        new IndexBy<HashMap<O, O>, O, O>(null, new HashMapFactory<O, O>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new IndexBy<HashMap<O, O>, O, O>(new Identity<O>(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        IndexBy<HashMap<O, O>, O, O> indexBy = new IndexBy<HashMap<O, O>, O, O>(new Identity<O>(), new HashMapFactory<O, O>());
        indexBy.apply(null);
    }

    @Test
    public void resultingMapContainsIndexedValues() {
        final IndexBy<LinkedHashMap<O, O>, O, O> indexBy = new IndexBy<LinkedHashMap<O, O>, O, O>(new Identity<O>(), new LinkedHashMapFactory<O, O>());
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
        final LinkedHashMap<O, O> indexed = indexBy.apply(iterator);

        final LinkedHashMap<O, O> expected = new LinkedHashMap<O, O>();
        expected.put(O.ONE, O.ONE);
        expected.put(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(expected, indexed);
    }
}