/**
 * 
 */
package numberrangesummarizer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;

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
	private Pattern p = Pattern.compile("[^-?\\d+(\\.\\d+)?,]");
	
	// TODO(@sach): utilize this method and combine the other regexes 
//	public boolean isNumeric(String strNum) {
//	    if (strNum == null) {
//		@TODO(@sach): change == to .equals
//	        return false; 
//	    }
//	    return pattern.matcher(strNum).matches();
//	}

	/**
	 * collect method explanation
	 * returns
	 */
	@Override
	public Collection<Integer> collect(String input) {
		/**
		 * ASSUMPTION: the values will be supplied as comma delimited numbers
		 * FUTURE FEATURE: easily change the delimiter
		 **/
		
		// initialise/declare/instantiate output data structure
		// used ArrayList but we wouldnt be expecting any further values right?
		Collection<Integer> result = new ArrayList<Integer>();
	
		// input sanitisation
		
		// check that the input is not null
		if (input == null) {
//			@TODO(@sach): change == to .equals
			System.out.println("Invalid Input: null input");
//			System.exit(0); // fix these panics
		}
		
		// check that the string is not blank (untested)
		if (input.isBlank()) {
			System.out.println("Invalid Input: blank input");
//			System.exit(0); // fix these panics
		}
		
		// TODO(@sach): combine all the regexes into one method (isNumeric(String input))
		
		// remove all white spaces (trailing, leading, in-between)
		input = input.replaceAll("\\s","");
		
		// removes any non digit from string
		input = input.replaceAll(p.pattern(), "");
		
		// removes any escape characters from the string
// TODO(@sach): negative numbers collection test failing
		input = input.replaceAll("[\\(\\)\\+\\-]", "");
		
		// removes any hanging commas (in the middle)
		input = input.replaceAll(",,+", ",");
		
		// remove leading hanging comma
		// TODO(@sach): use regex
		if (input.startsWith(",")) {
			input = input.substring(1);
		}
		
		// remove trailing hanging comma
		//TODO(@sach): use regex
		if (input.endsWith(",")) {
			input = input.substring(0, input.length());
		}
		
		
//		System.out.println("input string sanitized successfully");
//		FUTURE FEATURE: let the developer know the difference in the strings, what was taken out,
		
		// spilt the input based on the delimiter ","
		String [] split_input = input.split(",");
		// convert all the elements of the array to Integer values
		/** REMEMBER, these can also be floats etc **/
		for(String num : split_input) {
			// check type
			// if we can convert to integer, then convert to integer
			Integer tempInt =  Integer.valueOf(0);
			try {
				// convert to int
				tempInt = Integer.valueOf(num);
			}
			catch(NumberFormatException ex){
				// chain catches?
				// final catch! if anything slipped through sanitisation
				System.out.println("Invalid Input");
				System.exit(0); // fix these panics
			}						
						
			// check for duplicates
			if (result.contains(tempInt)){
				continue;
			}
			
			result.add(tempInt);
//			System.out.println(Arrays.toString(result.toArray()));
		}
		System.out.println(result);
		return result;
	}

	/**
	 * summarizeCollection method explanation
	 * returns
	 */
	@Override
	public String summarizeCollection(Collection<Integer> input) {
		
		// sort the collection
		// create a sorted list
		List<Integer> negativesNumberList = new ArrayList<>();
		
		// create a sorted list
		List<Integer> positivesNumberList = new ArrayList<>();
		
		// Add an Iterator to input. 
		for (Integer num : input) 
		{
        	// add it to the list to be sorted
        	// (you can use a lambda expression here)
        	if (num >= 0) {
        		positivesNumberList.add(num);
        	}
        	else if(num < 0) {
        		negativesNumberList.add(num);
        	}
        }
        
//         sort the lists
		Collections.sort(positivesNumberList);
		Collections.sort(negativesNumberList);
		
		// result structure
		List<String> result = new ArrayList<String>();
		
		
		String oldString = "";
		String newString = "";
		//negative numbers
		for(int i = 0; i < negativesNumberList.size(); i++) {
			// use an iterator maybe?
			// write start to result set
			result.add(negativesNumberList.get(i).toString());
			for(int j = i+1; j < negativesNumberList.size(); j++) {
				if ((negativesNumberList.get(j)).equals((negativesNumberList.get(i)+1))){
					// great
					// extend the range of the last element in the result set
					// get the last element
					oldString = result.get(result.size()-1);
					// if the old string was a range already
					if (oldString.contains("--")){
						// then get the upper limit
						// use substring rather
						newString = oldString.split("--")[0] + "-" + negativesNumberList.get(j);
//						(-3--2) -> (-3--1)
						//TODO(@sach): change to use .replace
					}
					else {
						// then make it a range
						newString = oldString + "-" + negativesNumberList.get(j);
					}
					result.set(result.size()-1,  newString);
					// now increment for the next pair
					i = j;	
				}
				else {
					i = j-1;
					break;
				}
			}
		}
		
		//positive numbers
		// if the next element is consecutive, then
		oldString = "";
		newString = "";
		for(int i = 0; i < positivesNumberList.size(); i++) {
			// use an iterator maybe?
			// write start to result set
			result.add(Integer.toString(positivesNumberList.get(i)));
			for(int j = i+1; j < positivesNumberList.size(); j++) {
				if (positivesNumberList.get(j) ==  positivesNumberList.get(i)+1){
					// great
					// extend the range of the last element in the result set
					// get the last element
					oldString = result.get(result.size()-1);
					
					// check the zero for neg-zero-pos discontinuity
//					if(oldString.equals("0")) {
//						System.out.println("zero detected");
//						//check that the previous element didn't end in "-1"
//						if ((result.get(result.size()-2).endsWith("--1"))){
//							// it is a range
//							newString = result.get(result.size()-2).replace("--1", "-"+positivesNumberList.get(j));
//						}
//						else {
//							System.out.println();
//						}
//					}else {
//						 if the old string was a range already
						if (oldString.contains("-")){
							// then get the upper limit
							// use substring ratherInteger.valueOf(3)
							newString = oldString.split("-")[0]+"-"+positivesNumberList.get(j);
							//TODO(@sach): change to use .replace
						}
						else {
							// then make it a range
							newString = oldString + "-" + positivesNumberList.get(j);
						}
//						if((result.get(result.size()-1).startsWith("0"))){
							// check the element before that ends with -1
							// if there is an element before that (make this check)
//							if (result.get(result.size()-2).endsWith("-1")){
//								System.out.println("ends with muinus one");
//								// if the element ends with minus one
//								// we must join the number range
//									// take the minus one number range
//										// if its a range
//								String former = result.get(result.size()-2);
//										// remove the minus one
//								former = former.split("--")[0];
//									// get the latter
//								String latter = result.get(result.size()-1);
//										// remove the zero
//								latter = latter.split("--")[0];
//									// perform the join
//								newString = former + "-" + latter;
//								
//								//pop the two from the result set
//								result.remove(result.size()-1);
//								result.remove(result.size()-1);
//								
//								// write the new result
//								result.add(newString);
//								i = j;
//							}
//							if (result.get(result.size()-1).endsWith("-1")){
//								System.out.println("ends with -1");
//							}
//							System.out.println("starts with zero");
//						} else 
//						{
						result.set(result.size()-1,  newString);
						// now increment for the next pair
						i = j;	
//					}	
				}
				else {
					i = j-1;
					break;
				}
						
			}
		}
		
//		System.out.println(result);
		String string_result = "";
		for(String num_range : result) {
			string_result = string_result + num_range + ", ";
		};
		
		// trim last comma
		// use regex for this
		string_result = string_result.substring(0, string_result.length()-2);
		
//		System.out.println(string_result);
		return string_result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		NumberRangeSummarizer obj = new Demo();
		Collection<Integer> input = obj.collect("&,*,1,2,{,|,3,\\,6,\n,7,@,8,12,-,13,$,14,15,+, 21,22,),23,24,31");
//		"&,*,1,2,{,|,3,\\,6,\n,7,@,8,--s-12E8-13,$,14,15,+, 21,22,),23,24,31"
//		Collection<Integer> input = obj.collect(null);
		obj.summarizeCollection(input);
		System.out.println("Done");
	}

}
