package bibleapi.tools;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/** 
 * Gestion de persistance pour le DataStore google
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    
    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}