package net.emaze.dysfunctional.time;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LongToDateTest {

    @Test(expected = IllegalArgumentException.class)
    public void convertingNullLongYieldsException() {
        new LongToDate().apply(null);
    }

    @Test
    public void canConvertNow() {
        final Date now = new Date();
        final Date got = new LongToDate().apply(now.getTime());
        Assert.assertEquals(now, got);
    }
}
