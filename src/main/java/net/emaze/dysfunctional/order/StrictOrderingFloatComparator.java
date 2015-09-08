package net.emaze.dysfunctional.order;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * semantic: MIN_FLOAT < negative floats < -0 < +0 < positive floats < MAX_FLOAT
 * < every(NAN)
 *
 *
 *
 *
 *

 *
 * @author rferranti
 */
public class StrictOrderingFloatComparator implements Comparator<Float>, Serializable {

    private static final long serialVersionUID = 1l;

    /**
     * Need to compare bits to handle 0.0 eq -0.0 being true. Compare should put
     * -0.0 &lt; +0.0. Two NaNs are also equals for compare purposes where NaN
     * eq NaN is false.
     *
     * @param lhs
     * @param rhs
     * @return TODO
     */
    @Override
    public int compare(Float lhs, Float rhs) {
        dbc.precondition(lhs != null, "null double (lhs) on StrictOrderingFloatComparator");
        dbc.precondition(rhs != null, "null double (rhs) on StrictOrderingFloatComparator");
        if (lhs < rhs) {
            return Order.LT.order();
        }
        if (lhs > rhs) {
            return Order.GT.order();
        }
        final int lhsBits = Float.floatToIntBits(lhs);
        final int rhsBits = Float.floatToIntBits(rhs);
        if (lhsBits == rhsBits) {
            return 0;
        }
        return lhsBits < rhsBits ? Order.LT.order() : Order.GT.order();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StrictOrderingFloatComparator;
    }

    @Override
    public int hashCode() {
        return StrictOrderingFloatComparator.class.hashCode();
    }
}
