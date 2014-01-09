package mypackage;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eclipsesource.json.JsonObject;

@WebServlet("/helloworld/*")
public class HelloworldServlet extends HttpServlet {
	private static final long serialVersionUID = -7759593256585062849L;
	private static final Logger LOG = LoggerFactory.getLogger(HelloworldServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		LOG.info("serving request");
		if ("json".equals(req.getParameter("format"))) {
			LOG.info("special format for request {}", req);
			resp.setContentType("application/json");
			String json = new JsonObject().add("msg", "Hello!").toString();
			resp.getWriter().print(json);
		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello, world");
		}
	}
}