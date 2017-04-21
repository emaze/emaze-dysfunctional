package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import net.emaze.dysfunctional.ranges.Range.Endpoint;
import static net.emaze.dysfunctional.ranges.RangeMother.*;
import org.junit.Assert;
import org.junit.Test;

public class MakeRangeTest {

    @Test
    public void densifiesRangesOnCreation() {
        final MakeRange<Integer> maker = new MakeRange<>(sequencer, comparator, 0);
        final Range<Integer> got = maker.apply(Arrays.asList(
                r(Endpoint.Include, 0, 5, Endpoint.Exclude),
                r(Endpoint.Include, 5, 10, Endpoint.Exclude)));
        Assert.assertEquals(r(Endpoint.Include, 0, 10, Endpoint.Exclude), got);
    }

    @Test
    public void desnifyEmptyRangesIntoEmptyRange() {
        final MakeRange<Integer> maker = new MakeRange<>(sequencer, comparator, 0);
        final Range<Integer> got = maker.apply(Arrays.asList(
                r(Endpoint.Include, 0, 0, Endpoint.Exclude),
                r(Endpoint.Include, 0, 0, Endpoint.Exclude)));
        Assert.assertEquals(r(Endpoint.Include, 0, 0, Endpoint.Exclude), got);
    }
}
