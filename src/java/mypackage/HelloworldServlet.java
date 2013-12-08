package mypackage;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eclipsesource.json.JsonObject;

public class HelloworldServlet extends HttpServlet {
	private static final long serialVersionUID = -7759593256585062849L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		if ("json".equals(req.getParameter("format"))) {
			resp.setContentType("application/json");
			String json = new JsonObject().add("msg", "Hello!").toString();
			resp.getWriter().print(json);
		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello, world");
		}
	}
}