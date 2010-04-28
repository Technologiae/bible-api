package bibleapi;

import java.io.IOException;
import javax.servlet.http.*;

import bibleapi.core.Bible;
import bibleapi.service.BibleService;

@SuppressWarnings("serial")
public class BibleeapiServlet extends HttpServlet {
	private BibleService service = new BibleService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String reference = req.getRequestURI();
		if (reference != null && reference.startsWith("/"))
			reference = reference.substring(1);
		if (reference == null || reference.isEmpty()) {
			resp.sendRedirect("/index.html");
		}
		else {
			if (reference.startsWith("/"))
				reference = reference.substring(1);
			Bible bible = service.getVerset(reference);
			if (bible != null) {
				resp.setContentType("text/html");
				resp.getWriter().println(bible.getVerset());
			}
			else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, reference + " n'existe pas");
			}
		}
	}
}
