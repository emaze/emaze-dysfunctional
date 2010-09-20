package net.emaze.dysfunctional.ranges.ips;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.ranges.DenseRange;
import net.emaze.dysfunctional.ranges.RangePolicy;

/**
 *
 * @author rferranti
 */
public class InetAddressPolicy implements RangePolicy<Inet4Address> {

    @Override
    public List<DenseRange<Inet4Address>> asNonOverlapping(DenseRange<Inet4Address>... ranges) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String toString(DenseRange<Inet4Address> range) {
        return String.format("%s-%s", range.lower(), range.upper());
    }

    @Override
    public String toString(List<DenseRange<Inet4Address>> ranges) {
        final StringBuilder sb = new StringBuilder();
        for (DenseRange<Inet4Address> range : ranges) {
            sb.append(toString(range));
            sb.append(",");
        }
        return sb.toString();
    }

    private static long asLong(Inet4Address address) {
        final byte[] octects = address.getAddress();
        long longAddress = 0;
        for(int i=0; i != octects.length; ++i){
            longAddress = (longAddress << Byte.SIZE) & octects[3-i];
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
    public int compare(Inet4Address lhs, Inet4Address rhs) {
        return Long.valueOf(asLong(lhs)).compareTo(asLong(rhs));
    }
}
