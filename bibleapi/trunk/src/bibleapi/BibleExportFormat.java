/**
 * 
 */
package bibleapi;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import bibleapi.core.Bible;
import bibleapi.core.Book;

/**
 * 
 *
 */
public class BibleExportFormat {
	public static String getText (Collection<Bible> results, String query) {
		StringBuilder response = new StringBuilder();
		//response.append("Il y a ").append(results.size()).append(" résultats pour la recherche \"").append(query).append("\"<br />");
		String currentVersion = null;
		String currentBook = null;
		Integer currentChapter = null;
		for (Bible result : results) {
			if (currentVersion == null || !currentVersion.equals(result.getVersion())) {
				currentVersion = result.getVersion();
				//response.append("<h1>").append(Version.getVersion(currentVersion).getName()).append("</h1>");
			}
			if (currentBook == null || !currentBook.equals(result.getBook())) {
				currentBook = result.getBook();
				Book book = Book.getBook(currentBook);
				if (book != null) {
					response.append("<h2>").append(book.getRealName()).append("</h2>");
				}
			}
			if (currentChapter == null || !currentChapter.equals(result.getChapter())) {
				currentChapter = result.getChapter();
				response.append("<b>&nbsp;").append(currentChapter).append("&nbsp;</b>");
			}
			response.append("&nbsp;<a href=\"/").append(result.getReference()).append("\"><sup>").append(result.getVersetNumber());
			response.append("</sup></a>").append(result.getVerset());
		}
		return response.toString();
	}
	
	public static String getXml (Collection<Bible> results, String query) {
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
			for (Bible result : results) {
				Element e = document.createElement("result");
				e.setAttribute("id", result.getReference());
				e.setAttribute("version", result.getVersion());
				e.setAttribute("book", result.getBook());
				e.setAttribute("chapter", result.getChapter().toString());
				e.setAttribute("verset", result.getVersetNumber().toString());
				e.appendChild(document.createCDATASection(result.getVerset()));
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
	public static String getJson (Collection<Bible> results, String query) {
		JSONObject root = new JSONObject();
		root.put("query", query);
		JSONObject r;
		List<JSONObject> list = new ArrayList<JSONObject>();
		for (Bible result : results) {
			r = new JSONObject();
			r.put("id", result.getReference());
			r.put("version", result.getVersion());
			r.put("book", result.getBook());
			r.put("chapter", result.getChapter());
			r.put("verseNumber", result.getVersetNumber());
			r.put("verset", result.getVerset());
			list.add(r);
		}
		root.put("results", list);
		return root.toJSONString();
	}
}
