package cn.laochou.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.laochou.service.MatrixService;
import cn.laochou.utils.PrintUtils;

@WebServlet("/matrix/")
public class MatrixController extends BaseServlet {
	
	private MatrixService matrixService = new MatrixService();
	
	private static final long serialVersionUID = 1L;
	
	public void plus(HttpServletRequest request, HttpServletResponse resposne) {
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		String row = request.getParameter("row");
		String col = request.getParameter("col");
		if(first != null && second != null && row != null && col != null) {
			double[][] matrixFirst = stringToDoubleArray(first, Integer.parseInt(row), Integer.parseInt(col));
			double[][] matrixSecond = stringToDoubleArray(second, Integer.parseInt(row), Integer.parseInt(col));
			double[][] result = matrixService.plus(matrixFirst, matrixSecond);
			PrintUtils.printTwoDimensionalArray(result);
		}
		
		
	}
	
	private double[][] stringToDoubleArray(String str, Integer row, Integer col){
		double[][] doubleArray = new double[row][col];
		String[] strArray = str.split(",");
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				doubleArray[i][j] = Double.parseDouble(strArray[i+j]);
			}
		}
		return doubleArray;
	}

	
	
}
