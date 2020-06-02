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
		
		// check that the input is not null (untested)
		if (input == null) {
//			@TODO(@sach): change == to .equals
			System.out.println("Invalid Input: null input");
			System.exit(0); // fix these panics
		}
		
		// check that the string is not blank (untested)
		if (input.isBlank()) {
			System.out.println("Invalid Input: blank input");
			System.exit(0); // fix these panics
		}
		
		// TODO(@sach): combine all the regexes into one method (isNumeric(String input))
		
		// remove all white spaces (trailing, leading, in-between)
		input = input.replaceAll("\\s","");
		
		// removes any non digit from string
		input = input.replaceAll(p.pattern(), "");
		
		// removes any hanging commas (in the middle)
		input = input.replaceAll(",,+", ",");
		
		// remove leading hanging comma
		// TODO(@sach): use regex
		if (input.startsWith(",")) {
			input = input.substring(1);
		}
		
		// remove leading hanging comma
		//TODO(@sach): use regex
		if (input.endsWith(",")) {
			input = input.substring(0, input.length());
		}
		
//		System.out.println("input string sanitized successfully");
		// FUTURE FEATURE: let the developer know the difference in the strings, what was taken out,
		
		
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
				System.exit(0); // fix these panics
			}						
						
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
			result.add(positivesNumberList.get(i).toString());
			for(int j = i+1; j < positivesNumberList.size(); j++) {
				if ((positivesNumberList.get(j)).equals(positivesNumberList.get(i)+1)){
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
		//		System.out.println("Done");
		String string_result = "";
		for(String num_range : result) {
			string_result = string_result + num_range + ", ";
		};
		
		// trim last comma
		// use regex for this
		string_result = string_result.substring(0, string_result.length()-2);
		
		System.out.println(string_result);
		return string_result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		NumberRangeSummarizer obj = new Demo();
//		Collection<Integer> input = obj.collect("&, !, $, 32, 26, 42, &, !, *, %, 7, 8, 33, 25, 19, 24, 44, 49,"
//				+ " 21, 31, 37, 50, 35, 6, 28, 47, 34, 27, 17, 12, 1, 41, 23, 46, 30, 2, 9,"
//				+ " 18, 20, 40, 36, 48, 5, 15, 4, 39, 43, 13, 14, 10, 11, 45, 3, 38, 16, 22, 29");
//		You requested 1 sets with ?? unique random integers in each, taken from the [1-550] range. 
//		The integers in each set were sorted in ascending order.
//		Collection<Integer> input = obj.collect("233, 389, 279, 254, 392, 263, 221, 301, 531, "
//				+ "518, 373, 523, 177, 390, 466, 208, 136, 541, 349, 211, 158, 422, 397, 82, 260, 243,"
//				+ " 495, 326, 365, 112, 472, 283, 8, 352, 393, 216, 78, 460, 262, 15, 51, 284, 336, 414,"
//				+ " 330, 359, 245, 97, 111, 275, 17, 418, 528, 406, 398, 280, 276, 353, 90, 132, 416, "
//				+ "133, 402, 110, 186, 400, 408, 470, 59, 160, 131, 514, 494, 200, 388, 249, 167, 477, "
//				+ "421, 469, 109, 268, 395, 85, 440, 347, 68, 198, 426, 486, 242, 122, 151, 287, 542, "
//				+ "520, 286, 316, 304, 533, 517, 487, 46, 107, 34, 501, 540, 492, 72, 125, 411, 218, "
//				+ "289, 43, 42, 506, 374, 92, 1, 372, 120, 401, 412, 228, 341, 413, 127, 522, 307, 442, "
//				+ "532, 547, 225, 116, 525, 95, 322, 88, 274, 299, 147, 338, 215, 25, 236, 165, 419, 23, "
//				+ "327, 500, 329, 106, 361, 180, 256, 210, 202, 526, 543, 417, 294, 253, 244, 311, 175, "
//				+ "318, 190, 290, 246, 130, 153, 219, 217, 195, 549, 103, 539, 124, 146, 463, 148, 231, "
//				+ "451, 288, 480, 199, 515, 184, 490, 171, 546, 265, 77, 340, 76, 137, 176, 52, 447, 423, "
//				+ "163, 356, 75, 223, 238, 203, 375, 105, 443, 37, 166, 22, 489, 13, 450, 333, 118, 473, 4, "
//				+ "465, 334, 99, 435, 89, 164, 9, 320, 73, 119, 87, 197, 252, 483, 170, 209, 545, 530, 269,"
//				+ " 3, 196, 121, 27, 183, 113, 479, 507, 485, 298, 488, 270, 476, 386, 30, 277, 431, 453, "
//				+ "100, 370, 155, 67, 503, 293, 544, 297, 405, 234, 379, 456, 484, 537, 69, 40, 204, 441, "
//				+ "2, 302, 101, 206, 339, 438, 378, 93, 58, 384, 358, 504, 527, 396, 114, 497, 53, 387, "
//				+ "98, 36, 229, 427, 115, 65, 33, 513, 91, 337, 11, 187, 357, 24, 35, 48, 66, 491, 462, 70,"
//				+ " 213, 436, 377, 550, 14, 428, 305, 239, 505, 258, 173, 80, 56, 194, 429, 444, 172, 502,"
//				+ " 50, 468, 230, 191, 430, 314, 524, 162, 259, 63, 534, 519, 117, 475, 174, 237, 79, 220,"
//				+ " 31, 350, 351, 235, 86, 508, 267, 247, 41, 38, 62, 457, 159, 382, 509, 135, 319, 321, 511,"
//				+ " 81, 309, 26, 12, 83, 226, 354, 415, 306, 315, 368, 134, 168, 227, 152, 454, 250, 94, 403,"
//				+ " 362, 381, 482, 521, 281, 212, 205, 74, 45, 266, 376, 178, 6, 420, 538, 498, 292, 535, 189,"
//				+ " 452, 344, 369, 467, 224, 264, 510, 313, 342, 261, 7, 391, 303, 461, 360, 96, 49, 29, 19,"
//				+ " 185, 44, 61, 449, 383, 493, 140, 285, 179, 295, 272, 366, 348, 394, 308, 169, 129, 300, 471,"
//				+ " 364, 363, 251, 439, 404, 141, 18, 182, 496, 16, 371, 248, 214, 346, 145, 123, 343, 5, 192,"
//				+ " 222, 434, 548, 157, 28, 385, 332, 143, 64, 102, 345, 273, 139, 60, 455, 448, 161, 55, 323,"
//				+ " 271, 536, 232, 54, 71, 126, 437, 446, 407, 478, 104, 464, 355, 108, 142, 432, 10, 278");
//		You requested 10 sets with 100 unique random integers in each, taken from the [-1000564,3456743] range. 
//		The integers in each set were sorted in ascending order.
//		Collection<Integer> input = obj.collect("-975213, -908915, -872718, -856291, -808170, -732158, -702987, "
//				+ "-638163, -620384, -615614, -607034, -568285, -562089, -438095, -360353, -162120, -141496,"
//				+ " -134892, -125011, -124029, -63572, -19018, -12010, 5299, 46476, 216457, 234671, 289405,"
//				+ " 370842, 374166, 399542, 494808, 521707, 526530, 569355, 636369, 694565, 795456, 875310,"
//				+ " 913074, 980211, 1018193, 1078679, 1116271, 1120071, 1127752, 1133932, 1145205, 1153523,"
//				+ "1183227, 1200483, 1203064, 1212825, 1224559, 1273210, 1298881, 1301589, 1411545, 1486057,"
//				+ " 1517465, 1538706, 1545415, 1626153, 1755210, 1930254, 1940198, 1992405, 2012340, 2050364,"
//				+ " 2082003, 2082742, 2091489, 2117993, 2159113, 2171572, 2205602, 2232525, 2270281, 2466219,"
//				+ " 2504077, 2543091, 2628840, 2672451, 2741584, 2762084, 2833291, 2878373, 2920252, 3007322,"
//				+ " 3011794, 3024757, 3097013, 3128475, 3227265, 3260312, 3292663, 3360372, 3366320, 3388425,"
//				+ " 3446047");
		Collection<Integer> input = obj.collect("-3, -2, -1, 0, 1, 2, 3");
		obj.summarizeCollection(input);
		System.out.println("Done");
	}

}
