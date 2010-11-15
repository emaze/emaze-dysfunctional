package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.contracts.dbc;

public class LongSequencingPolicy implements SequencingPolicy<Long> {

    @Override
    public Long next(Long element) {
        dbc.precondition(element != null, "cannot get next of null from a LongSequencingPolicy");
        return element + 1;
    }

    @Override
    public Long prev(Long element) {
        dbc.precondition(element != null, "cannot get prev of null from a LongSequencingPolicy");
        return element - 1;
    }

    @Override
    public int hashCode() {
        return LongSequencingPolicy.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LongSequencingPolicy;
    }
}
