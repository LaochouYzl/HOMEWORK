package cn.laochou.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.laochou.service.DeterminantService;

/**
 * 行列式请求
 * @author Administrator
 *
 */
@WebServlet("/determinant")
public class DeterminantController extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private DeterminantService determinantService = new DeterminantService();
       
    public void getDeterminantValue(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	PrintWriter out = response.getWriter();
    	try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	String frist = request.getParameter("frist");
    	String row = request.getParameter("row");
    	String col = request.getParameter("col");
    	if(frist != null && col != null && row != null) {
    		double[][] data = stringToDoubleArray(frist, Integer.parseInt(row), Integer.parseInt(col));
    		double result = determinantService.getDeterminantValue(data);
    		out.write("{\"content\":"+result+", \"row\":"+row+", \"col\":"+col+"}");
    		out.close();
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
    
    
    private double[][] stringToDoubleArray(String str, Integer row, Integer col){
		double[][] doubleArray = new double[row][col];
		int index = 0;
		String[] strArray = str.split(" ");
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				doubleArray[i][j] = Double.parseDouble(strArray[index]);
				index++;
			}
		}
		return doubleArray;
	}
   
	
}
