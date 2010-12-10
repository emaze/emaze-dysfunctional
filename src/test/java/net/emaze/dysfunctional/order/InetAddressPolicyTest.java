package net.emaze.dysfunctional.order;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InetAddressPolicyTest {

    @Test
    public void canPerformNextOnInetAddress() throws UnknownHostException {
        InetAddressPolicy policy = new InetAddressPolicy();
        Inet4Address current = (Inet4Address) Inet4Address.getByName("126.255.255.255");
        Inet4Address got = policy.next(current);
        Assert.assertEquals("127.0.0.0", got.getHostAddress());
    }

    @Test
    public void canPerformPrevOnInetAddress() throws UnknownHostException {
        InetAddressPolicy policy = new InetAddressPolicy();
        Inet4Address current = (Inet4Address) Inet4Address.getByName("127.0.0.0");
        Inet4Address got = policy.prev(current);
        Assert.assertEquals("126.255.255.255", got.getHostAddress());
    }
    
    @Test
    public void orderIsTheSameAsLongOrder() throws UnknownHostException {
        InetAddressPolicy policy = new InetAddressPolicy();
        Inet4Address former = (Inet4Address) Inet4Address.getByName("127.0.0.1");
        Inet4Address latter = (Inet4Address) Inet4Address.getByName("127.0.0.0");
        Assert.assertEquals(Order.LHS_IS_GREATER, policy.compare(former, latter));
    }

    @Test
    public void twoPoliciesHaveSameHashCode(){
        Assert.assertEquals(new InetAddressPolicy().hashCode(), new InetAddressPolicy().hashCode());
    }
    @Test
    public void twoPoliciesAreEquals(){
        Assert.assertEquals(new InetAddressPolicy(), new InetAddressPolicy());
    }
}
