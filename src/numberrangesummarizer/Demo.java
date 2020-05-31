/**
 * 
 */
package numberrangesummarizer;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
//import java.util.Arrays;

/**
 * @author Sach
 *
 */
public class Demo implements NumberRangeSummarizer {

	/**
	 * collect method explanantino
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
	
		
		// spilt the input based on the delimiter ","
		String [] temp = input.split(",");
		// convert all the elements of the array to Integer values
		/** REMEMBER, these can also be floats etc **/
		for(int i = 0; i < temp.length; i++) {
			
			// check type
			// if we can convert to integer, then convert to integer
			Integer tempInt =  Integer.valueOf(0);
			try {
				// convert to int
				tempInt = Integer.valueOf(temp[i]);
			}
			catch(NumberFormatException ex){
				System.out.println("Invalid Input");
				System.exit(0);
			}						
						
//			Integer tempInt = Integer.valueOf(temp[i]);
			// add to the output collection
			
			// check for duplicates
			if (result.contains(tempInt)){
				continue;
			}
			
			result.add(tempInt);
//			System.out.println(Arrays.toString(result.toArray()));
		}
//		System.out.println(result);
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
        	if (num > 0) {
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
				if (negativesNumberList.get(i)+1 == negativesNumberList.get(j)){
					// great
					// extend the range of the last element in the result set
					// get the last element
					oldString = result.get(result.size()-1);
					// if the old string was a range already
					if (oldString.contains("--")){
						// then get the upper limit
						newString = oldString.split("--")[0] + "-" + negativesNumberList.get(j);
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
			result.add(positivesNumberList.get(i).toString());
			for(int j = i+1; j < positivesNumberList.size(); j++) {
				if (positivesNumberList.get(i)+1 == positivesNumberList.get(j)){
					// great
					// extend the range of the last element in the result set
					// get the last element
					oldString = result.get(result.size()-1);
					// if the old string was a range already
					if (oldString.contains("-")){
						// then get the upper limit
						newString = oldString.split("-")[0]+"-"+positivesNumberList.get(j);
					}
					else {
						// then make it a range
						newString = oldString + "-" + positivesNumberList.get(j);
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
//		System.out.println(result);
		//		System.out.println("Done");
		String string_result = "";
		for(String num_range : result) {
			string_result = string_result + num_range + ", ";
		};
		
		//trim last comma
		string_result = string_result.substring(0, string_result.length()-2);
		
//		System.out.println(string_result);
		return string_result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		NumberRangeSummarizer obj = new Demo();
		Collection<Integer> input = obj.collect("-8,-4,-1,-2,-3,6,7,8,12,13,14,15,21,22,23,24,31");
		obj.summarizeCollection(input);
		System.out.println("Done");
	}

}
