package net.emaze.dysfunctional.sequences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.Assert;

public class CollectingTest {

    @Test
    public void flatStreamIntoList() {
        final Stream<List<Integer>> stream = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.<Integer>asList(),
                Arrays.asList(6)).stream();
        final List<Integer> got = stream.collect(Collecting.flat());
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), got);
    }

    @Test
    public void flatStreamIntoCollection() {
        final Stream<List<Integer>> stream = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(3, 4, 5)).stream();
        final Set<Integer> got = stream.collect(Collecting.flat(HashSet::new));
        Assert.assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)), got);
    }
}
