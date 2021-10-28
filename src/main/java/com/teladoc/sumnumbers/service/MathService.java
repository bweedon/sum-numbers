package com.teladoc.sumnumbers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class MathService {
    private static Logger Logger = LoggerFactory.getLogger(MathService.class);

    public String sum(String num1, String num2) {
        return sum(num1, num2, Locale.ENGLISH);
    }
    public String sum(String num1, String num2, Locale locale) {
        Logger.trace("Entering, Params - num1: %s, num2: %s, locale: %s", num1, num2, locale);
        BigDecimal num1BD =  convertStringToNumber(num1);
        BigDecimal num2BD = convertStringToNumber(num2);
        BigDecimal sum = num1BD.add(num2BD);
        NumberFormat formatter = NumberFormat.getInstance(locale);
        Logger.debug("Formatting sum: %d");
        String formattedNumber = formatter.format(sum);
        Logger.trace(String.format("Leaving, returning %s", formattedNumber));
        return formattedNumber;
    }

    private BigDecimal convertStringToNumber(String num) {
        Logger.trace(String.format("Entering, Params - num: %s", num));
        try {
            BigDecimal numBD = new BigDecimal(num);
            Logger.trace(String.format("Leaving, returning %f", numBD));
            return numBD;
        } catch (NumberFormatException nfe) {
            Logger.error(String.format("An error occured converting %s to a BigDecimal", num), nfe);
            throw nfe;
        }
    }
}
