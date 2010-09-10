package net.emaze.dysfunctional.iterations.sequencing;

import net.emaze.dysfunctional.contracts.dbc;

public class IntegerSequencingPolicy implements SequencingPolicy<Integer> {

    @Override
    public Integer next(Integer element) {
        dbc.precondition(element != null, "cannot get next of null from a IntegerSequencingPolicy");
        return ++element;
    }
}
