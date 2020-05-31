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
			// convert to int
			Integer tempInt = Integer.valueOf(temp[i]);
			// add to the output collection
			
			// check for duplicates
			if (result.contains(tempInt)){
				continue;
			}
			
			result.add(tempInt);
//			System.out.println(Arrays.toString(result.toArray()));
		}
		return result;
	}

	@Override
	public String summarizeCollection(Collection<Integer> input) {
		
		// first of all sort the collection
		
		// create a sorted list
		List<Integer> lList = new ArrayList<>();
		
		// Add an Iterator to input. 
        Iterator<Integer> it = input.iterator();
        while (it.hasNext())
        	// get this element
        	// add it to the list to be sorted
        	// (you can use a lambda expression here)
            lList.add(it.next());
        
        // sort the list
		Collections.sort(lList);
		
		// result structure
		List<String> result = new ArrayList<String>();

		// if the next element is consecutive, then
		String oldString = "";
		String newString = "";
		for(int i = 0; i < lList.size(); i++) {
			// use an iterator maybe?
			// write start to result set
			result.add(lList.get(i).toString());
			for(int j = i+1; j < lList.size(); j++) {
				if (lList.get(i)+1 == lList.get(j)){
					// great
					// extend the range of the last element in the result set
					// get the last element
					oldString = result.get(result.size()-1);
					// if the old string was a range already
					if (oldString.contains("-")){
						// then get the upper limit
						newString = oldString.split("-")[0]+"-"+lList.get(j);
					}
					else {
						// then make it a range
						newString = oldString + "-" + lList.get(j);
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
		System.out.println(result);
		System.out.println("Done");
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NumberRangeSummarizer obj = new Demo();
		Collection<Integer> input = obj.collect("1,2,3,6,7,8,12,13,14,15,21,22,23,24,31");
		obj.summarizeCollection(input);
	}

}
