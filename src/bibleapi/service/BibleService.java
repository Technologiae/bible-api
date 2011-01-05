package bibleapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bibleapi.core.Bible;
import bibleapi.core.Reference;
import bibleapi.search.SearchJanitor;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class BibleService {
	
	static {
		ObjectifyService.register(Bible.class);
	}
	/**
	 * Ajoute un verset dans la base de donnée
	 * @param reference Référence du verset
	 * @param verset Texte du verset
	 * @return
	 */
	public Bible addVerset(Reference reference, String verset) {
		Objectify ofy = ObjectifyService.begin();
		Bible bible = new Bible(reference, verset);
		ofy.put(bible);
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
		Objectify ofy = ObjectifyService.begin();
		return ofy.get(Bible.class, reference);
	}
	
	/**
	 * Recherche un verset dans la base de donnée
	 * @param reference Référence du verset
	 * @return L'objet bible ou null si le verset n'est pas trouvé
	 */
	public Map<Key<Bible>, Bible> getVerset(List<Reference> references) {
		Objectify ofy = ObjectifyService.begin();
		List<Key<Bible>> listeReferences = new ArrayList<Key<Bible>>(references.size());
		for (Reference reference : references) {
			listeReferences.add(new Key<Bible>(Bible.class, reference.toString()));
		}
		return ofy.get(listeReferences);
	}
	
	/**
	 * Lance une recherche
	 * @param search La chaine de caractère de la recherche
	 * @param itemsByPages 
	 * @param page 
	 * @return
	 */
	public List<Bible> search(String search, Integer page, Integer itemsByPages) {
		Integer start = page * itemsByPages;
		return SearchJanitor.searchBibleEntries(search, start, itemsByPages);
	}
	
	/**
	 * Retourne une liste d'objet
	 * Utilisé pour supprimer la table
	 * @param limit
	 * @param offset
	 * @return
	 */
	public Iterable<Key<Bible>> listFirstBible(int limit, int offset) {
		Objectify ofy = ObjectifyService.begin();
		Query<Bible> query = ofy.query(Bible.class).offset(offset).limit(limit);
		QueryResultIterable<Key<Bible>> keys = query.fetchKeys();
		return keys;
		
	}
}
