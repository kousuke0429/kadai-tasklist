package controllers2;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models2.Task;
import utils2.DBUtil2;
/**
 * Servlet implementation class NewServlet2
 */
@WebServlet("/new")
public class NewServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil2.createEntityManager();
        em.getTransaction().begin();

        // Messageのインスタンスを生成
        Task m = new Task();

        // mの各プロパティにデータを代入
        String title = "taro";
        m.setTitle(title);

        String content = "hello";
        m.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());     // 現在の日時を取得
        m.setCreated_at(currentTime);
        m.setUpdated_at(currentTime);

        // データベースに保存
        em.persist(m);
        em.getTransaction().commit();

        // 自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(m.getId()).toString());

        em.close();
     }
}