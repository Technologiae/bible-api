package bibleapi.importbible;

import java.io.BufferedReader;
import java.io.DataInputStream;
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

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String filename = req.getParameter("file");
		if (filename != null && !filename.isEmpty()) {
			System.out.println("Import file " + filename);
			String name = filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("."));
			System.out.println("Process " + name);
			int pos = name.indexOf(" - ");
			if (pos > 0) {
				String version = name.substring(0, pos);
				String book = name.substring(pos + 3);
				System.out.println(version + ":" + book);
				readBibleFile(filename, version, book);
			}
		}
	}

	private void readBibleFile(String filename, String version, String book) {
		try {
			// Open the file that is the first
			// command line parameter
			File file = new File(filename);
			FileInputStream fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
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
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
				pos1 = strLine.indexOf(",");
				pos2 = strLine.indexOf("\t");
				strChapter = strLine.substring(0, pos1);
				strVerset = strLine.substring(pos1 + 1, pos2);
				verset = strLine.substring(pos2 + 1);
				System.out.println(strChapter);
				System.out.println(strVerset);
				System.out.println(verset);
				chapter = Integer.decode(strChapter);
				verse = Integer.decode(strVerset);
				ref.setChapter(chapter);
				ref.setVerset(verse);
				System.out.println(ref.toString());
				service.addVerset(ref, verset);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
	}
}
