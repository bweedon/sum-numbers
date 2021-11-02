# sum-numbers

Write a function that takes two arbitrarily large non-negative numbers as strings and returns a string. This should be production level code.

```java
String addNumbers(String num1, String num2)
```

## Number Type

I ended up using BigDecimal. I know that BigDecimal is similar to BigInteger and that wasn't supposed to be used, so hopefully that is ok. The numbers were too big for long and double/float would end up being `0` in the tens, hundreds, and thousands position when it shouldn't be because they are floating point numbers.

### By Hand

I redid the same functionality by hand in `BasicMathService` after talking with Vinona. I didn't realize why you had said not to use BigInteger until after I finished. I didn't implement the localization that I did with BigDecimal on this one.

## Formatting

I added a method that will format the numbers with commas based on the locale you pass it. It makes the answers not match what is in the instructions but it is supposed to be production level code so I felt like it should be ok.

I can take that out if I need to and just remove the tests that go along with it.

## Running Tests

To run the tests, you should be able to just navigate into the folder and run
```sh
mvn verify
```

## Running the CLI

To run the CLI, you can just run below command inside the folder
```sh
mvn spring-boot:run
```
You will be able to input two numbers and will spit out the result using BigDecimal and the result using the `BasicMathService` by hand method that I wrote.

## TODO
- [x] Convert to an actual number
- [x] Proper exception handling
- [x] Add in an overload for locale and formatting the number
- [ ] Try it in Scala
