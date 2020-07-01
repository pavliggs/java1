package ru.progwards.java2.lessons.annotation;

import java.lang.reflect.*;
import java.util.Map;
import java.util.TreeMap;

public class JTest {
   // в methodMap ключом является значение priority() аннотации Test
   Map<Integer,Method> methodMap;

   public JTest() {
      methodMap = new TreeMap<>();
   }

   void run(String name) {
      try {
         Class<?> clazz = Class.forName(name);
         Constructor<?>[] constructors = clazz.getDeclaredConstructors();
         // constructor - конструктор по умолчанию
         Constructor<?> constructor = getConstructor(constructors);
         Method[] methods = clazz.getDeclaredMethods();
         // создаем экземпляр класса при помощи конструктора по умолчанию
         if (constructor != null) {
            Object objClass = constructor.newInstance();
            searchBefore(methods, objClass);
            searchTestAndRunMethods(methods, objClass);
            searchAfter(methods, objClass);
         }

      } catch (ClassNotFoundException | InstantiationException |
              IllegalAccessException | InvocationTargetException ex) {
         ex.printStackTrace();
      }
   }

   // метод получает конструктор без параметров
   private Constructor<?> getConstructor(Constructor<?>[] constructors) {
      for (Constructor<?> c : constructors) {
         if (c.getParameterCount() == 0)
            return c;
      }
      return null;
   }

   // метод ищет метод с аннотацией Before и запускает этот метод
   private void searchBefore(Method[] methods, Object objClass) {
      int count = 0;
      try {
         for (Method m : methods) {
            if (m.isAnnotationPresent(Before.class)) {
               if (count == 0)
                  m.invoke(objClass);
               else
                  throw new RuntimeException("Аннотация Before должна стоять только у одного метода в классе!!!");
               ++count;
            }
         }
      } catch (IllegalAccessException | InvocationTargetException ex) {
         ex.printStackTrace();
      }
   }

   // метод ищет методы с аннотацией Test и кладет их в methodMap (там они сортируются по приоритетам)
   private void searchTestAndRunMethods(Method[] methods, Object objClass) {
      for (Method m : methods) {
         if (m.isAnnotationPresent(Test.class)) {
            Test annotation = m.getAnnotation(Test.class);
            if (annotation.priority() >= 1 && annotation.priority() <= 10)
               methodMap.put(annotation.priority(), m);
         }
      }
      // запускаем методы с аннотацией Test
      runMethods(objClass);
   }

   // метод запускает методы, лежащие в methodMap в порядке приоритета
   private void runMethods(Object objClass) {
      try {
         for (var entry : methodMap.entrySet()) {
            entry.getValue().invoke(objClass);
         }
      } catch (InvocationTargetException | IllegalAccessException ex) {
         ex.printStackTrace();
      }
   }

   // метод ищет метод с аннотацией After и запускает этот метод
   private void searchAfter(Method[] methods, Object objClass) {
      int count = 0;
      try {
         for (Method m : methods) {
            if (m.isAnnotationPresent(After.class)) {
               if (count == 0)
                  m.invoke(objClass);
               else
                  throw new RuntimeException("Аннотация After должна стоять только у одного метода в классе!!!");
               ++count;
            }
         }
      } catch (IllegalAccessException | InvocationTargetException ex) {
         ex.printStackTrace();
      }
   }

   public static void main(String[] args) {
      JTest jTest = new JTest();
      jTest.run("ru.progwards.java2.lessons.annotation.CalculatorTest");
   }
}
