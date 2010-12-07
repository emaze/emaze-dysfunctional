package net.emaze.dysfunctional.order;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class StrictOrderingDoubleComparator implements Comparator<Double>, Serializable {

    private static final long serialVersionUID = 1l;

    /**
     * Need to compare bits to handle 0.0 == -0.0 being true
     * compare should put -0.0 < +0.0
     * Two NaNs are also == for compare purposes
     * where NaN == NaN is false
     * 
     * Something exotic! A comparison to NaN or 0.0 vs -0.0
     * Fortunately NaN's long is > than everything else
     * Also negzeros bits < poszero
     * NAN: 9221120237041090560
     * MAX: 9218868437227405311
     * NEGZERO: -9223372036854775808
     * @param lhs
     * @param rhs
     * @return
     */
    @Override
    public int compare(Double lhs, Double rhs) {
        dbc.precondition(lhs != null, "null double (lhs) on StrictOrderingDoubleComparator");
        dbc.precondition(rhs != null, "null double (rhs) on StrictOrderingDoubleComparator");
        if (lhs < rhs) {
            return Comparing.LHS_IS_LESSER;
        }
        if (lhs > rhs) {
            return Comparing.LHS_IS_GREATER;
        }
        final long lhsBits = Double.doubleToLongBits(lhs);
        final long rhsBits = Double.doubleToLongBits(rhs);
        if (lhsBits == rhsBits) {
            return 0;
        }
        return lhsBits < rhsBits ? Comparing.LHS_IS_LESSER : Comparing.LHS_IS_GREATER;
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
