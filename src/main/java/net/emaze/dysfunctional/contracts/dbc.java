package net.emaze.dysfunctional.contracts;

/**
 * Design by contract.
 *
 * @author rferranti
 */
public abstract class dbc {

    /**
     * Enforces a state precondition, throwing an IllegalArgumentException if
     * the assertion fails.
     *
     * @param assertion the assertion to be enforced
     * @param format the exception message format string
     * @param params the exception message parameters
     * @throws IllegalArgumentException if the assertion fails
     */
    public static void precondition(boolean assertion, String format, Object... params) {
        if (!assertion) {
            throw new IllegalArgumentException(String.format(format, params));
        }
    }

    /**
     * Enforces a state precondition, throwing an IllegalStateException if the
     * assertion fails.
     *
     * @param assertion the assertion to be enforced
     * @param format the exception message format string
     * @param params the exception message parameters
     * @throws IllegalStateException if the assertion fails
     */
    public static void state(boolean assertion, String format, Object... params) {
        if (!assertion) {
            throw new IllegalStateException(String.format(format, params));
        }
    }
}
