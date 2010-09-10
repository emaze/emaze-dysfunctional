package net.emaze.dysfunctional.contracts;

/**
 *
 * @author rferranti
 */
public abstract class dbc {

    public static interface Statement{
        public boolean evaluate();
    }

    public static void precondition(Statement statement){
        precondition(statement.evaluate(), "precondition failed: %s", statement);
    }

    public static void precondition(boolean assertion, String format, Object... params) {
        if (!assertion) {
            throw new IllegalArgumentException(String.format(format, params));
        }
    }
    
    public static void invariatn(Statement statement){
        invariant(statement.evaluate(), "invariant failed: %s", statement);
    }

    public static void invariant(boolean assertion, String format, Object... params) {
        if (!assertion) {
            throw new IllegalStateException(String.format(format, params));
        }
    }
}
