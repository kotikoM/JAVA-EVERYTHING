package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();



    private double a;
    private double b;
    private double c;

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameterized.Parameters
    public static Collection<Object []> tests(){
        return Arrays.asList(new Object[][]{
                {0, -5, 6},
                {0, -11, 30},
                {0, -4, -21},
                {0, -8, -90},
                {0, -2, -24},
        });
    }


    @Test(expected = IllegalArgumentException.class)
    public void testingTwoRootsCases(){
        quadraticEquation.solve(a, b, c);
    }


}
