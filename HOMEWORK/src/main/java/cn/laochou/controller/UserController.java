package cn.laochou.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.laochou.dao.CodeDao;
import cn.laochou.enums.EmailEnums;
import cn.laochou.enums.RegisterEnums;
import cn.laochou.pojo.User;
import cn.laochou.service.CodeService;
import cn.laochou.service.UserService;
import cn.laochou.utils.EmailUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserController extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
	
	private CodeService codeService = new CodeService();
	
	private CodeDao codeDao = new CodeDao();
       
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(userName != null && password != null) {
			User user = userService.checkUser(userName, password);
			if(user != null) {
				request.getSession().setAttribute("user", user);
				try {
					if(user.getType() == 1) {
						request.getRequestDispatcher("/Main.jsp").forward(request, response);
					}else {
						request.getRequestDispatcher("/Index.jsp").forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				request.setAttribute("msg", "用户名或密码错误");
				try {
					request.getRequestDispatcher("/Login.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 	注解
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String password_again = request.getParameter("password_again");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		if(userName != null && password != null && password_again != null && email != null && code != null) {
			// 校验邮箱和code, 以及两次密码
			if(password.trim().equals(password_again.trim())){
				// 验证通过
				if(!codeDao.checkTheEmailIsRegistered(email)) {
					if(codeService.checkEmailAndCode(email, code)) {
						userService.insertUser(userName, password, email);
						out.write(RegisterEnums.SUCCESS.getId());
					}else {
						// 2 代表验证码错误
						out.write(RegisterEnums.CODEERROR.getId());
					}
				}else {
					// 代表该邮箱已经注册了
					out.write(RegisterEnums.EMAILISREGISTERED.getId());
				}
			}else {
				// 3 代表两次密码错误
				out.write(RegisterEnums.PASSWORDERROR.getId());
			}
		}
	}
	
	
	/**
	 * 	发送邮件
	 * @param request
	 * @param response
	 */
	public void sendMail(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String email = request.getParameter("email");
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			if(email != null) {
				EmailEnums emailEnums = EmailUtils.sendEmail(email);
				if(emailEnums == EmailEnums.USERABLE) {
					out.write(emailEnums.getId());
				}else if(emailEnums == EmailEnums.REGISTERED) {
					out.write(emailEnums.getId());
				}else if(emailEnums == EmailEnums.UNKNOWN) {
					out.write(emailEnums.getId());
				}
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
