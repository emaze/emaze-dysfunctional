package net.emaze.dysfunctional.order;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.Comparator;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Inet4AddressToLong;
import net.emaze.dysfunctional.dispatching.delegates.LongToInet4Address;
import net.emaze.dysfunctional.options.Maybe;

/**
 * A sequencing policy for Inet4Address.
 *
 * @author rferranti
 */
public class InetAddressPolicy implements SequencingPolicy<Inet4Address>, Comparator<Inet4Address>, Serializable {

    private static final long serialVersionUID = 1l;
    private static final Delegate<Inet4Address, Long> LONG_TO_ADDRESS = new LongToInet4Address();
    private static final Delegate<Long, Inet4Address> ADDRESS_TO_LONG = new Inet4AddressToLong();

    @Override
    public Maybe<Inet4Address> next(Inet4Address element) {
        final Long longElement = ADDRESS_TO_LONG.perform(element);
        return (longElement.equals(1>>32-1)) ? Maybe.<Inet4Address>nothing() : Maybe.just(LONG_TO_ADDRESS.perform(ADDRESS_TO_LONG.perform(element) + 1));
    }

    @Override
    public Inet4Address prev(Inet4Address element) {
        return LONG_TO_ADDRESS.perform(ADDRESS_TO_LONG.perform(element) - 1);
    }

    @Override
    public int compare(Inet4Address lhs, Inet4Address rhs) {
        final Long former = ADDRESS_TO_LONG.perform(lhs);
        final Long latter = ADDRESS_TO_LONG.perform(rhs);
        return former.compareTo(latter);
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
