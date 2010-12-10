package net.emaze.dysfunctional.order;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Comparator;

/**
 *
 * @author rferranti
 */
public class InetAddressPolicy implements SequencingPolicy<Inet4Address>, Comparator<Inet4Address>, Serializable {

    private static final long serialVersionUID = 1l;

    private static long asLong(Inet4Address address) {
        final byte[] octects = address.getAddress();
        long longAddress = 0;
        for (int i = 0; i != octects.length; ++i) {
            longAddress = longAddress << Byte.SIZE | ( octects[i] & 0xff);
        }
        return longAddress;
    }

    private static Inet4Address fromLong(long address) {
        try {
            final byte[] octects = new byte[4];
            for (int i = 0; i != octects.length; ++i) {
                int shiftLen = 24 - Byte.SIZE * i;
                octects[i] = (byte) ((address >> shiftLen) & 0xff);
            }
            return (Inet4Address) Inet4Address.getByAddress(octects);
        } catch (UnknownHostException ex) {
            throw new RuntimeException("should never happen: UnknownHostException building a Inet4Address from octects", ex);
        }
    }

    @Override
    public Inet4Address next(Inet4Address element) {
        return fromLong(asLong(element) + 1);
    }

    @Override
    public Inet4Address prev(Inet4Address element) {
        return fromLong(asLong(element) - 1);
    }

    @Override
    public int compare(Inet4Address lhs, Inet4Address rhs) {
        return Long.valueOf(asLong(lhs)).compareTo(asLong(rhs));
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof InetAddressPolicy;
    }

    @Override
    public int hashCode() {
        return InetAddressPolicy.class.hashCode();
    }
}
