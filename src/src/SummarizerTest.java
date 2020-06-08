/**
 * 
 */
package src;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

/**
 * @author Sach
 *
 */
class SummarizerTest {

	/**
	 * This is considered the golden path input where the user provides
	 * a string of integers that are in sorted ascending order, without any non-digit characters
	 * or escape characters.
	 * 
	 * The test asserts that the provided input will be returned from the collect method
	 * as list of comma-delimited integers in ascending order
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectGoldenPath() {
		Summarizer demo = new Summarizer();
		Collection<Integer> expected = Arrays.asList(1, 2, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String input = "1,2,3,6,7,8,12,13,14,15,21,22,23,24,31";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provided input contains negative numbers the collect method is able to recognize the
	 * negative numbers as valid values, thereby including these numbers in the resultant list.
	 * 
	 * The test asserts that the provided input will be returned from the collect method
	 * as list of comma-delimited integers in ascending order.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectNegativeNumbersInput() {
		Summarizer demo = new Summarizer();
		Collection<Integer> expected = Arrays.asList(-1, -2, -3, 6, 7);
		String input = "-1,-2,-3,6,7";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provided input contains decimal numbers the collect method is removes these
	 * values as invalid values, thus they are not included in the resultant list.
	 * 
	 * The test asserts that the provided input will be returned from the collect method
	 * as list of comma-delimited integers in ascending order without the decimal numbers.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectDecimalNumbersInput() {
		Summarizer demo = new Summarizer();
		Collection<Integer> expected = Arrays.asList(-1, -2, 6, 7, 8);
		String input = "-1,-2,-3.5,6,7,8,12.4";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provided input contains duplicate numbers the collect method is removes these
	 * values as invalid values, such that only a single occurance remains thus they are not included
	 * in the resultant list.
	 * 
	 * The test asserts that the provided input will be returned from the collect method
	 * as list of comma-delimited integers in ascending order.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectDuplicateNumbersInput() {
		Summarizer demo = new Summarizer();
		Collection<Integer> expected = Arrays.asList(-1, 8, 6, 7);
		String input = "-1,8,6,7,8,-1";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provides and null input the collect method correctly returns a null value.
	 * 
	 * The test asserts that expected null will be returned from the collect method.
	 * An informative log is shown in the console.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectNullInput() {
		Summarizer demo = new Summarizer();
		String input = null;
		Collection<Integer> actual = demo.collect(input);
		assertNull(actual);
	}
	
	/**
	 * When the user provides and null input the collect method correctly returns a .. value.
	 * 
	 * The test asserts that expected ..  will be returned from the collect method.
	 * An informative log is shown in the console.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectBlankInput() {
		Summarizer demo = new Summarizer();
		String input = "";
		Collection<Integer> actual = demo.collect(input);
		assertNull(actual);
	}
	
	/**
	 * When the user provides an empty input the collect method correctly returns a .. value.
	 * 
	 * The test asserts that expected ..  will be returned from the collect method.
	 * An informative log is shown in the console.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectEmptyInput() {
		Summarizer demo = new Summarizer();
		String input = " ";
		Collection<Integer> actual = demo.collect(input);
		assertNull(actual);
	}

	/**
	 * When the user provided input contains non digit numbers such as symbols or letters,
	 * the collect method is removes these values as invalid values, such that 
	 * they are not included in the resultant list.
	 * 
	 * The test asserts that the provided input will be returned from the collect method
	 * as list of comma-delimited integers in ascending order without non-numeric characters.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectNonNumericInput() {
		Summarizer demo = new Summarizer();
		Collection<Integer> expected = Arrays.asList(1, 2, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String input = "&,*, 1,2,3,6,7,@,8,12,13,14h,15,dksg,21,22,23,24,31";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provided input contains non digit numbers such as commas,
	 * the collect method is removes these values as invalid values, such that 
	 * they are not included in the resultant list.
	 * 
	 * The test asserts that the provided input will be returned from the collect method
	 * as list of comma-delimited integers in ascending order without non-numeric characters.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 * test
	 */
	@Test
	void testCollectHangingCommaInput() {
		Summarizer demo = new Summarizer();
		Collection<Integer> expected = Arrays.asList(1, 2, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String input = ",,,,1,2,3,6,7,8,12,,,13,14,15,21,22,23,24,31,,,";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provided input contains non digit numbers such as white-spaces,
	 * the collect method is removes these values as invalid values, such that 
	 * they are not included in the resultant list.
	 * 
	 * The test asserts that the provided input will be returned from the collect method
	 * as list of comma-delimited integers in ascending order without extra white-spaces.
	 * {@link Summarizer.Demo#collect(java.lang.String)}.
	 */
	@Test
	void testCollectWhiteSpacesInput() {
		Summarizer demo = new Summarizer();
		Collection<Integer> expected = Arrays.asList(1, 2, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22);
		String input = "1,2, 3,  6,7 ,8,12  ,13,1 4,1  5, 2 1 ,  2  2  ";
		Collection<Integer> actual = demo.collect(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provided input contains non digit numbers such as escape characters,
	 * the collect method is returns a null as the result.
	 * 
	 * The test asserts that the provided input will be returned from the collect method
	 * as a null value without any further processing. An informative log is shown in the console.
	 * {@link numberrangesummarizer.Demo#collect(java.lang.String)}. escape
	 * characters test
	 */
	 @Test
	 void testCollectEscapeCharacters() {
	 Summarizer demo = new Summarizer();
	 String input = "\"&,*,1,2,{,|,3,\\\\,6,\\n,7,@,8,12,-,13,$,14,15,"
	 + "21,22,),23,24,31\"";
	 Collection<Integer> actual = demo.collect(input);
	 assertNull(actual);
	 }

	 /**
		 * This is considered the golden path input where the user provides
		 * a string of integers that are in sorted ascending order, without any non-digit characters
		 * or escape characters.
		 * 
		 * The test asserts that the provided input will be returned from the collect method
		 * as list of comma-delimited integers in ascending order, where sequential numbers are grouped
		 * together to form a range.
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionGoldenPath() {
		Summarizer demo = new Summarizer();
		Collection<Integer> input = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String expected = "1, 3, 6-8, 12-15, 21-24, 31";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provided input contains negative numbers the SummarizeCollection method is able to recognize the
	 * negative numbers as valid values, thereby including these numbers in the resultant list.
	 * 
	 * The test asserts that the provided input will be returned from the SummarizeCollection method
	 * as list of comma-delimited integers in ascending order, where sequential numbers are grouped
	 * together to form a range.
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionNegativeNumbers() {
		Summarizer demo = new Summarizer();
		Collection<Integer> input = Arrays.asList(-8, -4, -1, -2, -3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
		String expected = "-8, -4--1, 6-8, 12-15, 21-24, 31";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}

	/**
	 * When the user provides an empty list input the SummarizeCollection method correctly returns a null value.
	 * 
	 * The test asserts that expected null will be returned from the SummarizeCollection method.
	 * An informative log is shown in the console.
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionEmptyList() {
		Summarizer demo = new Summarizer();
		Collection<Integer> input = Arrays.asList();
		String actual = demo.summarizeCollection(input);
		assertNull(actual);
	}

	/**
	 * When the user provides and null input the SummarizeCollection method correctly returns a null value.
	 * 
	 * The test asserts that expected null will be returned from the SummarizeCollection method.
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionNullInput() {
		Summarizer demo = new Summarizer();
		String actual = demo.summarizeCollection(null);
		assertNull(actual);
	}

	// mono range tests
	/**
	 * Where the user provides a string of positive integers that are in random order, without any non-digit
	 * characters or escape characters.
	 * 
	 * The test asserts that the provided input will be returned from the SummarizeCollection method
	 * as list of comma-delimited integers in ascending order, where sequential numbers are grouped
	 * together to form a range.
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionMonoRangePositive() {
		Summarizer demo = new Summarizer();
		Collection<Integer> input = Arrays.asList(32, 26, 42, 7, 8, 33, 25, 19, 24, 44, 49, 21, 31, 37, 50, 35, 6, 28,
				47, 34, 27, 17, 12, 1, 41, 23, 46, 30, 2, 9, 18, 20, 40, 36, 48, 5, 15, 4, 39, 43, 13, 14, 10, 11, 45,
				3, 38, 16, 22, 29);
		String expected = "1-50";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}

	/** Where the user provides a string of negative integers that are in random order, without any non-digit
	 * characters or escape characters.
	 * 
	 * The test asserts that the provided input will be returned from the SummarizeCollection method
	 * as list of comma-delimited integers in ascending order, where sequential numbers are grouped
	 * together to form a range.
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionMonoRangeNegative() {
		Summarizer demo = new Summarizer();
		Collection<Integer> input = Arrays.asList(-1377, -1348, -1347, -1368, -1343, -1352, -1384, -1370, -1383, -1388,
				-1351, -1380, -1358, -1371, -1344, -1381, -1372, -1355, -1360, -1361, -1378, -1345, -1382, -1356, -1366,
				-1364, -1373, -1369, -1367, -1379, -1353, -1374, -1385, -1363, -1342, -1365, -1359, -1362, -1350, -1341,
				-1357, -1376, -1346, -1390, -1349, -1354, -1389, -1387, -1386, -1375);
		String expected = "-1390--1341";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}

	// multi range tests
	/** Where the user provides a string of both positive and negative integers that are in random order,
	 *  without any non-digit characters or escape characters. 1 sets with unique random integers in each, taken from
	 * the [1-550] range.
	 * 
	 * The test asserts that the provided input will be returned from the SummarizeCollection method
	 * as list of comma-delimited integers in ascending order, where sequential numbers are grouped
	 * together to form a range.
	 * 
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 * 
	 */
	@Test
	void testSummarizeCollectionMultiRangePositive() {
		Summarizer demo = new Summarizer();
		Collection<Integer> input = Arrays.asList(233, 389, 279, 254, 392, 263, 221, 301, 531, 518, 373, 523, 177, 390,
				466, 208, 136, 541, 349, 211, 158, 422, 397, 82, 260, 243, 495, 326, 365, 112, 472, 283, 8, 352, 393,
				216, 78, 460, 262, 15, 51, 284, 336, 414, 330, 359, 245, 97, 111, 275, 17, 418, 528, 406, 398, 280, 276,
				353, 90, 132, 416, 133, 402, 110, 186, 400, 408, 470, 59, 160, 131, 514, 494, 200, 388, 249, 167, 477,
				421, 469, 109, 268, 395, 85, 440, 347, 68, 198, 426, 486, 242, 122, 151, 287, 542, 520, 286, 316, 304,
				533, 517, 487, 46, 107, 34, 501, 540, 492, 72, 125, 411, 218, 289, 43, 42, 506, 374, 92, 1, 372, 120,
				401, 412, 228, 341, 413, 127, 522, 307, 442, 532, 547, 225, 116, 525, 95, 322, 88, 274, 299, 147, 338,
				215, 25, 236, 165, 419, 23, 327, 500, 329, 106, 361, 180, 256, 210, 202, 526, 543, 417, 294, 253, 244,
				311, 175, 318, 190, 290, 246, 130, 153, 219, 217, 195, 549, 103, 539, 124, 146, 463, 148, 231, 451, 288,
				480, 199, 515, 184, 490, 171, 546, 265, 77, 340, 76, 137, 176, 52, 447, 423, 163, 356, 75, 223, 238,
				203, 375, 105, 443, 37, 166, 22, 489, 13, 450, 333, 118, 473, 4, 465, 334, 99, 435, 89, 164, 9, 320, 73,
				119, 87, 197, 252, 483, 170, 209, 545, 530, 269, 3, 196, 121, 27, 183, 113, 479, 507, 485, 298, 488,
				270, 476, 386, 30, 277, 431, 453, 100, 370, 155, 67, 503, 293, 544, 297, 405, 234, 379, 456, 484, 537,
				69, 40, 204, 441, 2, 302, 101, 206, 339, 438, 378, 93, 58, 384, 358, 504, 527, 396, 114, 497, 53, 387,
				98, 36, 229, 427, 115, 65, 33, 513, 91, 337, 11, 187, 357, 24, 35, 48, 66, 491, 462, 70, 213, 436, 377,
				550, 14, 428, 305, 239, 505, 258, 173, 80, 56, 194, 429, 444, 172, 502, 50, 468, 230, 191, 430, 314,
				524, 162, 259, 63, 534, 519, 117, 475, 174, 237, 79, 220, 31, 350, 351, 235, 86, 508, 267, 247, 41, 38,
				62, 457, 159, 382, 509, 135, 319, 321, 511, 81, 309, 26, 12, 83, 226, 354, 415, 306, 315, 368, 134, 168,
				227, 152, 454, 250, 94, 403, 362, 381, 482, 521, 281, 212, 205, 74, 45, 266, 376, 178, 6, 420, 538, 498,
				292, 535, 189, 452, 344, 369, 467, 224, 264, 510, 313, 342, 261, 7, 391, 303, 461, 360, 96, 49, 29, 19,
				185, 44, 61, 449, 383, 493, 140, 285, 179, 295, 272, 366, 348, 394, 308, 169, 129, 300, 471, 364, 363,
				251, 439, 404, 141, 18, 182, 496, 16, 371, 248, 214, 346, 145, 123, 343, 5, 192, 222, 434, 548, 157, 28,
				385, 332, 143, 64, 102, 345, 273, 139, 60, 455, 448, 161, 55, 323, 271, 536, 232, 54, 71, 126, 437, 446,
				407, 478, 104, 464, 355, 108, 142, 432, 10, 278);
		String expected = "1-19, 22-31, 33-38, 40-46, 48-56, 58-83, 85-127, 129-137, 139-143, 145-148,"
				+ " 151-153, 155, 157-180, 182-187, 189-192, 194-200, 202-206, 208-239, 242-254, 256, "
				+ "258-281, 283-290, 292-295, 297-309, 311, 313-316, 318-323, 326-327, 329-330, 332-334,"
				+ " 336-366, 368-379, 381-398, 400-408, 411-423, 426-432, 434-444, 446-457, 460-473, 475-480,"
				+ " 482-498, 500-511, 513-515, 517-528, 530-550";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}

	/** Where the user provides a string of both positive and negative integers that are in random order,
	 *  without any non-digit characters or escape characters. 1 sets with disparate unique random integers in each, taken from
	 * the [1-550] range.
	 * 
	 * The test asserts that the provided input will be returned from the SummarizeCollection method
	 * as list of comma-delimited integers in ascending order, where sequential numbers are grouped
	 * together to form a range.
	 * 
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 *  range. The
	 * integers were not sorted.
	 */
	@Test
	void testSummarizeCollectionDisparateMultiRangePositiveNegative() {
		Summarizer demo = new Summarizer();
		Collection<Integer> input = Arrays.asList(63, 291, -93, 500, 7, -26, -77, 118, 76, 169, 192, -188, 237, 9, 117,
				310, 463, -59, 497, 133, -98, 484, 400, 135, 266, -24, -110, 91, -134, 470, 297, 141, 16, 5, -197, -162,
				324, 406, 453, -104, -152, 153, 180, 235, 432, -179, -105, 197, 272, 44, 283);
		String expected = "-197, -188, -179, -162, -152, -134, -110, -105--104, -98, -93, -77, -59,"
				+ " -26, -24, 5, 7, 9, 16, 44, 63, 76, 91, 117-118, 133, 135, 141, 153, 169, 180, 192,"
				+ " 197, 235, 237, 266, 272, 283, 291, 297, 310, 324, 400, 406, 432, 453, 463, 470," + " 484, 497, 500";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}

	/** Where the user provides a string of both positive and negative integers, where there is a sequence of
	 * numbers that change sign from negative to positive.
	 * 
	 * The test asserts that the provided input will be returned from the SummarizeCollection method
	 * as list of comma-delimited integers in ascending order, where sequential numbers are grouped
	 * together to form a range even across the zero boundary.
	 * {@link Summarizer.Demo#summarizeCollection(java.util.Collection)}.
	 */
	@Test
	void testSummarizeCollectionDiscontinuityTest() {
		Summarizer demo = new Summarizer();
		Collection<Integer> input = Arrays.asList(-3, -2, -1, 0, 1, 2, 3);
		String expected = "-3-3";
		String actual = demo.summarizeCollection(input);
		assertEquals(expected, actual);
	}
}