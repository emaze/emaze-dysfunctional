package net.emaze.dysfunctional.time;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DateToLongTest {

    @Test(expected = IllegalArgumentException.class)
    public void convertingNullDateYieldsException() {
        new DateToLong().perform(null);
    }

    @Test
    public void canConvertNow() {
        final Date now = new Date();
        final long got = new DateToLong().perform(now);
        Assert.assertEquals(now.getTime(), got);
    }
}
