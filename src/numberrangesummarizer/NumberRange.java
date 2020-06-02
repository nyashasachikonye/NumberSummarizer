/**
 * 
 */
package numberrangesummarizer;

/**
 * @author Sach
 *
 */

//NummberRange = {{ start , end }}
//
//"{1,1}, {3,3}, {6,8}, 12-15, 21-24, 31";
//
//toString : start = 12, end = 15 -> "12-15"
//
//NUmberRange[] = result
//
//calss NUmberRangeSUmmary{
//	
//	NumberRange[] reusult
//	
//	toString
//}
//
//System.out.print(result)

public class NumberRange {
	
	Integer start;
	Integer end;
	
	/**
	 * 
	 */
	public NumberRange(Integer start, Integer end){
		this.start = start;
		this.end = end;
	}

	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Integer getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Integer end) {
		this.end = end;
	}
	
	// join to number ranges
	public NumberRange join(NumberRange addend, NumberRange augend) {
		// not yet implemented
//		TODO(@sach): implement this
		// check that its possible to join the number ranges (or make that a seperate method? (isConsectutive?))
		return null;
	}
	
	public Boolean isConsecutive(NumberRange numberRangeA, NumberRange numberRangeB) {
		// give those parameters better names
		// not yet implemented
//		TODO(@sach): implement this
		return null;
	}
	
	// NumberRange.extend(5) // this should extend the number range, if possible, from start to 5
	// eg. if the number range was 0-4 and we wanted to extend it to five then it would become 0-5 after the extention
	// must make sure that the number we want to extend by is a consecutive number
	
	// NumberRange.next() // this should give the next consecutive number for a number range
	// eg. for a NumberRange (-2--3), then NumberRange.next() would return 4
	public Integer next() {
		// not yet implemented
//		TODO(@sach): implement this
		return null;
	}
	
	// NumberRange.next() // this should give the next consecutive number for a number range
	// eg. for a NumberRange (-2--3), then NumberRange.previous() would return -3
	public Integer previous() {
		// not yet implemented
//		TODO(@sach): implement this
		return null;
	}
	
	// this returns the range of the number range, that is, how large the difference is between
	// eg. for a singleton, the size would be 1
	// eg. for a NumberRange like (0-2), the range would be 3
	public int size() {
		// not yet implemented
//		TODO(@sach): implement this
		return 0;
	}
	
	// this method determines if the number range is really a range
	public boolean isRange() {
		return this.start.equals(this.end);
	}

	// this method gives the right representation of the number range
	@Override
	public String toString() {
		if (this.isRange()){
		return start + "-" + end;
		}
		else {
			return start.toString();
		}
	}

}