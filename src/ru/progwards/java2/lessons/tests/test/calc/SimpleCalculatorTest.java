package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({SimpleCalculatorTest.SumTest.class, SimpleCalculatorTest.DiffTest.class,
        SimpleCalculatorTest.MultTest.class, SimpleCalculatorTest.DivTest.class,
        SimpleCalculatorTest.SumExceptionTest.class, SimpleCalculatorTest.DiffExceptionTest.class,
        SimpleCalculatorTest.MultExceptionTest.class, SimpleCalculatorTest.DivExceptionTest.class})
@RunWith(Suite.class)
public class SimpleCalculatorTest {
    static SimpleCalculator calc;

    @BeforeClass
    public static void init() {
        calc = new SimpleCalculator();
    }

    @RunWith(Parameterized.class)
    public static class SumTest implements CalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index} = ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {45, 32, 77},
                    {-892, 69, -823},
                    {200, 305, 505},
                    {-1, -11, -12},
                    {0, -277, -277}
            });
        }

        @Test
        @Override
        public void testWithParams() {
            assertEquals(expected, calc.sum(val1, val2));
        }
    }

    public static class SumExceptionTest implements ExceptionCalculatorTest {
        @Test(expected = ArithmeticException.class)
        @Override
        public void testException() {
            int val1 = Integer.MIN_VALUE;
            int val2 = Integer.MIN_VALUE;
            assertEquals(val1 + val2, calc.sum(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class DiffTest implements CalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index} = ({0}) - ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {45, 32, 13},
                    {-892, 69, -961},
                    {200, 305, -105},
                    {-1, -11, 10},
                    {0, -277, 277}
            });
        }

        @Test
        @Override
        public void testWithParams() {
            assertEquals(expected, calc.diff(val1, val2));
        }
    }

    public static class DiffExceptionTest implements ExceptionCalculatorTest {
        @Test(expected = ArithmeticException.class)
        @Override
        public void testException() {
            int val1 = Integer.MAX_VALUE;
            int val2 = Integer.MIN_VALUE;
            assertEquals(val1 - val2, calc.diff(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class MultTest implements CalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index} = ({0}) * ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {45, 32, 1440},
                    {-892, 69, -61548},
                    {200, 305, 61000},
                    {-1, -11, 11},
                    {0, -277, 0}
            });
        }

        @Test
        @Override
        public void testWithParams() {
            assertEquals(expected, calc.mult(val1, val2));
        }
    }

    public static class MultExceptionTest implements ExceptionCalculatorTest {
        @Test(expected = ArithmeticException.class)
        @Override
        public void testException() {
            int val1 = Integer.MAX_VALUE;
            int val2 = Integer.MIN_VALUE;
            assertEquals(val1 * val2, calc.mult(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class DivTest implements CalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index} = ({0}) / ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {45, 32, 1},
                    {-892, 69, -12},
                    {200, 305, 0},
                    {234, 2, 117},
                    {32, 8, 4}
            });
        }

        @Test
        @Override
        public void testWithParams() {
            assertEquals(expected, calc.div(val1, val2));
        }
    }

    public static class DivExceptionTest implements ExceptionCalculatorTest {
        @Test(expected = ArithmeticException.class)
        @Override
        public void testException() {
            int val1 = Integer.MAX_VALUE;
            int val2 = 0;
            assertEquals(val1 / val2, calc.div(val1, val2));
        }
    }

    @AfterClass
    public static void destroy() {
        calc = null;
    }
}
