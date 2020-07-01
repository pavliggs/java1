package ru.progwards.java2.lessons.annotation;

public class CalculatorTest {
    Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
        System.out.println("Запускается метод с аннотацией @Before");
    }

    @Test(priority = 10)
    public void testSum10() {
        System.out.println(calculator.sum(19, 15));
    }

    @Test(priority = 3)
    public void testDiff3() {
        System.out.println(calculator.diff(76, 79));
    }

    @Test(priority = 6)
    public void testMult6() {
        System.out.println(calculator.mult(12, 12));
    }

    @Test(priority = 5)
    public void testDiff5() {
        System.out.println(calculator.diff(27, 7));
    }

    @Test(priority = 8)
    public void testMult8() {
        System.out.println(calculator.mult(34, 9));
    }

    @Test(priority = 2)
    public void testMult2() {
        System.out.println(calculator.mult(47, 5));
    }

    @Test(priority = 9)
    public void testDiv9() {
        System.out.println(calculator.div(124, 4));
    }

    @Test(priority = 4)
    public void testDiv4() {
        System.out.println(calculator.div(51, 17));
    }

    @Test(priority = 7)
    public void testDiv7() {
        System.out.println(calculator.div(123, 20));
    }

    @Test(priority = 1)
    public void testSum() {
        System.out.println(calculator.sum(2, 7));
    }

    @After
    public void destroy() {
        calculator = null;
        System.out.println("Запускается метод с аннотацией @After");
    }
}
