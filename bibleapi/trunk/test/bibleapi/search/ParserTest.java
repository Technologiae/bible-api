/**
 * 
 */
package bibleapi.search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import bibleapi.core.Reference;


/**
 * @author desaintsteban.p
 *
 */
public class ParserTest {

	@Test
	public void testParserSimpleTob() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("tob:mc,4,39");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(1));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(39));
	}
	
	@Test
	public void testParserSimpleWithSpace() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse(" tob  mc  4   39  ");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(1));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(39));
	}
	
	@Test
	public void testParserSimpleBj() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("bj:mc,4,39");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(1));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("bj"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(39));
	}
	
	@Test
	public void testParserSimple1M() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("tob:1m,1,1");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(1));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("1m"));
		assertThat(ref.getChapter(), is(1));
		assertThat(ref.getVerset(), is(1));
	}
	
	@Test
	public void testParserSimpleChapter() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("tob:mc,c 1,1");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(1));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(1));
		assertThat(ref.getVerset(), is(1));
	}
	
	@Test
	public void testParserSimpleVerset() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("tob:mc,1,v1");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(1));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(1));
		assertThat(ref.getVerset(), is(1));
	}
	
	@Test
	public void testParserMultiple() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("tob:mc,4,39;bj:jn,3,2");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(2));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(39));
		ref = liste.get(1);
		assertThat(ref.getVersion(), is("bj"));
		assertThat(ref.getBook(), is("jn"));
		assertThat(ref.getChapter(), is(3));
		assertThat(ref.getVerset(), is(2));
	}
	
	@Test
	public void testParserMultipleVerset() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("tob:mc,4,39 40");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(2));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(39));
		ref = liste.get(1);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(40));
	}
	
	@Test
	public void testParserSuiteVerset() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("tob:mc,4,39 - 42");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(4));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(39));
		ref = liste.get(1);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(40));
		ref = liste.get(2);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(41));
		ref = liste.get(3);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(42));
	}
	
	@Test
	public void testParserVersionText() {
		Parser parser = new Parser();
		List<Reference> liste = parser.parse("Mc Chapitre 4 verset 39 à 42");
		assertThat(liste, notNullValue());
		assertThat(liste.size(), is(4));
		Reference ref = liste.get(0);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(39));
		ref = liste.get(1);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(40));
		ref = liste.get(2);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(41));
		ref = liste.get(3);
		assertThat(ref.getVersion(), is("tob"));
		assertThat(ref.getBook(), is("mc"));
		assertThat(ref.getChapter(), is(4));
		assertThat(ref.getVerset(), is(42));
	}
}
