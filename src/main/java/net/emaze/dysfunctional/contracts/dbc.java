package net.emaze.dysfunctional.contracts;

/**
 *
 * @author rferranti
 */
public abstract class dbc {

    public static interface Statement{
        public boolean evaluate(); //TODO: hamcrest? if so we should write an adapter to throw the right exception
    }

    public static void precondition(Statement statement){
        precondition(statement.evaluate(), "precondition failed: %s", statement);
    }

    public static void precondition(boolean assertion, String format, Object... params) {
        if (!assertion) {
            throw new IllegalArgumentException(String.format(format, params));
        }
    }
    
    public static void invariant(Statement statement){
        invariant(statement.evaluate(), "invariant failed: %s", statement);
    }

    public static void invariant(boolean assertion, String format, Object... params) {
        if (!assertion) {
            throw new IllegalStateException(String.format(format, params));
        }
    }
}
