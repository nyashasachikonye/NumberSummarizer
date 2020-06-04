# README #

This README would normally document whatever steps are necessary to get your application up and running.

### Number Range Summarizer ###

* Quick summary

The number range summarizer is a library designed to convert a comma delimited string of numerical values
into a summarized string of values that are arranged in ascending order. The number range summarizer ensures
that wherein two or more numerical values are found to be consectutive, they will be summarized into a range.

The library has been designed to summarise the following values:
* Integers (positve natural numbers, negative natural numbers including zero)

The library has been built as robustly, thus handles "dirty" inputs that include non-numeric characters and 
symbols, as well as white spaces and other non printable characters.

The library does a best effort cleaning of data. Decimal values are removed from the input.

* Features
- The library is complete with the tests that indicate the cases that have been covered and 
it has been designed in such a way at to be extensible and readble for future reviews.


* Running
- This library was designed as a java project in eclipse, thus can easily be imported and run using Java8 and 
JUnit5. Otherwise from the command line.
