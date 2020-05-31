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

	@Override
	public String toString() {
		return start + "-" + end;
	}

}