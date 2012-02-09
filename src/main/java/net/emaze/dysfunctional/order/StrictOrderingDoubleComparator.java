package net.emaze.dysfunctional.order;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A strict order comparator for doubles.
 *
 * @author rferranti
 */
public class StrictOrderingDoubleComparator implements Comparator<Double>, Serializable {

    private static final long serialVersionUID = 1l;

    /**
     * Need to compare bits to handle 0.0 eq -0.0 being true. Compare should put
     * -0.0 &lt; +0.0. Two NaNs are also equals for compare purposes where NaN
     * eq NaN is false.
     *
     * @param lhs the left hand side comparable
     * @param rhs the right hand side comparable
     * @return the comparison result
     */
    @Override
    public int compare(Double lhs, Double rhs) {
        dbc.precondition(lhs != null, "null double (lhs) on StrictOrderingDoubleComparator");
        dbc.precondition(rhs != null, "null double (rhs) on StrictOrderingDoubleComparator");
        if (lhs < rhs) {
            return Order.LT.order();
        }
        if (lhs > rhs) {
            return Order.GT.order();
        }
        final long lhsBits = Double.doubleToLongBits(lhs);
        final long rhsBits = Double.doubleToLongBits(rhs);
        if (lhsBits == rhsBits) {
            return 0;
        }
        return lhsBits < rhsBits ? Order.LT.order() : Order.GT.order();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StrictOrderingDoubleComparator;
    }

    @Override
    public int hashCode() {
        return StrictOrderingDoubleComparator.class.hashCode();
    }
}
