package bibleapi.tools;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PersistenceManagerFilter implements Filter {

    private static ThreadLocal<PersistenceManager> currentManager = new ThreadLocal<PersistenceManager>();

	public static PersistenceManager getPersistenceManager() {
        return currentManager.get();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		PersistenceManager pm = null;

		try {
			pm = PMF.get().getPersistenceManager();
			currentManager.set(pm);
			chain.doFilter(req, resp);
		} finally {
			if(pm!=null && !pm.isClosed()){
				pm.flush();
				pm.close();
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
