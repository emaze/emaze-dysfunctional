package net.emaze.dysfunctional.order;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NextInetAddressPolicyTest {

    @Test
    public void canPerformNextOnInetAddress() throws UnknownHostException {
        NextInetAddressSequencingPolicy policy = new NextInetAddressSequencingPolicy();
        Inet4Address current = (Inet4Address) Inet4Address.getByName("126.255.255.255");
        Maybe<Inet4Address> got = policy.next(current);
        Assert.assertEquals("127.0.0.0", got.value().getHostAddress());
    }

    @Test
    public void orderIsTheSameAsLongOrder() throws UnknownHostException {
        NextInetAddressSequencingPolicy policy = new NextInetAddressSequencingPolicy();
        Inet4Address former = (Inet4Address) Inet4Address.getByName("127.0.0.1");
        Inet4Address latter = (Inet4Address) Inet4Address.getByName("127.0.0.0");
        Assert.assertEquals(Order.GT.order(), policy.compare(former, latter));
    }

    @Test
    public void twoPoliciesHaveSameHashCode() {
        Assert.assertEquals(new NextInetAddressSequencingPolicy().hashCode(), new NextInetAddressSequencingPolicy().hashCode());
    }

    @Test
    public void twoPoliciesAreEquals() {
        Assert.assertEquals(new NextInetAddressSequencingPolicy(), new NextInetAddressSequencingPolicy());
    }
}
