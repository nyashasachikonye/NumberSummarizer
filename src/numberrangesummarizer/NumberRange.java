/**
 * 
 */
package numberrangesummarizer;

/**
 * @author Sach
 *
 */
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

	@Override
	public String toString() {
		return start + "-" + end;
	}

}