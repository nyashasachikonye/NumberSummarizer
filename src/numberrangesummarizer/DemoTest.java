/**
 * 
 */
package numberrangesummarizer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

/**
 * @author Sach
 *
 */
class DemoTest {

	// tests for collect method
	/**
	 * Test method for {@link numberrangesummarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectGoldenPath() {
		Demo demo = new Demo();
		Collection<Integer> expected = Arrays.asList(1, 2, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String input = "1,2,3,6,7,8,12,13,14,15,21,22,23,24,31";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link numberrangesummarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectNegativeNumbers() {
		Demo demo = new Demo();
		Collection<Integer> expected = Arrays.asList(-1, -2, -3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String input = "-1,-2,-3,6,7,8,12,13,14,15,21,22,23,24,31";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}
	
	//tests for summarizeCollection method
	/**
	 * Test method for {@link numberrangesummarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionGoldenPath() {
		Demo demo = new Demo();
		Collection<Integer> input = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String expected = "1, 3, 6-8, 12-15, 21-24, 31";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link numberrangesummarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionNegativeNumbers() {
		Demo demo = new Demo();
		Collection<Integer> input = Arrays.asList(-8,-4,-1,-2,-3,6,7,8,12,13,14,15,21,22,23,24,31);
		String expected = "-8, -4--1, 6-8, 12-15, 21-24, 31";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}
}