package com.teladoc.sumnumbers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.util.Locale;

public class MathService {
    private static Logger Logger = LoggerFactory.getLogger(MathService.class);

    public String sum(String num1, String num2) {
        return sum(num1, num2, Locale.ENGLISH);
    }
    public String sum(String num1, String num2, Locale locale) {
        Logger.trace("Entering, Params - num1: %s, num2: %s, locale: %s", num1, num2, locale);
        long sum = convertStringToNumber(num1) + convertStringToNumber(num2);
        NumberFormat formatter = NumberFormat.getInstance(locale);
        Logger.debug("Formatting sum: %d");
        String formattedNumber = formatter.format(sum);
        Logger.trace(String.format("Leaving, returning %s", formattedNumber));
        return formattedNumber;
    }

    private long convertStringToNumber(String num) {
        Logger.trace(String.format("Entering, Params - num: %s", num));
        try {
            long longNum = Long.parseLong(num);
            Logger.trace(String.format("Leaving, returning %d", longNum));
            return longNum;
        } catch (NumberFormatException nfe) {
            Logger.error(String.format("An error occured converting %s to a long", num), nfe);
            throw nfe;
        }
    }
}
