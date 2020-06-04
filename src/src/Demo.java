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
public class Demo implements NumberRangeSummarizer {

	/**
    * TODO(@sach) : method explain
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
     * TODO(@sach) : method explain
     * collect method explanation
     * removes decimals
     * removes white-spaces
     * in the case of blank or null input, it returns null and posts a message
     * returns a sorted list of integers or null
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
     * TODO(@sach) : method explain
     * summarizeCollection method explanation
     * takes an a list of natural numbers sorted in ascending order
     * groups consecutive numbers into a range
     * returns a string representing a summary of the numbers or null
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
     * @param args
     */
    public static void main(String[] args) {

        		NumberRangeSummarizer obj = new Demo();
        		String input = "-1377, -1348, -1347, -1368, ghd, -1352, -1384, -1370, -1383, -1388, -1351, -1380, -1358, -1371, -1344, -1381, -1372, -1355, -1360, -1361, -1378, -1345, -1382, -1356, -1366, -1364, -1373, -1369, -1367, -1379, -1353, -1374, -1385, -1363, -1342, -1365, -1359, -1362, -1350, -1341, -1357, -1376, -1346, -1390, -1349, -1354, -1389, -1387, -1386, -1375";
        		System.out.println(input);
        		Collection<Integer> actual = obj.collect(input);
        		System.out.println(obj.summarizeCollection(actual));
        System.out.println("Done");
    }

}