package net.emaze.dysfunctional.contracts;

/**
 * design by contract
 * @author rferranti
 */
public abstract class dbc {

    public static void precondition(boolean assertion, String format, Object... params) {
        if (!assertion) {
            throw new IllegalArgumentException(String.format(format, params));
        }
    }
    
    public static void stateprecondition(boolean assertion, String format, Object... params) {
        if (!assertion) {
            throw new IllegalStateException(String.format(format, params));
        }
    }
}
