package net.emaze.dysfunctional.dispatching.delegates;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class LongToInet4Address implements Delegate<Inet4Address, Long> {

    @Override
    public Inet4Address perform(Long address) {
        dbc.precondition(address != null, "cannot transform a null Long to an Inet4Address");
        final byte[] octects = new byte[4];
        for (int i = 0; i != octects.length; ++i) {
            final int shiftBy = 24 - Byte.SIZE * i;
            octects[i] = (byte) ((address >>> shiftBy) & 0xff);
        }
        try {
            return (Inet4Address) Inet4Address.getByAddress(octects);
        } catch (UnknownHostException ex) {
            throw new IllegalStateException("Never happens: UnknownHostException building a Inet4Address from octects", ex);
        }
    }
    
}
