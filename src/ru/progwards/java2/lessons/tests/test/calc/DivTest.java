package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DivTest {
    static SimpleCalculator calc;

    @Parameterized.Parameter(0)
    public int val1;
    @Parameterized.Parameter(1)
    public int val2;
    @Parameterized.Parameter(2)
    public int expected;

    @BeforeClass
    public static void init() {
        calc = new SimpleCalculator();
    }

    @Parameterized.Parameters(name = "Тест {index} = ({0}) + ({1}) = {2}")
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
    public void testSum() {
        assertEquals(expected, calc.div(val1, val2));
    }

    @AfterClass
    public static void destroy() {
        calc = null;
    }
}
