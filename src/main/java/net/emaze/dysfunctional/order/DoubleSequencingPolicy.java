package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * 
 * @author dangelocola
 */
public class DoubleSequencingPolicy implements SequencingPolicy<Double> {

    @Override
    public Double next(Double element) {
        dbc.precondition(element != null, "cannot get next of null from a DoubleSequencingPolicy");
        return element + 1;
    }

    @Override
    public Double prev(Double element) {
        dbc.precondition(element != null, "cannot get prev of null from a DoubleSequencingPolicy");
        return element - 1;
    }

    @Override
    public int hashCode() {
        return DoubleSequencingPolicy.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DoubleSequencingPolicy;
    }
}
