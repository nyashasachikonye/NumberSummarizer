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

//	@Test
//	void testNumberRange() {
//		fail("Not yet implemented");
//	}


	@Test
	void testToString() {
		NumberRange num_range = new NumberRange(Integer.valueOf(3),Integer.valueOf(6));
		String expected = "3-6";
		String actual = num_range.toString();
		assertEquals(expected, actual);
	}

}