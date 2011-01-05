package bibleapi.core;

import java.util.ArrayList;
import java.util.List;

public class Version {
	private String id;
	private String name;
	private String[] abbreviations;
	private final static List<Version> listeVersion = new ArrayList<Version>();
	private final static Version defaultVersion;

	private Version(String id, String name, String... abbreviations) {
		super();
		this.id = id.toLowerCase();
		this.name = name;
		this.abbreviations = abbreviations;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String[] getAbbreviations() {
		return abbreviations;
	}

	public static List<Version> getListeVersion() {
		return listeVersion;
	}

	public static Version getDefaultVersion() {
		return defaultVersion;
	}
	
	@Override
	public String toString() {
		return getId();
	}

	//TODO ajouter toutes les versions possibles d'écriture des bibles !
	static {
		defaultVersion = new Version("tob", "Traduction œcuménique de la Bible","tob","traduction œcuménique de la bible", "traduction oecumenique de la bible", "traduction oecumenique", "œcumenique", "traduction oecumenique", "oecumenique", "oecum"); 
			 //Ajouter toutes les formes
		listeVersion.add(defaultVersion);
		listeVersion.add(new Version("bj", "Bible de Jérusalem", "biblejerusalem", "bj", "bible jerusalem"));
	}

	/**
	 * @param searchVersion
	 * @return
	 */
	public static Version getVersion(String searchVersion) {
		for (Version v : listeVersion) {
			if (v.getId().equals(searchVersion)) {
				return v;
			}
		}
		return defaultVersion;
	}
}
