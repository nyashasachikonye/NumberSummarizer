/**
 * 
 */
package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Sach
 *
 */
class NumberRangeTest {

//	/**
//	 * Test method for {@link numberrangesummarizer.NumberRange#NumberRange(java.lang.Integer, java.lang.Integer)}.
//	 */
//	@Test
//	void testNumberRange() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link numberrangesummarizer.NumberRange#toString()}.
	 */
	@Test
	void testToString() {
		NumberRange num_range = new NumberRange(Integer.valueOf(3),Integer.valueOf(6));
		String expected = "3-6";
		String actual = num_range.toString();
		assertEquals(expected, actual);
	}

}