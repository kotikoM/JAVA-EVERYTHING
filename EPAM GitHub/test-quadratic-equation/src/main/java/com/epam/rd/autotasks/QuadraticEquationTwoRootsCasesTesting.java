package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a;
    private double b;
    private double c;
    private String expected;

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> tests() {
        return Arrays.asList(new Object[][]{
                {1, -5, 6, "2.0 3.0"},
                {1, -11, 30, "5.0 6.0"},
                {1, -4, -21, "-3.0 7.0"},
                {2, -8, -90, "-5.0 9.0"},
                {2, -2, -24, "-3.0 4.0"}
        });
    }

    @Test
    public void testingTwoRootsCases() {
        String[] expect = expected.split(" ");
        assertTrue((expect[0]+" "+expect[1]).equals(quadraticEquation.solve(a, b, c)) |
                (expect[1]+" "+expect[0]).equals(quadraticEquation.solve(a, b, c)));
    }
}