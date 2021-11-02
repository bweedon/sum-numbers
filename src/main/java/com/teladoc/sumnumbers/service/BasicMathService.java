package com.teladoc.sumnumbers.service;

import com.teladoc.sumnumbers.model.PartialResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicMathService {

    private static Logger logger = LoggerFactory.getLogger(BasicMathService.class);

    /**
     * Takes in two strings that represent numbers and adds them together and returns the result.
     *
     * @param numOne the first number to add
     * @param numTwo the second number to add
     * @return The sum of the two numbers
     * @throws NumberFormatException if one of the numbers is not only numeric characters.
     */
    public static String sum(String numOne, String numTwo) throws NumberFormatException {
        if(numOne.length() > numTwo.length()) {
            return BasicMathService.doSum(numOne, numTwo);
        } else {
            return BasicMathService.doSum(numTwo, numOne);
        }
    }

    /**
     * Takes in two strings that represent numbers and adds them together and returns the result.
     *
     * @param bigNum the number with more digits(if it exists) to add
     * @param smallNum the number with less digits to add
     * @return The sum of the two numbers
     */
    private static String doSum(String bigNum, String smallNum) {
        String result = bigNum.length() > 0 && !bigNum.equals("0") ? bigNum : smallNum;
        if(smallNum.length() != 0) {
            StringBuilder resultBuilder = new StringBuilder();
            int carryOver = 0;
            int largeIndex = bigNum.length() - 1;
            // Loop over the smaller string from the ones place over to the largest place adding
            for(int smallIndex = smallNum.length() - 1; smallIndex >= 0; smallIndex--, largeIndex--) {
                try {
                    // Convert each character to a number. Do it this way instead of Character.NumericValue so
                    // that the program correctly throws a numberformatexception and doesn't silently fail.
                    int num1 = Integer.valueOf(String.valueOf(smallNum.charAt(smallIndex)));
                    int num2 = Integer.valueOf(String.valueOf(bigNum.charAt(largeIndex)));
                    PartialResult sumAndCarryOver = BasicMathService.compute(num1, num2, carryOver);
                    // Set carryover for next loop
                    carryOver = sumAndCarryOver.getCarryOver();
                    resultBuilder.append(sumAndCarryOver.getSum());
                } catch(NumberFormatException nfe) {
                    logger.error(String.format("An error occured converting %s or %s to a number at %d index", bigNum, smallNum, smallIndex), nfe);
                    throw nfe;
                }
            }
            // If they aren't the same length, need to add the front of the bigger number
            // to the result with the carryover
            if(smallNum.length() != bigNum.length() || carryOver != 0) {
                String remainingBigNum = bigNum.substring(0, (bigNum.length() - smallNum.length()));
                String carryOverString = String.valueOf(carryOver);
                // Add on the rest of the digits for the longer number or take the carry over into account
                return BasicMathService.sum(remainingBigNum, carryOverString) + resultBuilder.reverse().toString();
            } else {
                // Both numbers were the same length with no carryover, return the result.
                return resultBuilder.reverse().toString();
            }
        }
        return result;
    }

    private static PartialResult compute(int numOne, int numTwo, int currentCarryover) {
        logger.trace("Entering, with parameters numOne={}, numTwo={}, currentCarryOver={}", numOne, numTwo, currentCarryover);
        PartialResult result = new PartialResult();
        int sum = numOne + numTwo + currentCarryover;
        // Convert back to string
        String stringSum = String.valueOf(sum);
        logger.debug("Sum for numOne={}, numTwo={}, and currentCarryOver={} is {}", numOne, numTwo, currentCarryover, stringSum);
        if(stringSum.length() > 1) {
            result.setCarryOver(Character.getNumericValue(stringSum.charAt(0)));
            result.setSum(String.valueOf(stringSum.charAt(1)));
        } else {
            result.setCarryOver(0);
            result.setSum(stringSum);
        }
        logger.trace("Returning with sum of {} and carryOver of {}", result.getSum(), result.getCarryOver());
        return result;
    }


}
