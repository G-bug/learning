package com.test.reflect;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CopyOfTest {

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        a = (int[]) goodCopyOf(a,7);
        System.out.println(Arrays.toString(a));
    }

    public static Object[] badCopyOf(Object[] a, int newLenght) {
        Object[] newArray = new Object[newLenght];
        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLenght));
        return newArray;
    }

    public static Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();

        if (!cl.isArray()) {
            return null;
        }
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }

}
