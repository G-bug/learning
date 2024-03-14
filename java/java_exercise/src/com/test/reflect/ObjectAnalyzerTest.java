package com.test.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzerTest {

    public static void main(String[] args) {
        ArrayList<Integer> squares = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            squares.add(i);
        }

        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}

class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) {
        if (obj == null) return "null";
        if (visited.contains(obj)) return "...";
        visited.add(obj);

        Class cl = obj.getClass();
        if (cl == String.class) return (String) obj;
        if (cl.isArray()) {
            // 得到数组类型
            String r = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(cl); i++) {
                if (i > 0) r += ",";
                Object val = Array.get(cl, i);
                if (cl.getComponentType().isPrimitive()) r += val;
                else r += val.toString();
            }
            return r + "}";
        }

        String r = cl.getName();
        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field f : fields) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    if (!r.endsWith("[")) r += ",";
                    r += f.getName() + "=";
                    try {
                        Class t = f.getType();
                        Object val = f.get(obj);
                        if (t.isPrimitive()) r += val;
                        else r += t.toString();
                    } catch (Exception e) {

                    }
                }
            }

            r += "]";
            cl = cl.getSuperclass();

        } while (cl != null);

        return r;
    }
}
