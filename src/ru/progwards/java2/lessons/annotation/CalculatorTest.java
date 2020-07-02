package ru.progwards.java2.lessons.annotation;

import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
        System.out.println("Запускается метод с аннотацией @Before");
    }

    @Test(priority = 10)
    public void testSum10() {
        int a = 19;
        int b = 15;
        assertEquals(a+b, calculator.sum(a, b));
        System.out.println("Сложение " + a + " + " + b + " успешно протестировано");
    }

    @Test(priority = 3)
    public void testDiff3() {
        int a = 76;
        int b = 79;
        assertEquals(a-b, calculator.diff(a, b));
        System.out.println("Вычитание " + a + " - " + b + " успешно протестировано");
    }

    @Test(priority = 6)
    public void testMult6() {
        int a = 12;
        int b = 13;
        assertEquals(a*b, calculator.mult(a, b));
        System.out.println("Умножение " + a + " * " + b + " успешно протестировано");
    }

    @Test(priority = 5)
    public void testDiff5() {
        int a = 27;
        int b = 7;
        assertEquals(a-b, calculator.diff(a, b));
        System.out.println("Вычитание " + a + " - " + b + " успешно протестировано");
    }

    @Test(priority = 8)
    public void testMult8() {
        int a = 34;
        int b = 9;
        assertEquals(a*b, calculator.mult(a, b));
        System.out.println("Умножение " + a + " * " + b + " успешно протестировано");
    }

    @Test(priority = 2)
    public void testMult2() {
        int a = 47;
        int b = 5;
        assertEquals(a*b, calculator.mult(a, b));
        System.out.println("Умножение " + a + " * " + b + " успешно протестировано");
    }

    @Test(priority = 9)
    public void testDiv9() {
        int a = 124;
        int b = 4;
        assertEquals(a/b, calculator.div(a, b));
        System.out.println("Деление " + a + " / " + b + " успешно протестировано");
    }

    @Test(priority = 4)
    public void testDiv4() {
        int a = 51;
        int b = 17;
        assertEquals(a/b, calculator.div(a, b));
        System.out.println("Деление " + a + " / " + b + " успешно протестировано");
    }

    @Test(priority = 7)
    public void testDiv7() {
        int a = 123;
        int b = 20;
        assertEquals(a/b, calculator.div(a, b));
        System.out.println("Деление " + a + " / " + b + " успешно протестировано");
    }

    @Test(priority = 1)
    public void testSum() {
        int a = 2;
        int b = 9;
        assertEquals(a+b, calculator.sum(a, b));
        System.out.println("Сложение " + a + " + " + b + " успешно протестировано");
    }

    @After
    public void destroy() {
        calculator = null;
        System.out.println("Запускается метод с аннотацией @After");
    }
}
