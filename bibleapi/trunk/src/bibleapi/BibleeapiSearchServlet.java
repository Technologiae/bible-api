package bibleapi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import org.compass.core.CompassHit;
import org.compass.core.CompassHits;
import org.compass.core.CompassSearchSession;
import org.compass.core.Resource;

import bibleapi.service.BibleService;
import bibleapi.tools.PMF;

@SuppressWarnings("serial")
public class BibleeapiSearchServlet extends HttpServlet {
	private BibleService service = new BibleService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String q = req.getParameter("q");
		if (q == null || q.isEmpty()) {
			resp.sendRedirect("/index.html");
		} else {
			CompassSearchSession search = PMF.getCompass().openSearchSession();
			CompassHits results = search.find(q);
			resp.setContentType("text/html");
			PrintWriter writer = resp.getWriter();
			writer.println("Il y a " + results.getLength() + " résultats pour la recherche \"" +q + "\"<br />");
			for (CompassHit result : results) {
				Resource resource = result.getResource();
				writer.println("<b><a href=\"/" + resource.getId() + "\">"+ resource.getValue("book") + " " + resource.getValue("chapter") +"</a></b><br/>");
				writer.println("<p><sup>" + resource.getValue("verse") + "</sup>" + resource.getValue("verset") + "</p><br/>");
			}
			search.close();
		}
	}
}
