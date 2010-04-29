package bibleapi.tools;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.compass.core.Compass;
import org.compass.core.config.CompassConfiguration;
import org.compass.core.config.CompassEnvironment;
import org.compass.gps.CompassGps;
import org.compass.gps.device.jdo.JdoGpsDevice;
import org.compass.gps.impl.SingleCompassGps;

/** 
 * Gestion de persistance pour le DataStore google
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    
    private static final Compass compass;
    private static final CompassGps compassGps;
    
    static {
    	compass = new CompassConfiguration().setConnection("gae://index")
    				.setSetting(CompassEnvironment.ExecutorManager.EXECUTOR_MANAGER_TYPE, "disabled")
    				.addScan("bibleapi.core")
    				.buildCompass();
    	compassGps = new SingleCompassGps(compass);
    	compassGps.addGpsDevice(new JdoGpsDevice("appengine", pmfInstance));
    	compassGps.start();
    	
    	compassGps.index();
    }
    
    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
    
    public static Compass getCompass() {
    	return compass;
    }
}