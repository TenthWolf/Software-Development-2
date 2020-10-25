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
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public DeleteTaskServlet() {
		super();
		// TODO Auto-generated constructor stub
	} // end DeleteTaskServlet()


	/**
	 * Will delete the selected task from the databse
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String task = request.getParameter("task");

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a user object
			TaskItem item = session.get(TaskItem.class, task);
			if (item != null) {
				session.delete(item);
				System.out.println("Item was deleted");
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.jsp");
	} // end doGet()
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} // end doPost()

	
	/**
	 * Returns the list of database tasks in the form of datalist items 
	 * @return
	 */
	public static String getList() {
		String result = "";
		List<TaskItem> tasks = getAllTasks();

		for (TaskItem t : tasks)
			result += "<option value=\"" + t.getTask() + "\">";

		return result;
	} // end getList()
	

	/**
	 * Returns a list of all tasks from the database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<TaskItem> getAllTasks() {

		Transaction transaction = null;
		List<TaskItem> listOfTasks = null;
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

} // end DeleteTaskServlet
