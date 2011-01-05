package bibleapi.importbible;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class BibleeapiImportServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(BibleeapiImportServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Queue queue = QueueFactory.getQueue("import-queue");
		
		addBibleFile(queue, new File("bible/"), "bible/");
	}
	
	private static void addBibleFile(Queue queue, File dir, String prefix) {
		for (File file : dir.listFiles()) {
			if (canRead(file)) {
				String filename = file.getName();
				if (file.isDirectory()) {
					addBibleFile(queue, file, prefix + filename + "/");
				} else {
					if (filename.endsWith(".txt")) {
						System.out.println("addBibleFile " + filename);
						queue.add(withUrl("/admin/importBible").method(Method.GET).param("file", prefix + filename).param("offset", "0"));
					}
				}
			}
		}
	}
	
	private static boolean canRead(File file) {
		try {
			return file.canRead();
		} catch (Exception e) {
			LOGGER.info("Unable to read file " + file.getName());
			return false;
		}
	}
}
