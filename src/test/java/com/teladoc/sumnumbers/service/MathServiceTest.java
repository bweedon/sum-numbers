package com.teladoc.sumnumbers.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

// This uses spring boot which spins up the whole context. It adds about 300ms to the test run time,
// but this way I can dependency inject the mathService. If I needed to not do this, I would just make
// an @Before method or locally instantiate the MathService in each test.
@SpringBootTest
class MathServiceTest {

    @Autowired
    MathService mathService;

    @ParameterizedTest
    @MethodSource("NoLocaleTestCases")
    void sum_noLocale(String numOne, String numTwo, String expected) {
        String actual = mathService.sum(numOne, numTwo);
        assertThat(actual, is(equalTo(expected)));
    }

    @ParameterizedTest
    @MethodSource("LocaleTestCases")
    void sum_withLocale(String numOne, String numTwo, Locale locale, String expected) {
        String actual = mathService.sum(numOne, numTwo, locale);
        assertThat(actual, is(equalTo(expected)));
    }

    private static Stream<Arguments> NoLocaleTestCases() {
        return Stream.of(
                Arguments.of(
                        "11111111111",
                        "11111111111",
                        "22,222,222,222"),
                Arguments.of(
                        "123",
                        "11",
                        "134"),
                Arguments.of(
                        "123456789012345678901",
                        "12345678",
                        "123,456,789,012,358,024,579"),
                Arguments.of(
                        "111111222222333333444444555555",
                        "777777666666555555444444333333",
                        "888,888,888,888,888,888,888,888,888,888")
        );
    }

    private static Stream<Arguments> LocaleTestCases() {
        return Stream.of(
                Arguments.of(
                        "111111",
                        "111111",
                        Locale.GERMAN,
                        "222.222"
                ),
                Arguments.of(
                        "222222.22",
                        "555555.55",
                        Locale.FRENCH,
                        "777\u00a0777,77"
                )
        );
    }
}