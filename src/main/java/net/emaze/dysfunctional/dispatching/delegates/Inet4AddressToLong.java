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
        final byte[] octects = address.getAddress();
        long longAddress = 0;
        for (int i = 0; i != octects.length; ++i) {
            longAddress = longAddress << Byte.SIZE | (octects[i] & 0xff);
        }
        return longAddress;
    }
}
