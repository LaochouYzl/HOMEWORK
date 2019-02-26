package cn.laochou.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.laochou.dao.QuizDao;
import cn.laochou.dao.UserDao;
import cn.laochou.pojo.Quiz;
import cn.laochou.pojo.User;

@WebServlet("/admin")
public class AdminController extends BaseServlet{
	
	private QuizDao quizDao = new QuizDao();
	
	private UserDao userDao = new UserDao();

	private static final long serialVersionUID = 1L;
	
	public void toMainPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/Main.jsp").forward(request, response);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void toQuizManager(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Quiz> list = quizDao.selectAllQuiz();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/QuizManager.jsp").forward(request, response);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void toUserManager(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User> list = userDao.selectAllUser();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/UserManager.jsp").forward(request, response);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteQuiz(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if(id != null) {
			quizDao.deleteQuiz(Integer.parseInt(id));
			try {
				response.sendRedirect("admin?method=toQuizManager");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
