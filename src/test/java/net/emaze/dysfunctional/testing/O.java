package net.emaze.dysfunctional.testing;

/**
 * An object with a short class name
 * @author rferranti
 */
public class O {

    public static O ONE = new O("ONE");
    public static O ANOTHER = new O("ANOTHER");
    public static O YET_ANOTHER = new O("YET_ANOTHER");
    public static O IGNORED = new O("IGNORED");
    private final String label;

    public O(String label) {
        this.label = label;
    }

    public static O create(String label) {
        return new O(label);
    }

    @Override
    public String toString() {
        return label;
    }
}
