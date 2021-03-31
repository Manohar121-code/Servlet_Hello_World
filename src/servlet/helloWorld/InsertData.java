package servlet.helloWorld;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.DatabaseConnection;

@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			
			// Initialize the database
			con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			st = con.prepareStatement("insert into employee2 (empName, salary, address) values (?, ?, ?)");
			
			String empName = request.getParameter("empName");
			String salary = request.getParameter("salary");
			String address = request.getParameter("address");
			
			// For the first parameter,
			// get the data using request object
			// sets the data to st pointer
			st.setString(1, empName);

			// Same for second parameter
			st.setInt(2, Integer.parseInt(salary));
			
			st.setString(3, address);

			// Execute the insert command using executeUpdate()
			// to make changes in database
			st.executeUpdate();

			// Close all the connections

			// Get a writer pointer
			// to display the successful result
			PrintWriter out = response.getWriter();
			out.println("<html><body><b>Successfully Inserted" + "</b></body></html>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}