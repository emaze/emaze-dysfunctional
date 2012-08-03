package net.emaze.dysfunctional.ranges;
import net.emaze.dysfunctional.order.Order;

public enum Endpoints {

    IncludeBoth(true, true), IncludeLeft(true, false), IncludeRight(false, true), IncludeNone(false, false);
    private final boolean left;
    private final boolean right;

    private Endpoints(boolean left, boolean right) {
        this.left = left;
        this.right = right;
    }
    
    public static Endpoints from(boolean left, boolean right) {
        return left ? (right ? IncludeBoth : IncludeLeft) : (right ? IncludeRight : IncludeNone);
    }

    public <T> String stringify(DenseRange<T> range){
        final String leftGlyph = left ? "[" : "(";
        final String rightGlyph = right ? "]" : ")";
        return String.format("%s%s-%s%s", leftGlyph, range.lower, range.upper, rightGlyph);
    }

    public <T> boolean leftAccepts(Order o) {
        return o.isGt() || (left && o.isEq());
    }

    public boolean rightAccepts(Order o) {
        return o.isLt() || (right && o.isEq());
    }
    
    public boolean includesLeft() {
        return left;
    }
    
    public boolean includesRight() {
        return right;
    }

}
