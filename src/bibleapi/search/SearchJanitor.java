package bibleapi.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import bibleapi.core.Bible;

import com.google.appengine.api.datastore.DatastoreNeedIndexException;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class SearchJanitor {

        private static final Logger log = Logger.getLogger(SearchJanitor.class.getName());

        public static final int MAXIMUM_NUMBER_OF_WORDS_TO_SEARCH = 5;

        public static final int MAX_NUMBER_OF_WORDS_TO_PUT_IN_INDEX = 200;

        public static List<Bible> searchBibleEntries(String queryString, Integer start, Integer limit) {

                Objectify ofy = ObjectifyService.begin();

                Query<Bible> query = ofy.query(Bible.class);

                Set<String> queryTokens = SearchJanitorUtils.getTokensForIndexingOrQuery(queryString, MAXIMUM_NUMBER_OF_WORDS_TO_SEARCH);

                List<String> parametersForSearch = new ArrayList<String>(queryTokens);

                for (String token : parametersForSearch) {
                        query.filter("fts", token);
                }
                query.limit(limit).offset(start);

                List<Bible> result = null;

                try {
                        result = new ArrayList<Bible>();
                        for (Bible bibleEntry : query) {
                                result.add(bibleEntry);
                        }

                } catch (DatastoreTimeoutException e) {
                        log.severe(e.getMessage());
                        log.severe("datastore timeout at: " + queryString);
                } catch (DatastoreNeedIndexException e) {
                        log.severe(e.getMessage());
                        log.severe("datastore need index exception at: " + queryString);
                }

                return result;

        }

        public static void updateFTSStuffForBibleEntry(Bible bibleEntry) {

                StringBuffer sb = new StringBuffer();

                sb.append(bibleEntry.getVerset());

                Set<String> new_ftsTokens = SearchJanitorUtils.getTokensForIndexingOrQuery(sb.toString(), MAX_NUMBER_OF_WORDS_TO_PUT_IN_INDEX);

                Set<String> ftsTokens = bibleEntry.getFts();

                ftsTokens.clear();

                for (String token : new_ftsTokens) {
                        ftsTokens.add(token);

                }
        }

}