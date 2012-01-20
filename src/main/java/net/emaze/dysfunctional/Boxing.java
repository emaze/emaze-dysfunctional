package net.emaze.dysfunctional;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * Array boxing and unboxing.
 * @author rferranti
 */
public abstract class Boxing {

    /**
     * Converts an array of booleans to an array of Booleans.
     * @param array the primitive array
     * @return the array of boxed objects
     */
    public static Boolean[] box(boolean[] array) {
        dbc.precondition(array != null, "cannot box a null boolean array");
        final Boolean[] result = new Boolean[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of bytes to an array of Bytes.
     * @param array the primitive array
     * @return the array of boxed objects
     */
    public static Byte[] box(byte[] array) {
        dbc.precondition(array != null, "cannot box a null byte array");
        final Byte[] result = new Byte[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of chars to an array of Characters.
     * @param array the primitive array
     * @return the array of boxed objects
     */
    public static Character[] box(char[] array) {
        dbc.precondition(array != null, "cannot box a null char array");
        final Character[] result = new Character[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of shorts to an array of Shorts.
     * @param array the primitive array
     * @return the array of boxed objects
     */
    public static Short[] box(short[] array) {
        dbc.precondition(array != null, "cannot box a null short array");
        final Short[] result = new Short[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of ints to an array of Integers.
     * @param array the primitive array
     * @return the array of boxed objects
     */
    public static Integer[] box(int[] array) {
        dbc.precondition(array != null, "cannot box a null int array");
        final Integer[] result = new Integer[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of longs to an array of Longs.
     * @param array the primitive array
     * @return the array of boxed objects
     */
    public static Long[] box(long[] array) {
        dbc.precondition(array != null, "cannot box a null long array");
        final Long[] result = new Long[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of floats to an array of Floats.
     * @param array the primitive array
     * @return the array of boxed objects
     */
    public static Float[] box(float[] array) {
        dbc.precondition(array != null, "cannot box a null float array");
        final Float[] result = new Float[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of double to an array of Doubles.
     * @param array the primitive array
     * @return the array of boxed objects
     */
    public static Double[] box(double[] array) {
        dbc.precondition(array != null, "cannot box a null double array");
        final Double[] result = new Double[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of Booleans to an array of booleans.
     * @param array the array of boxed elements
     * @return the array of unboxed primitives
     */
    public static boolean[] unbox(Boolean[] array) {
        dbc.precondition(array != null, "cannot unbox a null Boolean array");
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of Bytes to an array of bytes.
     * @param array the array of boxed elements
     * @return the array of unboxed primitives
     */
    public static byte[] unbox(Byte[] array) {
        dbc.precondition(array != null, "cannot unbox a null Byte array");
        final byte[] result = new byte[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of Characters to an array of chars.
     * @param array the array of boxed elements
     * @return the array of unboxed primitives
     */
    public static char[] unbox(Character[] array) {
        dbc.precondition(array != null, "cannot unbox a null Character array");
        final char[] result = new char[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of Shorts to an array of shorts.
     * @param array the array of boxed elements
     * @return the array of unboxed primitives
     */
    public static short[] unbox(Short[] array) {
        dbc.precondition(array != null, "cannot unbox a null Short array");
        final short[] result = new short[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of Integers to an array of integers.
     * @param array the array of boxed elements
     * @return the array of unboxed primitives
     */
    public static int[] unbox(Integer[] array) {
        dbc.precondition(array != null, "cannot unbox a null Integer array");
        final int[] result = new int[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of Longs to an array of longs.
     * @param array the array of boxed elements
     * @return the array of unboxed primitives
     */
    public static long[] unbox(Long[] array) {
        dbc.precondition(array != null, "cannot unbox a null Long array");
        final long[] result = new long[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of Floats to an array of floats.
     * @param array the array of boxed elements
     * @return the array of unboxed primitives
     */
    public static float[] unbox(Float[] array) {
        dbc.precondition(array != null, "cannot unbox a null Float array");
        final float[] result = new float[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of Doubles to an array of doubles.
     * @param array the array of boxed elements
     * @return the array of unboxed primitives
     */
    public static double[] unbox(Double[] array) {
        dbc.precondition(array != null, "cannot unbox a null Double array");
        final double[] result = new double[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }
}
