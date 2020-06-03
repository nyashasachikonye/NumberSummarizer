/**
 * 
 */
package numberrangesummarizer;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Sach
 *
 */
public class Demo implements NumberRangeSummarizer {

    /**
    Let’s break down this regex and see how it works:
    * > -? – this part identifies if the given number is negative,
    *  the dash “–” searches for dash literally 
    *  and the question mark “?” marks its presence as an optional one
    * > \d+ – this searches for one or more digits
    * > (\.\d+)? – this part of regex is to identify float numbers.
    * Here we're searching for one or more digits followed by a period. 
    * > The question mark, at the end, signifies that this complete group is optional
    **/

    //TODO(@sach): split this regex up into mulitple commented strings
    // TODO(@sach): combine all the regexes into one method (isNumeric(String input))

    private Pattern include_regex = Pattern.compile(
        "["
        // regex components
        +
        "^" // include pattern that of the following: 
        +
        "-?" // negative numbers
        +
        "\\d+" // only digits
        //			+ "(\\.\\d+)?" // float numbers
        +
        "," // the comma as a delimiter
        +
        "." // the comma as a decimal seperator
        +
        "]");

    // TODO(@sach): utilize this method and combine the other regexes 
    //	public boolean isNumeric(String strNum) {
    //	    if (strNum == null) {
    //		@TODO(@sach): change == to .equals
    //	        return false; 
    //	    }
    //	    return pattern.matcher(strNum).matches();
    //	}

    public String sanitize(String input) {

        // check that the input is not null
        if (input == null) {
            // TODO(@sach): change == to .equals
            System.out.println("Invalid Input: null Input");
            //			System.exit(0); // fix these panics
        }

        // check that the input is not blank
        if (input.isBlank()) {
            // TODO(@sach): change == to .equals
            System.out.println("Invalid Input: Blank Input");
            return null;
        }

        // check that the input is not blank
        if (input.isBlank()) {
            // TODO(@sach): change == to .equals
            System.out.println("Invalid Input: Empty Input");
            return null;
        }

        // removes any non digit from string
        input = input.replaceAll(include_regex.pattern(), "");

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

        // remove added white-spaces and parenthesis
        return result.toString().replaceAll("[\\[\\]\\s]", "");

    }

    /**
     * collect method explanation
     * removes decimals
     * removes white-spaces
     * in the case of blank or null input, it returns null and posts a message
     * returns a sorted list of integers or null
     */
    @Override
    public Collection < Integer > collect(String input) {

        // initialise/declare/instantiate output data structure
        // used ArrayList but we wouldnt be expecting any further values right?
        Collection < Integer > result = new ArrayList < Integer > ();
        Integer tempInt = Integer.valueOf(0);

        // input sanitisation
        input = sanitize(input);

        // spilt the input based on the delimiter ","
        String[] split_input = input.split(",");
        // convert all the elements of the array to Integer values
        for (String num: split_input) {
            // convert to integer
            try {
                // convert to int
                tempInt = Integer.valueOf(num);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Input");
                System.exit(0); // fix these panics
            }
            // remove duplicates
            if (result.contains(tempInt)) {
                continue;
            }

            result.add(tempInt);
        }
        //		System.out.println(result);
        return result;
    }

    /**
     * summarizeCollection method explanation
     * takes an a list of natural numbers sorted in ascending order
     * groups consecutive numbers into a range
     * returns a string representing a summary of the numbers
     */
    @Override
    public String summarizeCollection(Collection < Integer > input) {
        ArrayList < Integer > arr = new ArrayList < > (input);

        // create a sorted list
        Collections.sort(arr);

        ArrayList < String > result = new ArrayList < > ();

        //			can you use a lambda expression here?
        // loop through the values
        for (int firstElementIndex = 0; firstElementIndex < arr.size(); firstElementIndex++) {
            // obtain the current iterations variable
            Integer firstElement = arr.get(firstElementIndex);

            // if the element is the last element
            if (firstElementIndex == arr.size() - 1) {
                // add this element to the result
                result.add(Integer.toString(firstElement));
            }

            // check that the this is not the last element
            // check that the next element is not consecutive
            if ((firstElementIndex + 1 < arr.size()) && (firstElement + 1 != arr.get(firstElementIndex + 1))) {
                // add this element to the result
                result.add(Integer.toString(firstElement));
            } else {
                // if the element is consecutive

                Integer lastElement = 0;
                // advance and seek for the next element that is consecutive
                for (int z = firstElementIndex + 1; z < arr.size(); z++) {
                    // if the next element is consecutive, range detected
                    if (z + 1 < arr.size() && (arr.get(z) + 1 == arr.get(z + 1))) {
                        // advance the iterator
                        continue;
                    } else {
                        // if the next element is not consecutive, range ended
                        // obtain the last element
                        lastElement = arr.get(z);

                        // store the range to the result
                        result.add(String.format("%s-%s", firstElement, lastElement));

                        // advance the iterator
                        firstElementIndex = z;

                        // 
                        z = arr.size();
                    }
                }
            }
        }

        // return the result as a string
        String string_result = "";
        for (String num_range: result) {
            string_result = string_result + num_range + ", ";
        };

        // trim last comma
        // use regex for this
        string_result = string_result.substring(0, string_result.length() - 2);

        //				System.out.println(string_result);
        return string_result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        //		NumberRangeSummarizer obj = new Demo();
        //		String input = "63, 291, -93, 500, 7, -26, -77, 118, 76, 169, 192, -188, 237, 9, 117, 310, 463, -59, 497, 133, -98, 484, 400, 135, 266, -24, -110, 91, -134, 470, 297, 141, 16, 5, -197, -162, 324, 406, 453, -104, -152, 153, 180, 235, 432, -179, -105, 197, 272, 44, 283";
        //		System.out.println(input);
        //		Collection<Integer> actual = obj.collect(input);
        //		System.out.println(obj.summarizeCollection(actual));
        System.out.println("Done");
    }

}

// what about the other toString way for integers?