package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a;
    private double b;
    private double c;
    private double expected;



    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object []> tests(){
        return Arrays.asList(new Object[][]{
                {1, 4, 4, -2.0},
                {2, -12, 18, 3.0},
                {1, -10, 25, 5.0},
                {4, -20, 25, 2.5}
        });
    }

    @Test
    public void testingSingleRootCases(){
        assertEquals(Double.toString(expected), quadraticEquation.solve(a, b, c));
    }
}