package net.emaze.dysfunctional.testing;

/**
 * An object with a short class name
 * @author rferranti
 */
public class O {

    public static O ONE = new O();
    public static O ANOTHER = new O();

    public static O create() {
        return new O();
    }

}
