package net.emaze.dysfunctional.adapting;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * 
 * @author rferranti
 */
public abstract class Boxing {

    public static Boolean[] box(boolean[] array) {
        dbc.precondition(array != null, "cannot box a null array");
        final Boolean[] result = new Boolean[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Byte[] box(byte[] array) {
        dbc.precondition(array != null, "cannot box a null array");
        final Byte[] result = new Byte[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Character[] box(char[] array) {
        dbc.precondition(array != null, "cannot box a null array");
        final Character[] result = new Character[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Short[] box(short[] array) {
        dbc.precondition(array != null, "cannot box a null array");
        final Short[] result = new Short[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Integer[] box(int[] array) {
        dbc.precondition(array != null, "cannot box a null array");
        final Integer[] result = new Integer[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Long[] box(long[] array) {
        dbc.precondition(array != null, "cannot box a null array");
        final Long[] result = new Long[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Float[] box(float[] array) {
        dbc.precondition(array != null, "cannot box a null array");
        final Float[] result = new Float[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Double[] box(double[] array) {
        dbc.precondition(array != null, "cannot box a null array");
        final Double[] result = new Double[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static boolean[] unbox(Boolean[] array) {
        dbc.precondition(array != null, "cannot unbox a null array");
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static byte[] unbox(Byte[] array) {
        dbc.precondition(array != null, "cannot unbox a null array");
        final byte[] result = new byte[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static char[] unbox(Character[] array) {
        dbc.precondition(array != null, "cannot unbox a null array");
        final char[] result = new char[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static short[] unbox(Short[] array) {
        dbc.precondition(array != null, "cannot unbox a null array");
        final short[] result = new short[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static int[] unbox(Integer[] array) {
        dbc.precondition(array != null, "cannot unbox a null array");
        final int[] result = new int[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static long[] unbox(Long[] array) {
        dbc.precondition(array != null, "cannot unbox a null array");
        final long[] result = new long[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static float[] unbox(Float[] array) {
        dbc.precondition(array != null, "cannot unbox a null array");
        final float[] result = new float[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static double[] unbox(Double[] array) {
        dbc.precondition(array != null, "cannot unbox a null array");
        final double[] result = new double[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }
}
