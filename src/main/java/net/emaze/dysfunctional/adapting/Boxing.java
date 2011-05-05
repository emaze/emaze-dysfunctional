package net.emaze.dysfunctional.adapting;

/**
 * 
 * @author rferranti
 */
public abstract class Boxing {

    public static Boolean[] box(boolean[] array) {
        final Boolean[] result = new Boolean[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Byte[] box(byte[] array) {
        final Byte[] result = new Byte[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Character[] box(char[] array) {
        final Character[] result = new Character[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Short[] box(short[] array) {
        final Short[] result = new Short[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Integer[] box(int[] array) {
        final Integer[] result = new Integer[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Long[] box(long[] array) {
        final Long[] result = new Long[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Float[] box(float[] array) {
        final Float[] result = new Float[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static Double[] box(double[] array) {
        final Double[] result = new Double[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static boolean[] unbox(Boolean[] array) {
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static byte[] unbox(Byte[] array) {
        final byte[] result = new byte[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static char[] unbox(Character[] array) {
        final char[] result = new char[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static short[] unbox(Short[] array) {
        final short[] result = new short[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static int[] unbox(Integer[] array) {
        final int[] result = new int[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static long[] unbox(Long[] array) {
        final long[] result = new long[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static float[] unbox(Float[] array) {
        final float[] result = new float[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static double[] unbox(Double[] array) {
        final double[] result = new double[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }
}
