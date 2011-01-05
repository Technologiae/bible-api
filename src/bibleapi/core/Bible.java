package bibleapi.core;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;

import bibleapi.search.SearchJanitor;

import com.google.appengine.api.datastore.Text;

public class Bible {

	/**
	 * Référence biblique d'un verset Version:Livre,Chapitre,Verset
	 */
	@Id
	private String reference;

	/**
	 * texte du verset
	 */
	private Text verset;

	/**
	 * Version de la bible
	 */
	private String version;

	/**
	 * Livre de la bible
	 */
	private String book;

	/**
	 * Chapitre du verset
	 */
	private Integer chapter;

	/**
	 * Numéro du verset
	 */
	private Integer versetNumber;

	/**
	 * For the search
	 */
	private Set<String> fts = new HashSet<String>();

	public Bible() {
	}

	public Bible(String reference, String verset) {
		setReference(reference);
		setVerset(verset);
		Reference ref = new Reference(reference);
		setVersion(ref.getVersion());
		setBook(ref.getBook());
		setChapter(ref.getChapter());
		setVersetNumber(ref.getVerset());
	}

	public Bible(Reference reference, String verset) {
		setReference(reference.toString());
		setVerset(verset);
		setVersion(reference.getVersion());
		setBook(reference.getBook());
		setChapter(reference.getChapter());
		setVersetNumber(reference.getVerset());
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}
	
	/**
	 * Retourne l'object reference
	 * @return
	 */
	public Reference toReference() {
		return new Reference(version, book, chapter, versetNumber);
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public Integer getChapter() {
		return chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}

	public Integer getVersetNumber() {
		return versetNumber;
	}

	public void setVersetNumber(Integer verse) {
		this.versetNumber = verse;
	}

	public void setVerset(Text verset) {
		this.verset = verset;
	}

	public void setVerset(String verset) {
		this.verset = new Text(verset);
		SearchJanitor.updateFTSStuffForBibleEntry(this);
	}

	public void setFts(Set<String> fts) {
		this.fts = fts;
	}

	public Set<String> getFts() {
		return fts;
	}

	public String getVerset() {
		return verset.getValue();
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}
}
