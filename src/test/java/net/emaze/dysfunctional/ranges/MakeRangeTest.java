package net.emaze.dysfunctional.ranges;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import static net.emaze.dysfunctional.ranges.RangeMother.*;
import net.emaze.dysfunctional.ranges.Range.Endpoint;

public class MakeRangeTest {

    @Test
    public void densifiesRangesOnCreation() {
        Assert.assertEquals(
            r(Endpoint.Include, 0, 10, Endpoint.Exclude),
            new MakeRange(RangeMother.sequencer, RangeMother.comparator, 0).apply(Arrays.asList(r(Endpoint.Include, 0, 5, Endpoint.Exclude), r(Endpoint.Include, 5, 10, Endpoint.Exclude))));
    }
}
