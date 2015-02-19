package java.util.function;

/**
 * A nullary functor returning a boolean
 *
 * @author rferranti
 */
public interface BooleanSupplier {

    /**
     * States a proposition.
     *
     * @return true or false
     */
    boolean getAsBoolean();
}
