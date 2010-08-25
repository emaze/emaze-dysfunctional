package net.emaze.disfunctional.concepts;

import java.util.Arrays;

public class EqualsBuilder {

    private boolean isEquals = true;

    public EqualsBuilder appendSuper(boolean superEquals) {
        if (isEquals == false) {
            return this;
        }
        isEquals = superEquals;
        return this;
    }

    public EqualsBuilder append(Object lhs, Object rhs) {
        if (isEquals == false) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            isEquals = false;
            return this;
        }
        Class lhsClass = lhs.getClass();
        if (!lhsClass.isArray()) {
            if (lhs instanceof java.math.BigDecimal) {
                isEquals = (((java.math.BigDecimal)lhs).compareTo((java.math.BigDecimal)rhs) == 0);
            } else {
                // The simple case, not an array, just test the element
                isEquals = lhs.equals(rhs);
            }
        } else if (lhs.getClass() != rhs.getClass()) {
            // Here when we compare different dimensions, for example: a boolean[][] to a boolean[]
            this.isEquals = false;
        }
        // 'Switch' on type of array, to dispatch to the correct handler
        // This handles multi dimensional arrays of the same depth
        else if (lhs instanceof long[]) {
            append((long[]) lhs, (long[]) rhs);
        } else if (lhs instanceof int[]) {
            append((int[]) lhs, (int[]) rhs);
        } else if (lhs instanceof short[]) {
            append((short[]) lhs, (short[]) rhs);
        } else if (lhs instanceof char[]) {
            append((char[]) lhs, (char[]) rhs);
        } else if (lhs instanceof byte[]) {
            append((byte[]) lhs, (byte[]) rhs);
        } else if (lhs instanceof double[]) {
            append((double[]) lhs, (double[]) rhs);
        } else if (lhs instanceof float[]) {
            append((float[]) lhs, (float[]) rhs);
        } else if (lhs instanceof boolean[]) {
            append((boolean[]) lhs, (boolean[]) rhs);
        } else {
            // Not an array of primitives
            append((Object[]) lhs, (Object[]) rhs);
        }
        return this;
    }

    public EqualsBuilder append(long lhs, long rhs) {
        if (isEquals == false) {
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }

    public EqualsBuilder append(int lhs, int rhs) {
        if (isEquals == false) {
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }

    public EqualsBuilder append(short lhs, short rhs) {
        if (isEquals == false) {
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }

    public EqualsBuilder append(char lhs, char rhs) {
        if (isEquals == false) {
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }

    public EqualsBuilder append(byte lhs, byte rhs) {
        if (isEquals == false) {
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }

    public EqualsBuilder append(double lhs, double rhs) {
        if (isEquals == false) {
            return this;
        }
        return append(Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
    }

    public EqualsBuilder append(float lhs, float rhs) {
        if (isEquals == false) {
            return this;
        }
        return append(Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
    }

    public EqualsBuilder append(boolean lhs, boolean rhs) {
        if (isEquals == false) {
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }

    public EqualsBuilder append(Object[] lhs, Object[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public EqualsBuilder append(long[] lhs, long[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public EqualsBuilder append(int[] lhs, int[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public EqualsBuilder append(short[] lhs, short[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public EqualsBuilder append(char[] lhs, char[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public EqualsBuilder append(byte[] lhs, byte[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public EqualsBuilder append(double[] lhs, double[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public EqualsBuilder append(float[] lhs, float[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public EqualsBuilder append(boolean[] lhs, boolean[] rhs) {
        if (isEquals == false) {
            return this;
        }
        this.isEquals = Arrays.equals(lhs, rhs);
        return this;
    }

    public boolean isEquals() {
        return this.isEquals;
    }

}


