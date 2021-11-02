package com.teladoc.sumnumbers.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BasicMathServiceTest {

    @ParameterizedTest
    @MethodSource("SumTestCases")
    void sum(String numOne, String numTwo, String expected) {
        assertThat(BasicMathService.sum(numOne, numTwo), is(equalTo(expected)));
    }

    @ParameterizedTest
    @MethodSource("SumNumberFormatTestCases")
    void sum_shouldThrowNumberFormat(String numOne, String numTwo) {
        assertThrows(NumberFormatException.class,
                () -> BasicMathService.sum(numOne, numTwo));
    }

    private static Stream<Arguments> SumTestCases() {
        return Stream.of(
                Arguments.of(
                        "11111111111",
                        "11111111111",
                        "22222222222"),
                Arguments.of(
                        "123",
                        "11",
                        "134"),
                Arguments.of(
                        "123456789012345678901",
                        "12345678",
                        "123456789012358024579"),
                Arguments.of(
                        "111111222222333333444444555555",
                        "777777666666555555444444333333",
                        "888888888888888888888888888888"),
                Arguments.of(
                        "111111222222333333444444555555",
                        "",
                        "111111222222333333444444555555"),
                Arguments.of(
                        "5566778899",
                        "221111",
                        "5567000010"),
                Arguments.of(
                        "12131415",
                        "22",
                        "12131437"
                ),
                Arguments.of(
                        "9",
                        "1",
                        "10"
                ),
                Arguments.of(
                        "800",
                        "200",
                        "1000"
                )
        );
    }

    private static Stream<Arguments> SumNumberFormatTestCases() {
        return Stream.of(
                Arguments.of(
                        "abc123",
                        "444"
                ),
                Arguments.of(
                        "1,234",
                        "56789."
                )
        );
    }
}