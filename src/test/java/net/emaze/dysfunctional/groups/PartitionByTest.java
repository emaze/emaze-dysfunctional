package net.emaze.dysfunctional.groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import java.util.function.Predicate;
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

    private final Supplier<List<O>> LIST_FACTORY = Compositions.compose(new Vary<ArrayList<O>, List<O>>(), new ArrayListFactory<O>());
    private final Predicate<O> PARTITIONER = new Always<O>();

    @Test(expected = IllegalArgumentException.class)
    public void creatingPartitionByWithNullPartitionerYieldsException() {
        new PartitionBy<>(null, LIST_FACTORY, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingPartitionByWithNullAcceptedCollectionProviderYieldsException() {
        new PartitionBy<>(PARTITIONER, null, LIST_FACTORY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingPartitionByWithNullRejectedCollectionProviderYieldsException() {
        new PartitionBy<>(PARTITIONER, LIST_FACTORY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void partitioningNullIteratorYieldsException() {
        final PartitionBy<O, List<O>, List<O>> partitionBy = new PartitionBy<>(PARTITIONER, LIST_FACTORY, LIST_FACTORY);
        partitionBy.apply(null);
    }

    @Test
    public void acceptedValuesAreInFirstCollection() {
        final PartitionBy<O, List<O>, List<O>> partitionBy = new PartitionBy<>(PARTITIONER, LIST_FACTORY, LIST_FACTORY);
        final Pair<List<O>, List<O>> got = partitionBy.apply(Iterations.iterator(O.ONE));
        Assert.assertEquals(Arrays.asList(O.ONE), got.first());
    }

    @Test
    public void rejectedValuesAreInSecondCollection() {
        final PartitionBy<O, List<O>, List<O>> partitionBy = new PartitionBy<>(new Never<O>(), LIST_FACTORY, LIST_FACTORY);
        final Pair<List<O>, List<O>> got = partitionBy.apply(Iterations.iterator(O.ONE));
        Assert.assertEquals(Arrays.asList(O.ONE), got.second());
    }
}
