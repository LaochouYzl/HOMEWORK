package cn.laochou.service;

import java.text.DecimalFormat;


/**
 * 	求行列式的值
 * @author Administrator
 *
 */
public class DeterminantService {

	
	/**
	 * 	求行列式的值
	 * @param daterminant
	 * @return
	 */
	public double getDeterminantValue(double[][] daterminant) {
		if(daterminant.length == 1) {
			return daterminant[0][0];
		}else if(daterminant.length == 2) {
			return daterminant[0][0]*daterminant[1][1] - daterminant[1][0]*daterminant[0][1];
		}else {
			return getNJDeterminatValue(daterminant);
		}
	}
	
	/**
	 * 	求N阶行列式的值
	 * @param determinant
	 * @return
	 */
	private double getNJDeterminatValue(double[][] determinant) {
		// 看后面是正负
		double result = 1.0;
		for(int i = 0; i < determinant.length; i++) {
			// 对角线为0
			if(determinant[i][i] == 0) {
				determinant = changeDeterminantNoZero(determinant, i, i);
				result *= -1;
			}
			//让开始处理的行的首位为0处理为三角形式
			for (int j = 0; j <i; j++) {
				//如果要处理的列为0则和自己调换一下位置，这样就省去了计算
				if (determinant[i][j] == 0) {
					continue;
				}
				// 如果jj的位置为0 的话, 那么下面一行的jj位置不一定为0, 进行替换
				if (determinant[j][j]==0) {
					double[] temp = determinant[i];
					determinant[i] = determinant[i-1];
					determinant[i-1] = temp;
					result*=-1;
					continue;
				}
				// 这里主要是求出比例, 使我们需要的位置变为0
				double  ratio = -(determinant[i][j]/determinant[j][j]);
				determinant[i] = addValue(determinant[i],determinant[j],ratio);
			}
		}
		DecimalFormat df = new DecimalFormat(".##");
		return Double.parseDouble(df.format(mathValue(determinant,result)));
	}
	
	
	/**
	 *	 计算行列式的结果
	 * @param value
	 * @return
	 */
	private double mathValue(double[][] value,double result){
		for (int i = 0; i < value.length; i++) {
			//如果对角线上有一个值为0则全部为0，直接返回结果
			if (value[i][i]==0) {
				return 0;
			}
			result *= value[i][i];
		}
		return result;
	}
	
	/***
	 *	 将i行之前的每一行乘以一个系数，使得从i行的第i列之前的数字置换为0
	 * @param currentRow 当前要处理的行
	 * @param frontRow i行之前的遍历的行
	 * @param ratio 要乘以的系数
	 * @return 将i行i列之前数字置换为0后的新的行
	 */
	private double[] addValue(double[] currentRow,double[] frontRow, double ratio){
		for (int i = 0; i < currentRow.length; i++) {
			currentRow[i] += frontRow[i]*ratio;
		}
		return currentRow;
	}
	
	
	// 找到该位置非0的与该行进行替换
	private double[][] changeDeterminantNoZero(double[][] determinant, int row, int col){
		for (int j = row; j < determinant.length; j++) {
			//进行行调换
			if (determinant[j][col] != 0) {
				double[] temp = determinant[row];
				determinant[row] = determinant[j];
				determinant[j] = temp;
				return determinant;
			}
		}
		return determinant;

	}
	
	
	public static void main(String[] args) {
//		double[][] test = {{2,1,-1},{4,-1,1},{201,102,-99}};
		double[][] test = {{1,0,-1,2},{-2,1,3,1},{0,1,0,-1},{1,3,4,-2}};
		DeterminantService lineGenerationService = new DeterminantService();
		double result = lineGenerationService.getDeterminantValue(test);
		System.out.println(result);
		MatrixService matrixService = new MatrixService();
		System.out.println(matrixService.getMartrixResult(test));
	}
	
	
}
