package com.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {

    public static void main(String[] args) {

        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("enter class(e.g. com.test.inner.TalkingClock$Timprinter):");
            name = in.nextLine();
        }


        try {

            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("class " + name);
            if (supercl != null && supercl != Object.class) {
                System.out.println("extends: " + supercl.getName());
            }

            System.out.println("\n{");
            printConstructor(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("\n}");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);

    }

    // 打印构造器
    private static void printConstructor(Class cl) {

        Constructor[] constructors = cl.getDeclaredConstructors();

        for (Constructor c : constructors) {

            String name = c.getName();

            System.out.println(" ");
            // public static
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            // Double
            System.out.print(name + "(");


            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                // (java.lang.int,...)
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.print(");");
        }

    }


    private static void printMethods(Class cl) {
        Method[] methods = cl.getMethods();

        for (Method m : methods) {
            Class retType = m.getReturnType();

            String name = m.getName();
            System.out.println(" ");

            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(retType.getName() + " " + name + "(");

            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.print(");");
        }

    }

    private static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(type.getName() + " " + name + ";\n");
        }
    }

}
