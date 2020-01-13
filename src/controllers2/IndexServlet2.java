package controllers2;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models2.Task;
import utils2.DBUtil2;
/**
 * Servlet implementation class IndexServlet2
 */
@WebServlet("/index")
public class IndexServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil2.createEntityManager();

        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class)
                                   .getResultList();

        em.close();

        request.setAttribute("tasks", tasks);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views2/tasks/index.jsp");
        rd.forward(request, response);
    }
}