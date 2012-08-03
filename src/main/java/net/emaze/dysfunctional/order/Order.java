package net.emaze.dysfunctional.order;

import java.util.Comparator;

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

    public boolean isEq() {
        return this == EQ;
    }
    
    public boolean isLte() {
        return this == LT || this == EQ;
    }

    public boolean isGte() {
        return this == GT || this == EQ;
    }
    
     public boolean isLt() {
        return this == LT;
    }

    public boolean isGt() {
        return this == GT;
    }

    public static Order of(int order) {
        return provide(order);
    }
    
    public static <T> Order of(Comparator<T> comparator, T lhs, T rhs) {
        return provide(comparator.compare(lhs, rhs));
    }
    
    private static Order provide(int index) {
        if(index == 0) {
            return EQ;
        }
        else if(index > 0) {
            return GT;
        }
        else {
            return LT;
        }
    }
}
