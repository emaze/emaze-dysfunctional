package net.emaze.dysfunctional.order;

/**
 *
 * @author rferranti
 */
public enum Order {

    LT(-1), EQ(0), GT(1);
    private int order;

    Order(int order) {
        this.order = order;
    }

    public int order() {
        return order;
    }

    public boolean isLte() {
        return this == LT || this == EQ;
    }

    public boolean isGte() {
        return this == GT || this == EQ;
    }

    public static Order from(int order) {
        return Order.values()[order + 1];
    }
}
