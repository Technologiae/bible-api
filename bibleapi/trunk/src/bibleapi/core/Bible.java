package bibleapi.core;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

import com.google.appengine.api.datastore.Text;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Searchable
public class Bible {
	/**
	 * Référence biblique d'un verset
	 * Version:Livre,Chapitre,Verset
	 */
	@PrimaryKey
	@Persistent
	@SearchableId
	private String reference;
	
	/**
	 * texte du verset
	 */
	@Persistent
	private Text verset;
	
	/**
	 * Version de la bible
	 */
	@Persistent
	@SearchableProperty
	private String version;
	
	/**
	 * Livre de la bible
	 */
	@Persistent
	@SearchableProperty
	private String book;
	
	/**
	 * Chapitre du verset
	 */
	@Persistent
	@SearchableProperty
	private Integer chapter;
	
	/**
	 * Numéro du verset
	 */
	@Persistent
	@SearchableProperty
	private Integer verse;
	
	public Bible() {
	}

	public Bible(String reference, String verset) {
		setReference(reference);
		setVerset(verset);
		Reference ref = new Reference(reference);
		setVersion(ref.getVersion());
		setBook(ref.getBook());
		setChapter(ref.getChapter());
		setVerse(ref.getVerset());
	}
	
	public Bible(Reference reference, String verset) {
		setReference(reference.toString());
		setVerset(verset);
		setVersion(reference.getVersion());
		setBook(reference.getBook());
		setChapter(reference.getChapter());
		setVerse(reference.getVerset());
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReference() {
		return reference;
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

	public Integer getVerse() {
		return verse;
	}

	public void setVerse(Integer verse) {
		this.verse = verse;
	}

	public void setVerset(Text verset) {
		this.verset = verset;
	}

	public void setVerset(String verset) {
		this.verset = new Text(verset);
	}

	@SearchableProperty
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
