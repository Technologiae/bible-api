package bibleapi.search;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;

public class SearchJanitorUtils {
        
        
        private static final Logger log = Logger.getLogger(SearchJanitorUtils.class.getName());
        
        /** From StopAnalyzer Lucene 2.9.1 */
        public final static String[] stopWords = new String[]{
            "a", "afin", "ai", "ainsi", "après", "attendu", "au", "aujourd", "auquel", "aussi",
            "autre", "autres", "aux", "auxquelles", "auxquels", "avait", "avant", "avec", "avoir",
            "c", "car", "ce", "ceci", "cela", "celle", "celles", "celui", "cependant", "certain",
            "certaine", "certaines", "certains", "ces", "cet", "cette", "ceux", "chez", "ci",
            "combien", "comme", "comment", "concernant", "contre", "d", "dans", "de", "debout",
            "dedans", "dehors", "delà", "depuis", "derrière", "des", "désormais", "desquelles",
            "desquels", "dessous", "dessus", "devant", "devers", "devra", "divers", "diverse",
            "diverses", "doit", "donc", "dont", "du", "duquel", "durant", "dès", "elle", "elles",
            "en", "entre", "environ", "est", "et", "etc", "etre", "eu", "eux", "excepté", "hormis",
            "hors", "hélas", "hui", "il", "ils", "j", "je", "jusqu", "jusque", "l", "la", "laquelle",
            "le", "lequel", "les", "lesquelles", "lesquels", "leur", "leurs", "lorsque", "lui", "là",
            "ma", "mais", "malgré", "me", "merci", "mes", "mien", "mienne", "miennes", "miens", "moi",
            "moins", "mon", "moyennant", "même", "mêmes", "n", "ne", "ni", "non", "nos", "notre",
            "nous", "néanmoins", "nôtre", "nôtres", "on", "ont", "ou", "outre", "où", "par", "parmi",
            "partant", "pas", "passé", "pendant", "plein", "plus", "plusieurs", "pour", "pourquoi",
            "proche", "près", "puisque", "qu", "quand", "que", "quel", "quelle", "quelles", "quels",
            "qui", "quoi", "quoique", "revoici", "revoilà", "s", "sa", "sans", "sauf", "se", "selon",
            "seront", "ses", "si", "sien", "sienne", "siennes", "siens", "sinon", "soi", "soit",
            "son", "sont", "sous", "suivant", "sur", "ta", "te", "tes", "tien", "tienne", "tiennes",
            "tiens", "toi", "ton", "tous", "tout", "toute", "toutes", "tu", "un", "une", "va", "vers",
            "voici", "voilà", "vos", "votre", "vous", "vu", "vôtre", "vô´tres", "y", "à", "ça", "ès",
            "été", "être", "ô"
          };
        
        /**
         * Uses french stemming (snowball + lucene) + stopwords for getting the words.
         * 
         * @param index
         * @return
         */
        public static Set<String> getTokensForIndexingOrQuery(
                        String index_raw,
                        int maximumNumberOfTokensToReturn) {
                String indexCleanedOfHTMLTags = index_raw.replaceAll("\\<.*?>"," ");
                Set<String> returnSet = new HashSet<String>();
                try {
                        Analyzer analyzer =  new SnowballAnalyzer(
                                        org.apache.lucene.util.Version.LUCENE_CURRENT,
                                        "French",
                                        stopWords);
                        TokenStream tokenStream = analyzer.tokenStream(
                                        "content", 
                                        new StringReader(indexCleanedOfHTMLTags));
                        Token token = new Token();
                while (((token = tokenStream.next()) != null)
                                && (returnSet.size() < maximumNumberOfTokensToReturn)) {
                                        returnSet.add(token.term());
                        }
                } catch (IOException e) {
                        log.severe(e.getMessage());
                }
                return returnSet;
        }
        
        
        
        
}