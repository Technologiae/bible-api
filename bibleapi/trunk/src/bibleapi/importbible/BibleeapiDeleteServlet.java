package bibleapi.importbible;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibleapi.core.Bible;
import bibleapi.service.BibleService;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.apphosting.api.DeadlineExceededException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class BibleeapiDeleteServlet extends HttpServlet {
	private static final int LIMIT_NUMBERS = 1000;
	private BibleService service = new BibleService();
	private static final Logger LOGGER = Logger
			.getLogger(BibleeapiDeleteServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Integer start = (req.getParameter("start") != null) ?Integer.decode(req.getParameter("start")) : 0;
		Integer inEx = (req.getParameter("inEx") != null) ?Integer.decode(req.getParameter("inEx"))+1 : 1;
		Integer outFor = (req.getParameter("outFor") != null) ?Integer.decode(req.getParameter("outFor"))+1 : 1;
		Integer offset = 0;
		try {
			Objectify ofy = ObjectifyService.begin();
			Iterable<Key<Bible>> versets = service.listFirstBible(
					LIMIT_NUMBERS, start);
			for (Key<Bible> verset : versets) {
				ofy.delete(verset);
				offset++;
//				if (offset % 10 == 0)
//					Thread.sleep(0); //Pour être sur que l'exception DeadlinExceededException soit envoyé
//				if (System.currentTimeMillis() - startTime > LIMIT_MILLIS) {
//					LOGGER.info("delete break " + offset.toString());
//					Queue queue = QueueFactory.getDefaultQueue();
//					queue.add(url("/admin/delete").method(Method.GET)
//							.param("offset", offset.toString())
//							.param("start", start.toString())
//							.param("inFor", "1"));
//					break;
//				}
			}
			LOGGER.info("delete end " + offset.toString());
			if (offset >= LIMIT_NUMBERS) {
				Queue queue = QueueFactory.getDefaultQueue();
				queue.add(withUrl("/admin/delete").method(Method.GET)
						.param("offset", offset.toString())
						.param("start", start.toString()).param("outFor", outFor.toString()));
			}
		} catch (DeadlineExceededException e) {
			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(withUrl("/admin/delete").method(Method.GET)
					.param("offset", offset.toString())
					.param("start", start.toString()).param("inEx", inEx.toString()));
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().append(": " + offset.toString());
		} 
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
