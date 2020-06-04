/**
 * 
 */
package src;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Sach
 *
 */
public class Summarizer implements NumberRangeSummarizer {

	/**
    * TODO(@sach) : method explain
    */
    /**
     * TODO(@sach) : method explain
     * collect method explanation
     * removes decimals
     * removes white-spaces
     * in the case of blank or null input, it returns null and posts a message
     * returns a sorted list of integers or null
     */
	/**
     * Performs some safety checks for null input and empty input
     * then proceeds to sort received input. Finally this method consumes
     * the input to create a comma delimited number summary for a given
     * range of numbers, grouping the numbers into a range when
     * they are sequential. 
     * 
     * @return	string of comma-delimited numbers, in ascending order where
     * 			consecutive numbers have been grouped.
     * 
     * 			this function can also return null on encountering a null or
     * 			empty input
     *
     * @param	input: a sanitized string of Integers
     */
	
    public String sanitize(String input) {

        // check that the input is not null
        if (input == null) {
            System.out.println("Invalid Input: null Input");
            return null;
        }

        // check that the input is not blank
        if (input.isBlank()) {
            System.out.println("Invalid Input: Blank Input");
            return null;
        }

        // check that the input is not blank
        if (input.isBlank()) {
            System.out.println("Invalid Input: Empty Input");
            return null;
        }
        
        // regex to determine valid characters in string
        final Pattern valid_chars = Pattern.compile(
    			// regex components
        "["
        +
        "^" 	// include characters that  match the the following: 
        +
        "-?" 	// negative numbers
        +
        "\\d+" 	// digits
        +
        "," 	// comma (delimiter)
        +
        "." 	// the period (decimal)
        +
        "]");

        // removes any non digit from string
        input = input.replaceAll(valid_chars.pattern(), "");

        // spilt the input based on the delimiter ","
        String[] split_input = input.split(",");

        // create a result data structure
        Collection < String > result = new ArrayList < String > ();
        // loop through the array and add each qualifying element to the resultant
        for (String num: split_input) {

            // remove blank elements (" ")
            if (num.isBlank()) {
                continue;
            }

            // remove empty elements ("")
            if (num.isEmpty()) {
                continue;
            }

            // remove decimal elements (12.5)
            if (num.contains(".")) {
                continue;
            }

            // remove duplicates
            if (result.contains(num)) {
                continue;
            }

            result.add(num);
        }

        // sort the elements in the result
        Arrays.sort(result.toArray());

        // remove added white-spaces and square brackets
        return result.toString().replaceAll("[\\[\\]\\s]", "");

    }
    
    /**
     * Collects and parses the input of a comma delimited string of characters
     * to return a sanitized list of Integers sorted in ascending order and safe
     * for performing a summary operation. 
     * 
     * Safety checks for null input are performed and this method may return null
     * if the conversion of the characters has failed or a null input has been
     * supplied. In such a case the reason for failure is reported to the console.
     * 
     * @return	a sanitized list of Integers
     *  
     * 			this function can also return null on encountering a null or
     * 			empty input
     *
     * @param	input: a random assortment of comma-delimited characters as a string
     */
    @Override
    public Collection <Integer> collect(String input) {

        // input sanitization: clean the input string from all non valid characters
        input = sanitize(input);
        
        // if the input is null, sanitization unsuccessful or null input supplied
        if (input == null){
        	System.out.println("Invalid Input");
        	return null;
        }
        
        // spilt the input based on the delimiter ","
        String[] sanitizedInputArray = input.split(",");
        
        // create a result data structure
        Collection <Integer> result = new ArrayList <Integer> ();
        Integer numIntegerElement = null;
        
        // convert all the elements of the list to Integer values
        for (String numStringElement: sanitizedInputArray) {
            // convert to integer
            try {
                // convert to Integer
                numIntegerElement = Integer.valueOf(numStringElement);
            } catch (NumberFormatException ex) {
            	// failed to create Integer
                System.out.println("Invalid Input: Integer Conversion Failed");
                return null;
            }
            
            result.add(numIntegerElement);
        }
        // return the resultant list
        return result;
    }

    /**
     * Performs some safety checks for null input and empty input
     * then proceeds to sort received input. Finally this method consumes
     * the input to create a comma delimited number summary for a given
     * range of numbers, grouping the numbers into a range when
     * they are sequential. 
     * 
     * @return	string of comma-delimited numbers, in ascending order where
     * 			consecutive numbers have been grouped.
     * 
     * 			this function can also return null on encountering a null or
     * 			empty input
     *
     * @param	input: a sanitized list of Integers
     */
    @Override
    public String summarizeCollection(Collection <Integer> input) {
    	
    	// check that the input is not null
        if (input == null) {
            System.out.println("Invalid Input: null Input");
            return null;
        }
        
        // check that the input is not empty
        if (input.isEmpty()) {
            System.out.println("Invalid Input: Empty Input");
            return null;
        }
        
        // create a list for traversal
        ArrayList <Integer> arr = new ArrayList <> (input);

        // sort the list
        Collections.sort(arr);

        // create a result array
        ArrayList <String> result = new ArrayList <> ();
        
        int nextElementIndex = 0;
        int seekElementIndex = 0;
        int currElementIndex = 0;

        // loop through the list of values
        for (; currElementIndex < arr.size(); currElementIndex++) {
            // obtain the current element
            Integer currElement = arr.get(currElementIndex);
            

            // if the element is the last element
            if (currElementIndex == arr.size() - 1) {
                // add this element to the result
                result.add(Integer.toString(currElement));
            }

            // check that the this is not the last element & 
            // the next element is not consecutive
            
            nextElementIndex = currElementIndex + 1;
            if ((nextElementIndex < arr.size()) && (currElement + 1 != arr.get(nextElementIndex))) {
                // add this element to the result
                result.add(Integer.toString(currElement));
            } else {
                // if the element is consecutive
                Integer lastElement = 0;
                
                // advance and seek for the next element that is consecutive
                for (seekElementIndex = nextElementIndex; seekElementIndex < arr.size(); seekElementIndex++) {
                	Integer seekElement = arr.get(seekElementIndex);
                	
                	// if the next element is consecutive, range detected
                    if (seekElementIndex + 1 < arr.size() && (seekElement + 1 == arr.get(seekElementIndex + 1))) {
                        // advance the iterator
                        continue;
                    } else {
                        // if the next element is not consecutive, range ended
                        // obtain the last element
                        lastElement = seekElement;

                        // store the range to the result
                        result.add(String.format("%s-%s", currElement, lastElement));

                        // advance the iterator
                        currElementIndex = seekElementIndex;

                        seekElementIndex = arr.size();
                    }
                }
            }
        }

        // remove added white-spaces and square brackets
        return result.toString().replaceAll("[\\[\\]]", "");
    }

    /**
     * The main function for running the number range summarizer.
     * This function is used to demonstrate the implementation of the
     * interface provided for the NumberRangeSummarizer. 
     * 
     * A new Summarizer Object is created and the given string input 
     * is supplied, this is then passed to it's collect function. 
     * The collect function returns a Collection<Integer> Object 
     * containing a list of numbers that are sanitized.
     * 
     * The Collection<Integer> Object is then passed as an argument
     * to the summarizeCollection function that performs the summarization
     * of the numbers and returns this as a string.
     * 
     * For ease of use, the result is printed to the console.
     *
     * @param  args: the functions does not require any args
     */
    public static void main(String[] args) {
    	NumberRangeSummarizer obj = new Summarizer();
    	String input = "1,2,3,6,7,8,12,13,14,15,21,22,23,24,31";
    	System.out.println("Input:");
    	System.out.println(input);
        Collection<Integer> actual = obj.collect(input);
        System.out.println("Output:");
        System.out.println(obj.summarizeCollection(actual));
    }

}