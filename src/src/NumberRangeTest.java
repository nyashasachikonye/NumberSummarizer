package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberRangeTest {

	@Test
	void testNumberRange() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStart() {
		fail("Not yet implemented");
	}

	@Test
	void testSetStart() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEnd() {
		fail("Not yet implemented");
	}

	@Test
	void testSetEnd() {
		fail("Not yet implemented");
	}

	@Test
	void testJoin() {
		fail("Not yet implemented");
	}

	@Test
	void testIsConsecutive() {
		fail("Not yet implemented");
	}

	@Test
	void testNext() {
		fail("Not yet implemented");
	}

	@Test
	void testPrevious() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	@Test
	void testIsRange() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		NumberRange num_range = new NumberRange(Integer.valueOf(3),Integer.valueOf(6));
		String expected = "3-6";
		String actual = num_range.toString();
		assertEquals(expected, actual);
	}

}
