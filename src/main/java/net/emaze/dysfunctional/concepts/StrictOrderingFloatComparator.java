package net.emaze.dysfunctional.concepts;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * semantic:
 * MIN_FLOAT < negative floats < -0 < +0 < positive floats < MAX_FLOAT < every(NAN)
 * @author rferranti
 */
public class StrictOrderingFloatComparator implements Comparator<Float>, Serializable {

    @Override
    public int compare(Float lhs, Float rhs) {
        dbc.precondition(lhs != null, "null double (lhs) on StrictOrderingFloatComparator");
        dbc.precondition(rhs != null, "null double (rhs) on StrictOrderingFloatComparator");

        if (lhs < rhs) {
            return -1;
        }
        if (lhs > rhs) {
            return +1;
        }
        //Need to compare bits to handle 0.0 == -0.0 being true
        // compare should put -0.0 < +0.0
        // Two NaNs are also == for compare purposes
        // where NaN == NaN is false
        int lhsBits = Float.floatToIntBits(lhs);
        int rhsBits = Float.floatToIntBits(rhs);
        if (lhsBits == rhsBits) {
            return 0;
        }
        //Something exotic! A comparison to NaN or 0.0 vs -0.0
        //Fortunately NaN's int is > than everything else
        //Also negzeros bits < poszero
        //NAN: 2143289344
        //MAX: 2139095039
        //NEGZERO: -2147483648
        if (lhsBits < rhsBits) {
            return -1;
        } else {
            return +1;
        }
    }
}
