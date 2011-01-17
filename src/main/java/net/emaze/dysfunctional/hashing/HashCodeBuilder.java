package net.emaze.dysfunctional.hashing;

import net.emaze.dysfunctional.contracts.dbc;

public class HashCodeBuilder {

    private final int iConstant;
    private int iTotal = 0;

    public HashCodeBuilder() {
        iConstant = 37;
        iTotal = 17;
    }

    public HashCodeBuilder(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber) {
        dbc.precondition(initialNonZeroOddNumber != 0, "HashCodeBuilder requires a non zero initial value");
        dbc.precondition(initialNonZeroOddNumber % 2 != 0, "HashCodeBuilder requires an odd initial value");
        dbc.precondition(multiplierNonZeroOddNumber != 0,"HashCodeBuilder requires a non zero multiplier");
        dbc.precondition(multiplierNonZeroOddNumber % 2 != 0,"HashCodeBuilder requires an odd multiplier");
        iConstant = multiplierNonZeroOddNumber;
        iTotal = initialNonZeroOddNumber;
    }
    
    public HashCodeBuilder appendSuper(int superHashCode) {
        iTotal = iTotal * iConstant + superHashCode;
        return this;
    }

    public HashCodeBuilder append(Object object) {
        if (object == null) {
            iTotal = iTotal * iConstant;

        } else {
            if (object.getClass().isArray() == false) {
                //the simple case, not an array, just the element
                iTotal = iTotal * iConstant + object.hashCode();

            } else {
                //'Switch' on type of array, to dispatch to the correct handler
                // This handles multi dimensional arrays
                if (object instanceof long[]) {
                    append((long[]) object);
                } else if (object instanceof int[]) {
                    append((int[]) object);
                } else if (object instanceof short[]) {
                    append((short[]) object);
                } else if (object instanceof char[]) {
                    append((char[]) object);
                } else if (object instanceof byte[]) {
                    append((byte[]) object);
                } else if (object instanceof double[]) {
                    append((double[]) object);
                } else if (object instanceof float[]) {
                    append((float[]) object);
                } else if (object instanceof boolean[]) {
                    append((boolean[]) object);
                } else {
                    // Not an array of primitives
                    append((Object[]) object);
                }
            }
        }
        return this;
    }

    public HashCodeBuilder append(long value) {
        iTotal = iTotal * iConstant + ((int) (value ^ (value >> 32)));
        return this;
    }

    public HashCodeBuilder append(int value) {
        iTotal = iTotal * iConstant + value;
        return this;
    }

    public HashCodeBuilder append(short value) {
        iTotal = iTotal * iConstant + value;
        return this;
    }

    public HashCodeBuilder append(char value) {
        iTotal = iTotal * iConstant + value;
        return this;
    }

    public HashCodeBuilder append(byte value) {
        iTotal = iTotal * iConstant + value;
        return this;
    }

    public HashCodeBuilder append(double value) {
        return append(Double.doubleToLongBits(value));
    }

    public HashCodeBuilder append(float value) {
        iTotal = iTotal * iConstant + Float.floatToIntBits(value);
        return this;
    }

    public HashCodeBuilder append(boolean value) {
        iTotal = iTotal * iConstant + (value ? 0 : 1);
        return this;
    }

    public HashCodeBuilder append(Object[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    public HashCodeBuilder append(long[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    public HashCodeBuilder append(int[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    public HashCodeBuilder append(short[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    public HashCodeBuilder append(char[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    public HashCodeBuilder append(byte[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    public HashCodeBuilder append(double[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    public HashCodeBuilder append(float[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    public HashCodeBuilder append(boolean[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for(boolean element: array){
                append(element);
            }
        }
        return this;
    }

    public int toHashCode() {
        return iTotal;
    }

    @Override
    public int hashCode() {
        return iTotal;
    }

    @Override
    public boolean equals(Object rhs) {
        if(rhs instanceof HashCodeBuilder == false){
            return false;
        }
        final HashCodeBuilder other = (HashCodeBuilder) rhs;
        return this.iTotal == other.iTotal &&
                this.iConstant == other.iConstant;
    }

    

}