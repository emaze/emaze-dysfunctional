package net.emaze.dysfunctional.dispatching.delegates;

import java.net.Inet4Address;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class Inet4AddressToLong implements Delegate<Long, Inet4Address> {

    @Override
    public Long perform(Inet4Address address) {
        dbc.precondition(address != null, "cannot transform a null Inet4Address to Long");
        final byte[] octets = address.getAddress();
        long longAddress = 0;
        for (int i = 0; i != octets.length; ++i) {
            longAddress = longAddress << Byte.SIZE | (octets[i] & 0xff);
        }
        return longAddress;
    }
}
