package bibleapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import org.compass.core.CompassHit;
import org.compass.core.CompassHits;
import org.compass.core.CompassSearchSession;
import org.compass.core.Resource;
import org.json.simple.JSONObject;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import bibleapi.tools.PMF;

@SuppressWarnings("serial")
public class BibleeapiSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String q = req.getParameter("q");
		/* no query */
		if (q == null || q.isEmpty()) {
			resp.sendRedirect("/index.html");
		} else {
			CompassSearchSession search = PMF.getCompass().openSearchSession();
			CompassHits results = search.find(q);
			String outputFormat = req.getParameter("o") != null ? req.getParameter("o") : "";
			PrintWriter writer = resp.getWriter();
			/* XML response */
			if (outputFormat.equalsIgnoreCase("xml")) {
				resp.setContentType("application/xml");
				writer.println(this.getXml(results, q));
			}
			/* JSON response */
			else if (outputFormat.equals("json")) {
				resp.setContentType("text/html");
				writer.println(this.getJson(results, q));
			}
			/* plain text response */
			else {
				resp.setContentType("text/html");
				writer.println(this.getText(results, q));
			}
			search.close();
		}
	}
	
	private String getText (CompassHits results, String query) {
		String response = "Isssl y a " + results.getLength()
				+ " résultats pour la recherche \"" + query + "\"<br />";
		for (CompassHit result : results) {
			Resource resource = result.getResource();
			response += "<b><a href=\"/" + resource.getId() + "\">"
					+ resource.getValue("book") + " "
					+ resource.getValue("chapter") + "</a></b><br/>";
			response += "<p><sup>" + resource.getValue("verse")
					+ "</sup>" + resource.getValue("verset")
					+ "</p><br/>";
		}
		return response;
	}
	
	private String getXml (CompassHits results, String query) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			document.setXmlVersion("1.0");
			document.setXmlStandalone(true);
			Element root = document.createElement("results");
			root.setAttribute("query", query);
			document.appendChild(root);
			for (CompassHit result : results) {
				Resource resource = result.getResource();
				Element e = document.createElement("result");
				e.setAttribute("id", resource.getId());
				e.setAttribute("version", resource.getValue("version"));
				e.setAttribute("book", resource.getValue("book"));
				e.setAttribute("chapter", resource.getValue("chapter"));
				e.setAttribute("verse", resource.getValue("verse"));
				e.setTextContent(resource.getValue("verset"));
				root.appendChild(e);
			}

			DOMSource domSource = new DOMSource(document);
			StringWriter w = new StringWriter();
			StreamResult r = new StreamResult(w);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, r);

			return w.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private String getJson (CompassHits results, String query) {
		JSONObject root = new JSONObject();
		root.put("query", query);
		JSONObject r;
		List<JSONObject> list = new ArrayList<JSONObject>();
		for (CompassHit result : results) {
			Resource resource = result.getResource();
			r = new JSONObject();
			r.put("id", resource.getId());
			r.put("version", resource.getValue("version"));
			r.put("book", resource.getValue("book"));
			r.put("chapter", resource.getValue("chapter"));
			r.put("verse", resource.getValue("verse"));
			r.put("verset", resource.getValue("verset"));
			list.add(r);
		}
		root.put("results", list);
		return root.toJSONString();
	}
}
