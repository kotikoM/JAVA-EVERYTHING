package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FactorialRegularInputTesting {

    Factorial factorial = new Factorial();

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "5, 120",
            "10, 3628800",
            "15, 1307674368000",
            "20, 2432902008176640000",
            "25, 15511210043330985984000000",
            "30, 265252859812191058636308480000000",
            "50, 30414093201713378043612608166064768844377641568960512000000000000"
    })
    void test(int input, String expected) {
        assertEquals(expected, factorial.factorial(String.valueOf(input)));
    }

}
