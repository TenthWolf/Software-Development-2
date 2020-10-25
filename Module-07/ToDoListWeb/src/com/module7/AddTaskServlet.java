package com.module7;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.module7.*;
/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    } // end AddTaskServlet()

    
   /**
    * Takes the submitted task, and passes it to saveToDatabase()
    */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		// Set the content type
		response.setContentType("text/html");
		TaskItem item = new TaskItem(request.getParameter("newTask"), 
				request.getParameter("newDate"));
	
		saveToDatabase(item);
		System.out.println(item);

		response.sendRedirect("index.jsp");
	} // end doGet()
	

	/**
	 * Saves the passed in TaskItem to the database
	 * @param item
	 */
    public void saveToDatabase(TaskItem item) {
    	 Transaction transaction = null;
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             // start a transaction
             transaction = session.beginTransaction();
             // save the student object
             session.save(item);
             // commit transaction
             transaction.commit();
             
         } catch (Exception e) {
             e.printStackTrace();
         }
   
    } // end saveToDatabase()


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} // end doPost()

} // end AddTaskServlet
