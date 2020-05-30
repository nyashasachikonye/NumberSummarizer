/**
 * 
 */
package numberrangesummarizer;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Sach
 *
 */
public class Demo implements NumberRangeSummarizer {

	@Override
	public Collection<Integer> collect(String input) {
		/**
		 * ASSUMPTION: the values will be supplied as comma delimited numbers
		 * FUTRE FEATURE: easily change the delimiter
		 **/
		
		// initialise/declare/instantiate output datastructure
		// used ArrayList but we wouldnt be expecting any further values right?
		Collection<Integer> result = new ArrayList<Integer>();
	
		
		// spilt the input based on the delimiter ","
		String [] temp = input.split(",");
		// convert all the elements of the array to int values
		/** REMEMBER, these can also be floats etc **/
		for(int i = 0; i < temp.length; i++) {
			// convert to int
			Integer tempInt = Integer.valueOf(temp[i]);
			// add to the output collection
			result.add(tempInt);
//			System.out.println(Arrays.toString(result.toArray()));
		}
		return result;
	}

	@Override
	public String summarizeCollection(Collection<Integer> input) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NumberRangeSummarizer obj = new Demo();
		Collection<Integer> input = obj.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
		System.out.println(Arrays.toString(input.toArray()));
	}

}
