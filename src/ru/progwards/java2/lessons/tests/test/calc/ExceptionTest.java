package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import static org.junit.Assert.assertEquals;

public class ExceptionTest {
    static SimpleCalculator calc;

    @BeforeClass
    public static void init() {
        calc = new SimpleCalculator();
    }

    @Test(expected = ArithmeticException.class)
    public void testExceptionSum() {
        assertEquals(0, calc.sum(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    @Test(expected = ArithmeticException.class)
    public void testExceptionDiff() {
        assertEquals(0, calc.diff(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test(expected = ArithmeticException.class)
    public void testExceptionMult() {
        assertEquals(0, calc.mult(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test(expected = ArithmeticException.class)
    public void testExceptionDiv() {
        assertEquals(0, calc.div(Integer.MAX_VALUE, 0));
    }

    @AfterClass
    public static void destroy() {
        calc = null;
    }
}
