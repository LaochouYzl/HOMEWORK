package cn.laochou.controller;


import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.laochou.dao.CommentDao;
import cn.laochou.dao.QuizDao;
import cn.laochou.pojo.Comment;
import cn.laochou.pojo.Quiz;
import cn.laochou.pojo.User;

@WebServlet("/blog")
public class BlogController extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	private QuizDao quizDao = new QuizDao();
	
	private CommentDao commentDao = new CommentDao();
   
	// 跳转到blog页面
	public void toBlogPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Quiz> list = quizDao.selectAllQuiz();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/Blog.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 详情页
	public void detail(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if(id != null) {
			Quiz quiz = quizDao.selectQuizById(Integer.parseInt(id.trim()));
			request.setAttribute("quiz", quiz);
			List<Comment> list = commentDao.selectCommentById(Integer.parseInt(id.trim()));
			request.setAttribute("list", list);
			try {
				request.getRequestDispatcher("/Detail.jsp").forward(request, response);
				return;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("code", "404");
			request.setAttribute("msg", "您的参数错误");
			try {
				request.getRequestDispatcher("/Error.jsp").forward(request, response);
				return;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 发布评论
	public void putComment(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		String content = request.getParameter("content");
		String quizId = request.getParameter("quiz_id");
		if(user != null && content != null && quizId != null) {
			commentDao.insertComment(user.getId(), Integer.parseInt(quizId.trim()), content);
			try {
				response.sendRedirect("blog?method=detail&id="+quizId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("code", "404");
			request.setAttribute("msg", "您的参数错误");
			try {
				request.getRequestDispatcher("/Error.jsp").forward(request, response);
				return;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 根据条件查询
	public void search(HttpServletRequest request, HttpServletResponse response) {
		String condition = request.getParameter("condition");
		if(condition != null) {
			List<Quiz> list = quizDao.searchQuizByCondition(condition.trim());
			request.setAttribute("list", list);
			try {
				request.getRequestDispatcher("/Blog.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("code", "404");
			request.setAttribute("msg", "您的参数错误");
			try {
				request.getRequestDispatcher("/Error.jsp").forward(request, response);
				return;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 去发布问答界面
	public void toPutPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/Put.jsp").forward(request, response);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
