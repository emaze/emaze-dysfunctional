package net.emaze.dysfunctional.ranges;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import static net.emaze.dysfunctional.ranges.RangeMother.*;
import net.emaze.dysfunctional.ranges.Range.Endpoint;

public class MakeRangeTest {

    @Test
    public void densifiesRangesOnCreation() {
        final MakeRange<Integer> maker = new MakeRange<Integer>(RangeMother.sequencer, RangeMother.comparator, 0);
        final Range<Integer> got = maker.perform(Arrays.asList(
                r(Endpoint.Include, 0, 5, Endpoint.Exclude),
                r(Endpoint.Include, 5, 10, Endpoint.Exclude)));
        Assert.assertEquals(r(Endpoint.Include, 0, 10, Endpoint.Exclude), got);
    }

    @Test
    public void desnifyEmptyRangesIntoEmptyRange() {
        final MakeRange<Integer> maker = new MakeRange<Integer>(sequencer, comparator, 0);
        final Range<Integer> got = maker.perform(Arrays.asList(
                r(Endpoint.Include, 0, 0, Endpoint.Exclude),
                r(Endpoint.Include, 0, 0, Endpoint.Exclude)));
        Assert.assertEquals(r(Endpoint.Include, 0, 0, Endpoint.Exclude), got);
    }
}
