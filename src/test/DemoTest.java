/**
 * 
 */
package numberrangesummarizer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
/**
 * @author Sach
 *
 */
class DemoTest {

	/**
	 * Test method for {@link numberrangesummarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollect() {
		
		Demo demo = new Demo();
		Collection<Integer> expected = Arrays.asList(1, 2, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String input = "1,2,3,6,7,8,12,13,14,15,21,22,23,24,31";
		Collection<Integer> actual = demo.collect(input);
		System.out.println("The collect method was tested");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link numberrangesummarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollection() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link numberrangesummarizer.Demo#main(java.lang.String[])}.
	 */
	@Test
	void testMain() {
		fail("Not yet implemented");

	}

}
