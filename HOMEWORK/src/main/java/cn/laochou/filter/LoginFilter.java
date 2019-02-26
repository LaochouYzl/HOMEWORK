package cn.laochou.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.laochou.pojo.User;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		String requestUrl = httpServletRequest.getRequestURI();
		if(requestUrl.contains("/user")||requestUrl.contains("/static")||requestUrl.contains("/Register.jsp")) {
			chain.doFilter(request, response);
		}else {
			// Î´µÇÂ¼
			if(user == null) {
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return;
			}
			chain.doFilter(request, response);
		}
		
		
	}

}
