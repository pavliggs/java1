package ru.progwards.pavliggs.java2.L7;

import ru.progwards.java2.lessons.gc.Heap;
import ru.progwards.pavliggs.java2.L7.Test.Person;

import java.lang.reflect.*;

public class App {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        ReflectionExample example = new ReflectionExample();
        example.number = 100;
        System.out.println(example);

        // изменение поля через RunTime
        Class<ReflectionExample> exampleClass = (Class<ReflectionExample>) example.getClass();
        Field number = exampleClass.getDeclaredField("number");
        number.set(example, 300);
        System.out.println(example);

        // создание объекта через RunTime при помощи конструктора по умолчанию
        Class<ReflectionExample> clazz1 = ReflectionExample.class;
        ReflectionExample example1 = clazz1.newInstance();
        example1.number = 700;
        System.out.println(example1);

        // создание объекта через RunTime при помощи конструктора с параметрами
        Class<ReflectionExample> clazz2 = ReflectionExample.class;
        Constructor<ReflectionExample> constructor = clazz2.getDeclaredConstructor(String.class, int.class);
        ReflectionExample example2 = constructor.newInstance("Pavel", 30);
        System.out.println(example2);

        // вызов метода через RunTime
        Method method = clazz2.getMethod("changeNumber");
        method.invoke(example2);
        System.out.println(example2);

        // вызов метода с параметрами через RunTime
        Method method1 = clazz2.getMethod("changeNumber", int.class);
        method1.invoke(example2, 30);
        System.out.println(example2);

        // получение доступа и изменение поля private
        Field name = clazz2.getDeclaredField("name");
        // true - получаем доступ к полю с модификатором private
        name.setAccessible(true);
        name.set(example2, "Maria");
        System.out.println(example2);

        Class<Heap> clazz = Heap.class;

        System.out.println(clazz.getName());

        printFields(clazz);
        printMethods(clazz);
        printConstructor(clazz);

    }

    Person callConstructor(String name) {
        Person result = null;
        Class<Person> clazz = Person.class;
        try {
            Constructor<Person> constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            try {
                result = constructor.newInstance(name);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        } catch (NoSuchMethodException | SecurityException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    void setName(Person person, String name) {
        Class<Person> clazz = Person.class;
        try {
            Field fieldName = clazz.getDeclaredField("name");
            fieldName.setAccessible(true);
            try {
                fieldName.set(person, name);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        } catch (NoSuchFieldException | SecurityException ex) {
            ex.printStackTrace();
        }
    }

    void callSetName(Person person, String name) {
        Class<Person> clazz = Person.class;
        try {
            Method method = clazz.getDeclaredMethod("setName", String.class);
            method.setAccessible(true);
            try {
                method.invoke(person, name);
            } catch (InvocationTargetException | IllegalAccessException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (NoSuchMethodException | SecurityException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printConstructor(Class<Heap> clazz) {
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c.getName());
        }
    }

    private static void printMethods(Class<Heap> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            int mod = m.getModifiers();
            String modStr = Modifier.toString(mod);
            System.out.println(modStr + " " + m.getReturnType().getSimpleName() + " " + m.getName());
        }
    }

    private static void printFields(Class<Heap> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            int mod = f.getModifiers();
            String modStr = Modifier.toString(mod);
            System.out.println(modStr + " " + f.getType().getSimpleName() + " - " + f.getName());
        }
    }
}
