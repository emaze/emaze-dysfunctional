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
public class Inet4AddressToLongTest {

    private final Inet4AddressToLong transformer = new Inet4AddressToLong();
    
    @Test(expected=IllegalArgumentException.class)
    public void transformingNullYieldsException() {
        transformer.apply(null);
    }
    
    @Test
    public void canTransformAnAddress() throws UnknownHostException {
        final Inet4Address address = (Inet4Address) InetAddress.getByName("127.0.0.1");
        long got = transformer.apply(address);
        Assert.assertEquals(0x7f000001L, got);
    }
    @Test
    public void canTransformNegativeBytes() throws UnknownHostException {
        final Inet4Address address = (Inet4Address) InetAddress.getByName("255.255.255.255");
        long got = transformer.apply(address);
        Assert.assertEquals(0xffffffffL, got);
    }
}
