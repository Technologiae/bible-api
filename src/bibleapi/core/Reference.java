package bibleapi.core;



public class Reference {
	public final static String VERSION_SEPARATOR = ":";
	public final static String BOOK_SEPARATOR = ",";
	public final static String CHAPTER_SEPARATOR = ",";
	/**
	 * Version de la bible
	 */
	private String version = "";
	
	/**
	 * Livre de la bible
	 */
	private String book = "";
	
	/**
	 * Chapitre du verset
	 */
	private Integer chapter = null;
	
	/**
	 * Numéro du verset
	 */
	private Integer verset = null;
	
	public Reference() {
	}
	
	public Reference(String reference) {
		parse(reference);
	}

	/**
	 * Crée une nouvelle référence de bible
	 * @param version Version of the Bible (Bi
	 * @param book
	 * @param chapter
	 * @param verset
	 */
	public Reference(String version, String book, Integer chapter, Integer verset) {
		this.version = version;
		this.book = book;
		this.chapter = chapter;
		this.verset = verset;
	}
	
	/**
	 * Parse la chaine de caractère Référence Doit être au bon format
	 * @param reference
	 */
	public void parse(String reference) {
		int begin = 0;
		int end = 0;
		if (reference.indexOf(VERSION_SEPARATOR) > 0) {
			end = reference.indexOf(VERSION_SEPARATOR);
			version = reference.substring(begin, end);
			begin = end + 1;
		}
		if (reference.indexOf(BOOK_SEPARATOR, begin) > 0) {
			end = reference.indexOf(BOOK_SEPARATOR);
			book = reference.substring(begin, end);
			begin = end + 1;
		}
		if (reference.indexOf(CHAPTER_SEPARATOR, begin) > 0) {
			end = reference.indexOf(CHAPTER_SEPARATOR, begin);
			String chp = reference.substring(begin, end);
			chapter = Integer.decode(chp);
			begin = end + 1;
		}
		if (reference.length() > begin) {
			String v = reference.substring(begin);
			verset = Integer.decode(v);
		}
	}
	
	public String toString() {
		return version + VERSION_SEPARATOR + book + BOOK_SEPARATOR + chapter + CHAPTER_SEPARATOR + verset;
	}
	
	/**
	 * Retourne la référence textuelle
	 * @return
	 */
	public String getReference() {
		return toString();
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

	public Integer getVerset() {
		return verset;
	}

	public void setVerset(Integer verse) {
		this.verset = verse;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}
}
