package net.emaze.dysfunctional.ranges;

public enum Endpoints {

    IncludeBoth(true, true), IncludeLeft(true, false), IncludeRight(false, true), IncludeNone(false, false);
    private final boolean left;
    private final boolean right;

    private Endpoints(boolean left, boolean right) {
        this.left = left;
        this.right = right;
    }

    public <T> String stringify(DenseRange<T> range){
        final String leftGlyph = left ? "[" : "(";
        final String rightGlyph = right ? "]" : ")";
        return String.format("%s%s-%s%s", leftGlyph, range.lower(), range.upper(), rightGlyph);
    }

    public boolean includeLeftEndpoint() {
        return left;
    }

    public boolean includeRightEndpoint() {
        return right;
    }

}
