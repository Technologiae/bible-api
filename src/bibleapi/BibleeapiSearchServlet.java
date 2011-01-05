package bibleapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibleapi.core.Bible;
import bibleapi.service.BibleService;

@SuppressWarnings("serial")
public class BibleeapiSearchServlet extends HttpServlet {
	private BibleService service = new BibleService();
	public static final Integer ITEMS_BY_PAGES = 100;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String q = req.getParameter("q");
		/* no query */
		if (q == null || q.isEmpty()) {
			resp.sendRedirect("/index.html");
		} else {
			
			String p = req.getParameter("p");
			Integer page = 0;
			String outputFormat = req.getParameter("o") != null ? req.getParameter("o") : "";
			if (p != null && !p.isEmpty()) {
				page = Integer.valueOf(p);
			}

			List<Bible> results = service.search(q, page, ITEMS_BY_PAGES);
			PrintWriter writer = resp.getWriter();
			/* XML response */
			if (outputFormat.equalsIgnoreCase("xml")) {
				resp.setContentType("application/xml");
				writer.println(BibleExportFormat.getXml(results, q));
			}
			/* JSON response */
			else if (outputFormat.equals("json")) {
				resp.setContentType("application/json");
				writer.println(BibleExportFormat.getJson(results, q));
			}
			/* plain text response */
			else {
				resp.setContentType("text/html");
				writer.println(BibleExportFormat.getText(results, q));
			}
		}
	}
}
