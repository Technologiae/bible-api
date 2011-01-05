package bibleapi.search;

import java.util.ArrayList;
import java.util.List;

import bibleapi.core.Book;
import bibleapi.core.Reference;
import bibleapi.core.Version;
import bibleapi.tools.StringUtils;

public class Parser {
	private List<Reference> listeReference;
	
	private Book currentBook;
	private Version currentVersion;
	private Integer currentPosition;
	private Integer currentChapter;
	private String currentSearch;
	
	public List<Reference> parse(String search) {
		currentPosition = 0;
		currentSearch = StringUtils.toLowerCaseWithoutAccent(search);
		currentVersion = Version.getDefaultVersion();
		listeReference = new ArrayList<Reference>();
		int length = search.length();
		for (;currentPosition < length;){
			int firstPosition = currentPosition;
			if (trimSpace())
				break;
			searchVersion();
			if (trimSpace())
				break;
			searchBook();
			if (currentBook == null)
				break; //Erreur pas de livre
			if (trimSpace())
				break;
			searchChapter();
			if (currentChapter == null)
				break; //Erreur pas de chapitre trouvé
			if (trimSpace())
				break;
			searchVerset();
			if (currentPosition >= currentSearch.length() || firstPosition == currentPosition)
				break;
		}
		return listeReference;
	}
	private String[] listeChapter = {"chapitre","chapter","chp","ch","c.","c"};
	private void searchChapter() {
		boolean foundChapterMarker = false;
		for (String chString : listeChapter) {
			if (currentSearch.startsWith(chString, currentPosition)) {
				currentPosition += chString.length();
				foundChapterMarker = true;
			}
		}
		if (trimSpace())
			return;
		if ((!foundChapterMarker && currentChapter == null) || foundChapterMarker) {
			if (Character.isDigit(currentSearch.charAt(currentPosition))) {
				String chapterStr = currentSearch.substring(currentPosition, searchEndInteger(currentPosition));
				currentChapter = Integer.decode(chapterStr);
				currentPosition += chapterStr.length();
			}
		}
	}
	
	private String[] listeVerset = {"versets","verset","verse","v.","v"};
	private void searchVerset() {
		for (String chString : listeVerset) {
			if (currentSearch.startsWith(chString, currentPosition)) {
				currentPosition += chString.length();
				break;
			}
		}
		if (trimSpace())
			return;
		if (Character.isDigit(currentSearch.charAt(currentPosition))) {
			String versetStr = currentSearch.substring(currentPosition, searchEndInteger(currentPosition));
			Integer verset = Integer.decode(versetStr);
			Integer versetMax = null;
			currentPosition += versetStr.length();
			if (!trimSpaceWithoutMinus()) {
				if (currentSearch.charAt(currentPosition) == '-' ||
					currentSearch.startsWith("à", currentPosition) ||
					currentSearch.charAt(currentPosition) == 'a') {
					currentPosition += 1;
					if (!trimSpace()) {
						String versetMaxStr = currentSearch.substring(currentPosition, searchEndInteger(currentPosition));
						versetMax = Integer.decode(versetMaxStr);
						currentPosition += versetMaxStr.length();
						if (versetMax < verset) {
							versetMax = null;
						}
					}
				}
			}				
			if (currentBook != null && currentChapter != null) { // Si on a bien trouvé le chapitre et le livre
				if (versetMax == null) {
					versetMax = verset;
				}
				for (;verset <= versetMax;verset++) {
					Reference ref = new Reference(currentVersion.getId(), currentBook.getId(), currentChapter, verset);
					listeReference.add(ref);
				}
			}
		}
	}

	private int searchEndInteger(Integer position) {
		while(position < currentSearch.length() && Character.isDigit(currentSearch.charAt(position))) {
			++position;
		}
		return position;
	}

	private void searchBook() {
		for (Book book : Book.getListeBook()) {
//			if (book != currentBook) {
				for (String bookString : book.getAbbreviations()) {
					if (currentSearch.startsWith(bookString, currentPosition)) {
						currentPosition += bookString.length();
						currentBook = book;
						if (currentChapter != null)
							currentChapter = null;
						return;
					}
				}
//			}
		}
	}

	private void searchVersion() {
		for (Version version : Version.getListeVersion()) {
//			if (version != currentVersion) {
				for (String versionString : version.getAbbreviations()) {
					if (currentSearch.startsWith(versionString, currentPosition)) {
						currentPosition += versionString.length();
						currentVersion = version;
						if (currentBook != null)
							currentBook = null;
						return;
					}
				}
//			}
		}
	}

	private boolean trimSpace() {
		while(currentPosition < currentSearch.length() && !Character.isLetterOrDigit(currentSearch.charAt(currentPosition))) {
			++currentPosition;
		}
		return currentPosition >= currentSearch.length();
	}
	
	private boolean trimSpaceWithoutMinus() {
		while(currentPosition < currentSearch.length() && currentSearch.charAt(currentPosition) != '-' && !Character.isLetterOrDigit(currentSearch.charAt(currentPosition))) {
			++currentPosition;
		}
		return currentPosition >= currentSearch.length();
	}
	
	
}
