package bibleapi.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Book {
	private int pos;
	private String id;
	private String realName;
	private String name;
	private String[] abbreviations;
	
	private Book(int pos, String name, String realName, String id, String... strings) {
		this.pos = pos;
		this.id = id;
		this.realName = realName;
		this.name = name;
		abbreviations = strings;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getAbbreviations() {
		return abbreviations;
	}

	public void setAbbreviations(String[] abbreviations) {
		this.abbreviations = abbreviations;
	}

	private final static List<Book> listeBook = new ArrayList<Book>(75);
	
	public static List<Book> getListeBook() {
		return listeBook;
	}
	
	@Override
	public String toString() {
		return getId();
	}

	//TODO ajouter toutes les versions possibles d'écriture des livres !
	static {
		listeBook.add(new Book(1,"Genèse","Genèse","gn","genese","gen","ge"));
		listeBook.add(new Book(2,"Exode","Exode","ex","exode","ex"));
		listeBook.add(new Book(3,"Lévitique","Lévitique","lv","levetique","lév","lev","lv"));
		listeBook.add(new Book(4,"Nombres","Nombres","nb","nombres","nom","nb"));
		listeBook.add(new Book(5,"Deutéronome","Deutéronome","dt","deuteronome","deut","ddt"));
		listeBook.add(new Book(6,"Josusé","Josusé","js","josusé","josuse","jos","js"));
		listeBook.add(new Book(7,"Juges","Livre des Juges","jg","livre des juges","juges","jug","jg"));
		listeBook.add(new Book(8,"Ruth","Livre de Ruth","rt","livre de ruth","ruth","ru","rt"));
		listeBook.add(new Book(9,"1 Samuel","Premier livre de Samuel","1s","premier livre de samuel","1 samuel","samuel 1","1samuel","samuel1","1 s","1s","s1","s 1"));
		listeBook.add(new Book(10,"2 Samuel","Deuxième livre de Samuel","2s","deuxieme livre de samuel","2 samuel","samuel 2","2samuel","samuel2","2 s","2s","s2","s 2"));
		listeBook.add(new Book(11,"1 Rois","Premier livre des Rois","1r","premier livre des rois","1 rois","rois 1","1rois","rois1","1r","r1"));
		listeBook.add(new Book(12,"2 Rois","Deuxième livre des Rois","2r","deuxieme livre des rois","2 rois","rois 2","2rois","rois2","2r","r2"));
		listeBook.add(new Book(13,"1 Chroniques","Premier livre des Chroniques","1ch","premier livre des chroniques","chroniques 1","1 chroniques","chronique 1","1 chronique","1ch","1 ch","ch1","ch 1"));
		listeBook.add(new Book(14,"2 Chroniques","Deuxième livre des Chroniques","2ch","deuxieme livre des chroniques","chroniques 2","2 chroniques","chronique 2","2 chronique","2ch","2 ch","ch2","ch 2"));
		listeBook.add(new Book(15,"Esdras","Livre d’Esdras","esd","esdras","esd"));
		listeBook.add(new Book(16,"Néhémie","Livre de Néhémie","ne","nehemie","ne"));
		listeBook.add(new Book(17,"Esther","Livre d’Esther","est","livre d'esther","esther","est"));
		listeBook.add(new Book(18,"Job","Job","jb","job","jb"));
		listeBook.add(new Book(19,"Psaumes","Psaumes","ps","psaumes","psaume","livre des psaumes","ps"));
		listeBook.add(new Book(19,"Proverbes","Proverbes","pr","proverbes","proverbe","pr"));
		listeBook.add(new Book(20,"Ecclésiaste","Ecclésiaste","ec","ecclesiaste","ecc","ec"));
		listeBook.add(new Book(21,"Cantique des cantiques","Cantique des cantiques","ct","cantique des cantiques","cantique des cantique","cantique","cantiques","can"));
		listeBook.add(new Book(22,"Ésaïe","Livre d’Ésaïe","es","livre d'esaie","esaie","es"));
		listeBook.add(new Book(23,"Jérémie","Livre de Jérémie","jr","livre de jeremie","jeremie","je"));
		listeBook.add(new Book(24,"Lamentations","Livre des Lamentations","la","livre des lamentations","livre des lamentation","lamentations","lamentation","lam","lam"));
		listeBook.add(new Book(25,"Ézéchiel","Livre d’Ézéchiel","ez","livre d'ezechiel","ezechiel","ez"));
		listeBook.add(new Book(26,"Daniel","Livre de Daniel","da","livre de daniel","daniel","dn"));
		listeBook.add(new Book(27,"Osée","Osée","os","osee","os"));
		listeBook.add(new Book(28,"Joël","Joël","jl","joel","joe","jl"));
		listeBook.add(new Book(29,"Amos","Amos","am","amos","am"));
		listeBook.add(new Book(30,"Abdias","Abdias","ab","abdias","abd","ab"));
		listeBook.add(new Book(31,"Jon","Jon","jon","jonas","jon"));
		listeBook.add(new Book(32,"Michée","Michée","mi","michee","mich","mi"));
		listeBook.add(new Book(33,"Nahum","Nahum","na","nahum","nah","na"));
		listeBook.add(new Book(34,"Habakuk","Habakuk","ha","habakuk","hab","ha"));
		listeBook.add(new Book(35,"Sophonie","Sophonie","so","sophonie","so"));
		listeBook.add(new Book(36,"Aggée","Aggée","ag","aggée","aggee","agg","ag"));
		listeBook.add(new Book(37,"Zacharie","Zacharie","za","zacharie","za"));
		listeBook.add(new Book(38,"malachie","Malachie","ma","malachie","ma"));
		listeBook.add(new Book(39,"1 Macchabées","Premier livre des Macchabées","1m","premier livre des macchabees","macchabees1","macchabees 1","1macchabees","1 m","1m","1m","m1","m 1"));
		listeBook.add(new Book(40,"2 Macchabées","Deuxième livre des Macchabées","2m","deuxieme livre des macchabees","macchabees2","macchabees 2","2macchabees","2 m","2m","2m","m2","m 2"));
		listeBook.add(new Book(41,"Tobie","Tobie","tb","tobie","tob","tb"));
		listeBook.add(new Book(42,"Judith","Judith","jdt","judith","jdt"));
		listeBook.add(new Book(43,"Sagesse","Livre de la Sagesse","sg","livre de la sagesse","sagesse","sa","sg"));
		listeBook.add(new Book(44,"Ecclésiastique","Ecclésiastique ou Siracide","si","ecclesiastique ou siracide","siracide","ecclesiastique","si"));
		listeBook.add(new Book(45,"Matthieu","Évangile selon St Matthieu","mt","evangile selon st matthieu","evangile selon matthieu","evangile de matthieu","matthieu","matt","mat","mt"));
		listeBook.add(new Book(46,"Marc","Évangile selon St Marc","mc","evangile selon saint marc","evangile selon st marc","evangile selon marc","evangile de marc","evangile de saint marc","evangile de marc","marc","mar","mc"));
		listeBook.add(new Book(47,"Luc","Évangile selon St Luc","lc","evangile selon st luc","evangile selon luc","evangile de luc","luc","lc"));
		listeBook.add(new Book(48,"Jean","Évangile selon St Jean","jn","evangile selon st jean","evangile selon jean","evangile de jean","jean","jn"));
		listeBook.add(new Book(49,"Actes","Actes des Apôtres","ac","actes des apotres","actes","acte","act","ac"));
		listeBook.add(new Book(50,"Romains","Épître aux Romains","rm","epitre aux romains","epitres aux romains","romains","rom","ro","rm"));
		listeBook.add(new Book(51,"1 Corinthiens","Première épître aux Corinthiens","1co","premiere epitre aux corinthiens","1 corinthiens","1corinthiens","1corinthien","1 corinthien","corinthiens1","corinthien1","corinthiens 1","corinthien 1","1 corinthiens","1co","1 co","1 cor","co1","cor1","co 1","cor 1"));
		listeBook.add(new Book(52,"2 Corinthiens","Deuxième épître aux Corinthiens","2co","deuxieme epitre aux corinthiens","2 corinthiens","2corinthiens","2corinthien","2 corinthien","corinthiens2","corinthien2","corinthiens 2","corinthien 2","2 corinthiens","2co","2 co","2 cor","co2","cor2","co 2","cor 2"));
		listeBook.add(new Book(53,"Galates","Épître aux Galates", "ga","epitre aux galates","galates","gal","ga"));
		listeBook.add(new Book(54,"Éphésiens","Épître aux Éphésiens","ep","epitre aux ephesiens","ephesiens","eph","ep"));
		listeBook.add(new Book(55,"Philippiens","Épître aux Philippiens","ph","epitre aux philippiens","philippiens","philippien","phil","ph"));
		listeBook.add(new Book(56,"Colossiens","Épître aux Colossiens","co","epitre aux colossiens","colossiens","col","cl","co"));
		listeBook.add(new Book(57,"1 Thessaloniciens","Première épître aux Thessaloniciens","1th","premiere epitre aux thessaloniciens","1ere epitre aux thessaloniciens","1 thessaloniciens","1thessaloniciens","1 thessalonicien","1thessalonicien","1 thess","1thess","thess 1","thess1","1th","1 th","1 th","th1","th 1"));
		listeBook.add(new Book(58,"2 Thessaloniciens","Deuxième épître aux Thessaloniciens","2th","deuxieme epitre aux thessaloniciens","2ere epitre aux thessaloniciens","2 thessaloniciens","2thessaloniciens","2 thessalonicien","2thessalonicien","2 thess","2thess","thess 2","thess2","2th","2 th","2 th","th2","th 2"));
		listeBook.add(new Book(59,"1 Timothée","Première épître à Timothée","1ti","premiere epitre a timothee","1ere epitre a timothee","1 timothee","1timothee","1ti","1 ti","1 tm","ti1","ti 1","tm1","tm 1"));
		listeBook.add(new Book(60,"2 Timothée","Deuxième épître à Timothée","2ti","deuxieme epitre a timothee","2ere epitre a timothee","2 timothee","2timothee","2ti","2 ti","2 tm","ti2","ti 2","tm2","tm 2"));
		listeBook.add(new Book(61,"Tite","Épître à Tite","tt","epitre a tite","tite","tit","tt"));
		listeBook.add(new Book(62,"Philémon","Épître à Philémon","phm","epitre a philemon","philemon","phm"));
		listeBook.add(new Book(63,"Hébreux","Épître aux Hébreux","he","epitre aux hebreux","hebreux","he","hébreux"));
		listeBook.add(new Book(64,"Jacques","Épître de Jacques","jc","epitre de jacques","jacques","jac","jc"));
		listeBook.add(new Book(65,"1 Pierre","Première épître de Pierre","1p","premiere epitre de pierre","1ere epitre de pierre","1 pierre","1pierre","1 pi","1pi","p1","p 1","pi1","pi 1"));
		listeBook.add(new Book(66,"2 Pierre","Deuxième épître de Pierre","2p","deuxième epitre de pierre","2eme epitre de pierre","2 pierre","2pierre","2 pi","2pi","p2","p 2","pi2","pi 2"));
		listeBook.add(new Book(67,"1 Jean","Première épître de Jean","1jn","1 jean","premiere epitre de jean","1jn","1 jn","jn1","1 jean","1jn"));
		listeBook.add(new Book(68,"2 Jean","Deuxième épître de Jean","2jn","2 jean","deuxieme epitre de jean","2jn","2 jn","jn2","2 jean","2jn"));
		listeBook.add(new Book(69,"3 Jean","Troisième épître de Jean","3jn","3 jean","troisieme epitre de jean","3jn","3 jn","jn3","3 jean","3jn"));
		listeBook.add(new Book(70,"Jude","Épître de Jude","jd","epitre de Jude","jude","jud","jd"));
		listeBook.add(new Book(71,"Apocalypse","Apocalypse","ap","apocalypse","ap"));
		Collections.sort(listeBook, new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	/**
	 * @param searchBook
	 * @return
	 */
	public static Book getBook(String searchBook) {
		for (Book b : listeBook) {
			if (b.getId().equals(searchBook)) {
				return b;
			}
		}
		return null;
	}
}
