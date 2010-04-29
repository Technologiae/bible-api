package bibleapi.core;

import junit.framework.TestCase;

public class ReferenceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testParse() {
		Reference ref = new Reference();
		ref.parse("TOB:Mc,4,39");
		assertEquals("TOB",ref.getVersion());
		assertEquals("Mc",ref.getBook());
		assertEquals(new Integer(4),ref.getChapter());
		assertEquals(new Integer(39),ref.getVerset());
	}

	public void testToString() {
		Reference ref = new Reference("TOB", "Mc", 4, 39);
		assertEquals("TOB",ref.getVersion());
		assertEquals("Mc",ref.getBook());
		assertEquals(new Integer(4),ref.getChapter());
		assertEquals(new Integer(39),ref.getVerset());
		assertEquals("TOB:Mc,4,39", ref.toString());
	}

}
