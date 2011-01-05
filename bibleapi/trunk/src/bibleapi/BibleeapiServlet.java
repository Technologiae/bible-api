package bibleapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibleapi.core.Bible;
import bibleapi.core.Reference;
import bibleapi.search.Parser;
import bibleapi.service.BibleService;

import com.googlecode.objectify.Key;

@SuppressWarnings("serial")
public class BibleeapiServlet extends HttpServlet {
	private BibleService service = new BibleService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String reference = req.getRequestURI();
		if (reference != null && reference.startsWith("/")) {
			reference = reference.substring(1);
		}
		if (req.getParameter("r") != null) {
			reference = req.getParameter("r"); 
		}
		if (reference == null || reference.isEmpty()) {
			resp.sendRedirect("/index.html");
		}
		else {
			if (reference.startsWith("/"))
				reference = reference.substring(1);
			
			String p = req.getParameter("p");
			Integer page = 0;
			String outputFormat = req.getParameter("o") != null ? req.getParameter("o") : "";
			if (p != null && !p.isEmpty()) {
				page = Integer.valueOf(p);
			}
			List<Reference> references = new Parser().parse(reference);
			Map<Key<Bible>, Bible> bibles = service.getVerset(references);

			
//			resp.setContentType("text/html");
//			resp.getWriter().println("Il y a " + bibles.size() + " résultats <br/>");
//			for (Reference ref : references) {
//				resp.getWriter().println(ref.toString() + " ");
//			}
//			resp.getWriter().println("<br />");
			if (bibles != null) {
				PrintWriter writer = resp.getWriter();
				/* XML response */
				if (outputFormat.equalsIgnoreCase("xml")) {
					resp.setContentType("application/xml");
					writer.println(BibleExportFormat.getXml(bibles.values(), reference));
				}
				/* JSON response */
				else if (outputFormat.equals("json")) {
					resp.setContentType("application/json");
					writer.println(BibleExportFormat.getJson(bibles.values(), reference));
				}
				/* plain text response */
				else {
					resp.setContentType("text/html");
					writer.println(BibleExportFormat.getText(bibles.values(), reference));
				}
//				for (Bible bible : bibles.values()) {
//					if (bible != null) {
//						response.append("<b><a href=\"/");
//						response.append(bible.getReference());
//						response.append("\">");
//						response.append(bible.getBook());
//						response.append(" ");
//						response.append(bible.getChapter());
//						response.append("</a></b><br/>");
//						response.append("<p><sup>");
//						response.append(bible.getVersetNumber());
//						response.append("</sup>");
//						response.append(bible.getVerset());
//						response.append("</p><br/>");
//					}
//				}
//				resp.getWriter().println(response.toString());
//				resp.getWriter().println("<br/><small>&copy; bibleAPI <a href=\"http://www.technologiae.org\">Technologiae.org</a></small>");
			}
			else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, reference + " n'existe pas");
			}
		}
	}
}
