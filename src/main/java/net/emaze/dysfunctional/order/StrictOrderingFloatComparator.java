package net.emaze.dysfunctional.order;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * semantic:
 * MIN_FLOAT < negative floats < -0 < +0 < positive floats < MAX_FLOAT < every(NAN)
 * @author rferranti
 */
public class StrictOrderingFloatComparator implements Comparator<Float>, Serializable {

    private static final long serialVersionUID = 1l;


    /**
     * Need to compare bits to handle 0.0 == -0.0 being true
     * compare should put -0.0 < +0.0
     * Two NaNs are also == for compare purposes
     * where NaN == NaN is false
     * 
     * Something exotic! A comparison to NaN or 0.0 vs -0.0
     * Fortunately NaN's int is > than everything else
     * Also negzeros bits < poszero
     * NAN: 2143289344
     * MAX: 2139095039
     * NEGZERO: -2147483648
     * @param lhs
     * @param rhs
     * @return
     */
    @Override
    public int compare(Float lhs, Float rhs) {
        dbc.precondition(lhs != null, "null double (lhs) on StrictOrderingFloatComparator");
        dbc.precondition(rhs != null, "null double (rhs) on StrictOrderingFloatComparator");
        if (lhs < rhs) {
            return Comparing.LHS_IS_LESSER;
        }
        if (lhs > rhs) {
            return Comparing.LHS_IS_GREATER;
        }
        final int lhsBits = Float.floatToIntBits(lhs);
        final int rhsBits = Float.floatToIntBits(rhs);
        if (lhsBits == rhsBits) {
            return 0;
        }
        return lhsBits < rhsBits ? Comparing.LHS_IS_LESSER : Comparing.LHS_IS_GREATER;
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
