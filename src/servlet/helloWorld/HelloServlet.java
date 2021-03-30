package servlet.helloWorld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private String message;

	public void init() throws ServletException {
		// Do required initialization
		message = "Hello World";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("userName");
		String userName = null;
		if (attribute instanceof String) {
			userName = (String) attribute;
		}
		int age = (int) session.getAttribute("age");
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "Hello " + " - " + userName + " with age - " + age + "</h1>");
	}

	public void destroy() {
		// do nothing.
	}
}
