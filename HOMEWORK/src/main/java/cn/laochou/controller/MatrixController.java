package cn.laochou.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.laochou.service.MatrixService;
import cn.laochou.utils.PrintUtils;

@WebServlet("/matrix")
public class MatrixController extends BaseServlet {
	
	private MatrixService matrixService = new MatrixService();
	
	private static final long serialVersionUID = 1L;
	
	// 矩阵的加法
	public void plus(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");;
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		String row = request.getParameter("row");
		String col = request.getParameter("col");
		if(first != null && second != null && row != null && col != null) {
			double[][] matrixFirst = stringToDoubleArray(first, Integer.parseInt(row), Integer.parseInt(col));
			double[][] matrixSecond = stringToDoubleArray(second, Integer.parseInt(row), Integer.parseInt(col));
			if(matrixFirst.length != matrixSecond.length) {
				// 规格不对
				request.setAttribute("code", 303);
				request.setAttribute("msg", "您两个矩阵的规格不同");
				try {
					request.getRequestDispatcher("/Error.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			double[][] result = matrixService.plus(matrixFirst, matrixSecond);
			PrintUtils.printTwoDimensionalArray(result);
			out.write("{\"content\":["+PrintUtils.arrayToString(result)+"], \"row\":"+row+", \"col\":"+col+"}");
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
	
	//矩阵的乘法
	public void multiply(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");;
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		String row = request.getParameter("row");
		String col = request.getParameter("col");
		if(first != null && second != null && row != null && col != null) {
			double[][] matrixFirst = stringToDoubleArray(first, Integer.parseInt(row), Integer.parseInt(col));
			double[][] matrixSecond = stringToDoubleArray(second, Integer.parseInt(row), Integer.parseInt(col));
			if(matrixFirst.length != matrixSecond.length) {
				// 规格不对
				request.setAttribute("code", 303);
				request.setAttribute("msg", "您两个矩阵的规格不同");
				try {
					request.getRequestDispatcher("/Error.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			double[][] result = matrixService.multiply(matrixFirst, matrixSecond);
			PrintUtils.printTwoDimensionalArray(result);
			out.write("{\"content\":["+PrintUtils.arrayToString(result)+"], \"row\":"+row+", \"col\":"+col+"}");
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
	
	// 矩阵的转置
	public void transposition(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");;
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String first = request.getParameter("frist");
		String row = request.getParameter("row");
		String col = request.getParameter("col");
		if(first != null && row != null && col != null) {
			double[][] matrixFirst = stringToDoubleArray(first, Integer.parseInt(row), Integer.parseInt(col));
			double[][] result = matrixService.transposition(matrixFirst);
			PrintUtils.printTwoDimensionalArray(result);
			out.write("{\"content\":["+PrintUtils.arrayToString(result)+"], \"row\":"+row+", \"col\":"+col+"}");
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
	
	// 矩阵的逆矩阵
	public void getReverseMartrix(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");;
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String first = request.getParameter("frist");
		String row = request.getParameter("row");
		String col = request.getParameter("col");
		if(first != null && row != null && col != null) {
			double[][] matrixFirst = stringToDoubleArray(first, Integer.parseInt(row), Integer.parseInt(col));
			double[][] result = matrixService.getReverseMartrix(matrixFirst);
			PrintUtils.printTwoDimensionalArray(result);
			out.write("{\"content\":["+PrintUtils.arrayToString(result)+"], \"row\":"+row+", \"col\":"+col+"}");
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
	
	
	// 求矩阵的秩
	public void getRank(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");;
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String first = request.getParameter("frist");
		String row = request.getParameter("row");
		String col = request.getParameter("col");
		if(first != null && row != null && col != null) {
			double[][] matrixFirst = stringToDoubleArray(first, Integer.parseInt(row), Integer.parseInt(col));
			double result = matrixService.getRank(matrixFirst);
			out.write("{\"content\":["+result+"], \"row\":"+row+", \"col\":"+col+"}");
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
