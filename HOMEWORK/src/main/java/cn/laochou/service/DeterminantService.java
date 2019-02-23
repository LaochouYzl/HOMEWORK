package cn.laochou.service;

import java.text.DecimalFormat;


/**
 * 	������ʽ��ֵ
 * @author Administrator
 *
 */
public class DeterminantService {

	
	/**
	 * 	������ʽ��ֵ
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
	 * 	��N������ʽ��ֵ
	 * @param determinant
	 * @return
	 */
	private double getNJDeterminatValue(double[][] determinant) {
		// ������������
		double result = 1.0;
		for(int i = 0; i < determinant.length; i++) {
			// �Խ���Ϊ0
			if(determinant[i][i] == 0) {
				determinant = changeDeterminantNoZero(determinant, i, i);
				result *= -1;
			}
			//�ÿ�ʼ������е���λΪ0����Ϊ������ʽ
			for (int j = 0; j <i; j++) {
				//���Ҫ�������Ϊ0����Լ�����һ��λ�ã�������ʡȥ�˼���
				if (determinant[i][j] == 0) {
					continue;
				}
				// ���jj��λ��Ϊ0 �Ļ�, ��ô����һ�е�jjλ�ò�һ��Ϊ0, �����滻
				if (determinant[j][j]==0) {
					double[] temp = determinant[i];
					determinant[i] = determinant[i-1];
					determinant[i-1] = temp;
					result*=-1;
					continue;
				}
				// ������Ҫ���������, ʹ������Ҫ��λ�ñ�Ϊ0
				double  ratio = -(determinant[i][j]/determinant[j][j]);
				determinant[i] = addValue(determinant[i],determinant[j],ratio);
			}
		}
		DecimalFormat df = new DecimalFormat(".##");
		return Double.parseDouble(df.format(mathValue(determinant,result)));
	}
	
	
	/**
	 *	 ��������ʽ�Ľ��
	 * @param value
	 * @return
	 */
	private double mathValue(double[][] value,double result){
		for (int i = 0; i < value.length; i++) {
			//����Խ�������һ��ֵΪ0��ȫ��Ϊ0��ֱ�ӷ��ؽ��
			if (value[i][i]==0) {
				return 0;
			}
			result *= value[i][i];
		}
		return result;
	}
	
	/***
	 *	 ��i��֮ǰ��ÿһ�г���һ��ϵ����ʹ�ô�i�еĵ�i��֮ǰ�������û�Ϊ0
	 * @param currentRow ��ǰҪ�������
	 * @param frontRow i��֮ǰ�ı�������
	 * @param ratio Ҫ���Ե�ϵ��
	 * @return ��i��i��֮ǰ�����û�Ϊ0����µ���
	 */
	private double[] addValue(double[] currentRow,double[] frontRow, double ratio){
		for (int i = 0; i < currentRow.length; i++) {
			currentRow[i] += frontRow[i]*ratio;
		}
		return currentRow;
	}
	
	
	// �ҵ���λ�÷�0������н����滻
	private double[][] changeDeterminantNoZero(double[][] determinant, int row, int col){
		for (int j = row; j < determinant.length; j++) {
			//�����е���
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
