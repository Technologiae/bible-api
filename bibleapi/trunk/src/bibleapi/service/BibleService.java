package bibleapi.service;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import bibleapi.core.Bible;
import bibleapi.core.Reference;
import bibleapi.tools.PersistenceManagerFilter;

public class BibleService {
	/**
	 * Ajoute un verset dans la base de donnée
	 * @param reference Référence du verset
	 * @param verset Texte du verset
	 * @return
	 */
	public Bible addVerset(Reference reference, String verset) {
		PersistenceManager pm = PersistenceManagerFilter.getPersistenceManager();
		Bible bible = new Bible(reference, verset);
		pm.makePersistent(bible);
		return bible;
	}
	
	public Bible addVerset(String reference, String verset) {
		return addVerset(new Reference(reference), verset);
	}
	
	/**
	 * Recherche un verset dans la base de donnée
	 * @param reference Référence du verset
	 * @return L'objet bible ou null si le verset n'est pas trouvé
	 */
	public Bible getVerset(String reference) {
		PersistenceManager pm = PersistenceManagerFilter.getPersistenceManager();
		try {
			return pm.getObjectById(Bible.class, reference);
		}
		catch (JDOObjectNotFoundException e) {
			return null;
		}
	}
}
