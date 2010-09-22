package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.contracts.dbc;

public class LongSequencingPolicy implements SequencingPolicy<Long> {

    @Override
    public Long next(Long element) {
        dbc.precondition(element != null, "cannot get next of null from a LongSequencingPolicy");
        return element+1;
    }
}
