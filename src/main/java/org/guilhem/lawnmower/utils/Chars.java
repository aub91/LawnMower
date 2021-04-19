package org.guilhem.lawnmower.utils;

/**
 * Util class for operation with char array.
 * Extract of Guava's Chars class.
 */
public class Chars {
    public static int indexOf(char[] array, char target) {
        return indexOf(array, target, 0, array.length);
    }

    private static int indexOf(char[] array, char target, int start, int end) {
        for(int i = start; i < end; ++i) {
            if (array[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
