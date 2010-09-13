package net.emaze.dysfunctional.concepts;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class StrictOrderingDoubleComparator implements Comparator<Double>, Serializable {

    private static final long serialVersionUID = 1l;

    @Override
    public int compare(Double lhs, Double rhs) {
        dbc.precondition(lhs != null, "null double (lhs) on StrictOrderingDoubleComparator");
        dbc.precondition(rhs != null, "null double (rhs) on StrictOrderingDoubleComparator");
        if (lhs < rhs) {
            return -1;
        }
        if (lhs > rhs) {
            return +1;
        }
        // Need to compare bits to handle 0.0 == -0.0 being true
        // compare should put -0.0 < +0.0
        // Two NaNs are also == for compare purposes
        // where NaN == NaN is false
        long lhsBits = Double.doubleToLongBits(lhs);
        long rhsBits = Double.doubleToLongBits(rhs);
        if (lhsBits == rhsBits) {
            return 0;
        }
        // Something exotic! A comparison to NaN or 0.0 vs -0.0
        // Fortunately NaN's long is > than everything else
        // Also negzeros bits < poszero
        // NAN: 9221120237041090560
        // MAX: 9218868437227405311
        // NEGZERO: -9223372036854775808
        if (lhsBits < rhsBits) {
            return -1;
        } else {
            return +1;
        }
    }
}
