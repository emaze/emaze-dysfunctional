package net.emaze.dysfunctional.dispatching.delegates;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LongToInet4AddressTest {

    private final LongToInet4Address transformer = new LongToInet4Address();

    @Test(expected = IllegalArgumentException.class)
    public void transformingNullYieldsException() {
        transformer.apply(null);
    }

    @Test
    public void canTransformAnAddress() throws UnknownHostException {
        final Inet4Address got = transformer.apply(0x7f000001L);
        Assert.assertEquals(InetAddress.getByName("127.0.0.1"), got);
    }

    @Test
    public void canTransformNegativeBytes() throws UnknownHostException {
        final Inet4Address got = transformer.apply(0xffffffffL);
        Assert.assertEquals(InetAddress.getByName("255.255.255.255"), got);
    }
}
