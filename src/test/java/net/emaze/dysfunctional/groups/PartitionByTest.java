package net.emaze.dysfunctional.groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.Dispatching;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PartitionByTest {

    private final Provider<List<O>> LIST_FACTORY = Dispatching.compose(new Narrow<List<O>, ArrayList<O>>(), new ArrayListFactory<O>());
    private final Predicate<O> PARTITIONER = new Always<O>();

    @Test(expected = IllegalArgumentException.class)
    public void creatingPartitionByWithNullPartitionerYieldsException() {
        new PartitionBy<List<O>, List<O>, O>(null, LIST_FACTORY, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingPartitionByWithNullAcceptedCollectionProviderYieldsException() {
        new PartitionBy<List<O>, List<O>, O>(PARTITIONER, null, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingPartitionByWithNullRejectedCollectionProviderYieldsException() {
        new PartitionBy<List<O>, List<O>, O>(PARTITIONER, LIST_FACTORY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void partitioningNullIteratorYieldsException() {
        final PartitionBy<List<O>, List<O>, O> partitionBy = new PartitionBy<List<O>, List<O>, O>(PARTITIONER, LIST_FACTORY, LIST_FACTORY);
        partitionBy.perform(null);
    }

    @Test
    public void acceptedValuesAreInFirstCollection() {
        final PartitionBy<List<O>, List<O>, O> partitionBy = new PartitionBy<List<O>, List<O>, O>(PARTITIONER, LIST_FACTORY, LIST_FACTORY);
        final Pair<List<O>, List<O>> got = partitionBy.perform(Iterations.iterator(O.ONE));
        Assert.assertEquals(Arrays.asList(O.ONE), got.first());
    }

    @Test
    public void rejectedValuesAreInSecondCollection() {
        final PartitionBy<List<O>, List<O>, O> partitionBy = new PartitionBy<List<O>, List<O>, O>(new Never<O>(), LIST_FACTORY, LIST_FACTORY);
        final Pair<List<O>, List<O>> got = partitionBy.perform(Iterations.iterator(O.ONE));
        Assert.assertEquals(Arrays.asList(O.ONE), got.second());
    }
}
