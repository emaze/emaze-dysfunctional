package net.emaze.dysfunctional.dispatching.delegates;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A unary delegate transforming a Long to an Inet4Address.
 *
 * See {@link Inet4AddressToLong}.
 *
 * @author rferranti
 */
public class LongToInet4Address implements Delegate<Inet4Address, Long> {

    @Override
    public Inet4Address perform(Long address) {
        dbc.precondition(address != null, "cannot transform a null Long to an Inet4Address");
        final byte[] octets = new byte[4];
        for (int i = 0; i != octets.length; ++i) {
            final int shiftBy = 24 - Byte.SIZE * i;
            octets[i] = (byte) ((address >>> shiftBy) & 0xff);
        }
        try {
            return (Inet4Address) Inet4Address.getByAddress(octets);
        } catch (UnknownHostException ex) {
            throw new IllegalStateException("Never happens: UnknownHostException building a Inet4Address from octets", ex);
        }
    }
}
