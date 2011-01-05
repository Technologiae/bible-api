package bibleapi.importbible;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibleapi.core.Reference;
import bibleapi.service.BibleService;

@SuppressWarnings("serial")
public class BibleeapiImportBibleServlet extends HttpServlet {
	private BibleService service = new BibleService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String filename = req.getParameter("file");
		Integer pos = 0;
		if (filename != null && !filename.isEmpty()) {
			// System.out.println("Import file " + filename);
			String name = filename.substring(filename.lastIndexOf("/") + 1,
					filename.lastIndexOf(".")).toLowerCase();
			System.out.println("Process " + name);
			int dashPos = name.indexOf(" - ");
			if (dashPos > 0) {
				String version = name.substring(0, dashPos);
				String book = name.substring(dashPos + 3);
				// System.out.println(version + ":" + book);
				try {
					// Open the file that is the first
					// command line parameter
					File file = new File(filename);
					FileInputStream fstream = new FileInputStream(file);
					// Get the object of DataInputStream
					InputStreamReader in = new InputStreamReader(fstream,
							"UTF-8");
					BufferedReader br = new BufferedReader(in);
					String strLine;
					String verset;
					String strChapter;
					String strVerset;
					Integer chapter;
					Integer verse;
					Reference ref = new Reference();
					ref.setBook(book);
					ref.setVersion(version);
					int pos1, pos2 = 0;
					pos = 0;
					// Read File Line By Line
					while ((strLine = br.readLine()) != null) {
						// Print the content on the console
						// System.out.println(strLine);
						pos1 = strLine.indexOf(",");
						pos2 = strLine.indexOf("\t");
						strChapter = strLine.substring(0, pos1);
						strVerset = strLine.substring(pos1 + 1, pos2);
						verset = strLine.substring(pos2 + 1);
						// System.out.println(strChapter);
						// System.out.println(strVerset);
						// System.out.println(verset);
						chapter = Integer.decode(strChapter);
						verse = Integer.decode(strVerset);
						ref.setChapter(chapter);
						ref.setVerset(verse);
						// System.out.println(ref.toString());
						service.addVerset(ref, verset);
						// if (offset % 10 == 0)
						// Thread.sleep(0); //Pour être sur que l'exception
						// DeadlinExceededException soit envoyé
						// if (System.currentTimeMillis() - startTime >
						// LIMIT_MILLIS) {
						// Queue queue = QueueFactory.getQueue("import-queue");
						// queue.add(url("/admin/importBible").method(Method.GET).param("file",
						// filename).param("offset",
						// pos.toString()).param("inEx", "1"));
						// break;
						// }
						pos++;
					}
					// Close the input stream
					in.close();
				} catch (Exception e) {// Catch exception if any
					e.printStackTrace();
					System.err.println("Error: " + e.getMessage());
				}
			}
		}
	}
}
