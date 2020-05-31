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
public class DiffTest {
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
                {45, 32, 13},
                {-892, 69, -961},
                {200, 305, -105},
                {-1, -11, 10},
                {0, -277, 277}
        });
    }

    @Test
    public void testSum() {
        assertEquals(expected, calc.diff(val1, val2));
    }

    @AfterClass
    public static void destroy() {
        calc = null;
    }
}
