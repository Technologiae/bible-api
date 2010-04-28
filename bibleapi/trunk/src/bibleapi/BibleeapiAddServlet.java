package bibleapi;

import java.io.IOException;
import javax.servlet.http.*;

import bibleapi.service.BibleService;

@SuppressWarnings("serial")
public class BibleeapiAddServlet extends HttpServlet {
	private BibleService service = new BibleService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String reference = req.getParameter("reference");
		String verset = req.getParameter("verset");
		if (reference == null || reference.isEmpty() || verset == null || verset.isEmpty()) {
			resp.sendRedirect("/index.html");
		}
		service.addVerset(reference, verset);
		resp.sendRedirect("/" + reference);
	}
}
