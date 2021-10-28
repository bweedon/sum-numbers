package com.teladoc.sumnumbers.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

class MathServiceTest {

    @ParameterizedTest
    @MethodSource("NoLocaleTestCases")
    void sum_noLocale(String numOne, String numTwo, String expected) {
        MathService mathService = new MathService();
        String actual = mathService.sum(numOne, numTwo);
        assertThat(actual, is(equalTo(expected)));
    }

    private static Stream<Arguments> NoLocaleTestCases() {
        return Stream.of(
                Arguments.of("11111111111","11111111111","22,222,222,222"),
                Arguments.of("123","11","134")
                //Arguments.of("123456789012345678901","12345678","123,456,789,012,358,024,579")
        );
    }
}