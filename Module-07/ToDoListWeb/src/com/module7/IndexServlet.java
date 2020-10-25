package com.module7;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    } // end IndexServlet()


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // end doGet()

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} // end doPost()
	
	
	/**
	 * Creates a table filled with all tasks from the database
	 * @return
	 */
	public static String fillTable() {
		String result = "<table>";
		
		result += "<tr><th>Task</th>";
		result += "<th>Date</th></tr>";

		
		List<TaskItem> tasks = getAllTasks();
		for(TaskItem t : tasks) {
			result += "<tr>";
			result += "<td>" + t.getTask() + "</td>";
			result += "<td>" + t.getDate() + "</td>";
			result += "</tr>";

		}
		
		return result += "</table>";
	} // end fillTable()
	
	
	/**
	 * Returns a list of all tasks from the database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List <TaskItem> getAllTasks() {

        Transaction transaction = null;
        List <TaskItem> listOfTasks = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            listOfTasks = session.createQuery("from TaskItem").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
        
            e.printStackTrace();
        }
        return listOfTasks;
    } // end getAllTasks()
	
	

} // end IndexServlet
