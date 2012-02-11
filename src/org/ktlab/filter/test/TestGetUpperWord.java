/**
 * 
 */
package org.ktlab.filter.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ktlab.filter.RawParser;

/**
 * @author hugo
 *
 */
public class TestGetUpperWord {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		RawParser rp = new RawParser();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		RawParser rp = null;
	}

	/**
	 * Test method for {@link org.ktlab.filter.RawParser#filter(java.lang.String, int, int)}.
	 */
	@Test
	public final void testFilter() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.ktlab.filter.RawParser#parser(java.util.List, int, int)}.
	 */
	@Test
	public final void testParser() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.ktlab.filter.RawParser#getUpperWord(java.util.List, int)}.
	 */
	@Test
	public final void testGetUpperWord() {
		
		assertEquals("Result", "", "");
	}
}
