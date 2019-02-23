package cn.laochou.service;

import cn.laochou.utils.PrintUtils;

/**
 * 	�������
 * @author Administrator
 *
 */
public class MatrixService {
	
	
	/**
	 * 	����ļӷ�(�Լ���֤������ĺϷ���)
	 * @param frist
	 * @param second
	 * @return
	 */
	public double[][] plus(double[][] frist, double[][] second){
		double[][] result = new double[frist.length][frist.length];
		for(int i = 0; i < frist.length; i++) {
			for(int j = 0; j < frist.length; j++) {
				result[i][j] = frist[i][j] + second[i][j];
			}
		}
		return result;
	}
	
	/**
	 * 	����ĳ˷�
	 * @param frist
	 * @param second
	 * @return
	 */
	public double[][] multiply(double[][] frist, double[][] second){
		double[][] result = new double[frist.length][second[0].length];
		for(int i = 0; i < frist.length; i++) {
			for(int j = 0; j < second[0].length; j++) {
				double[] newDouble = new double[second.length];
				for(int k = 0; k < second.length; k++) {
					newDouble[k] = second[k][j];
				}
				result[i][j] = realMultiply(frist[i], newDouble);
			}
		}
		return result;
	}
	
	private double realMultiply(double[] frist, double[] second) {
		double result = 0.0;
		for(int i = 0; i < frist.length; i++) {
			result += frist[i]*second[i];
		}
		return result;
	}
	
	/**
	 * 	�����ת��
	 * @param frist
	 * @return
	 */
	public double[][] transposition(double[][] frist){
		double[][] second = new double[frist[0].length][frist.length];
		for(int i = 0; i < frist.length; i++) {
			for(int j = 0; j < frist[0].length; j++) {
				second[j][i] = frist[i][j];
			}
		}
		return second;
	}
	
	/**
	 * 	��������
	 * @param frist
	 * @return
	 */
	public double getRank(double[][] frist) {
		for(int i = 0; i < frist.length; i++) {
			// �Խ���Ϊ0
			if(frist[i][i] == 0) {
				frist = changeDeterminantNoZero(frist, i, i);
			}
			//�ÿ�ʼ������е���λΪ0����Ϊ������ʽ
			for (int j = 0; j <=i; j++) {
				//���Ҫ�������Ϊ0����Լ�����һ��λ�ã�������ʡȥ�˼���
				if (frist[i][j] == 0) {
					continue;
				}
				// ���jj��λ��Ϊ0 �Ļ�, ��ô����һ�е�jjλ�ò�һ��Ϊ0, �����滻
				if (frist[j][j]==0) {
					double[] temp = frist[i];
					frist[i] = frist[i-1];
					frist[i-1] = temp;
					continue;
				}
				// ������Ҫ���������, ʹ������Ҫ��λ�ñ�Ϊ0
				double  ratio = -(frist[i][j]/frist[j][j]);
				frist[i] = addValue(frist[i],frist[j],ratio);
			}
		}
		PrintUtils.printTwoDimensionalArray(frist);
		return 0;
	}
	
	/**
	 * 	��ȡָ��λ�õĴ�������ʽ
	 * @param frist
	 * @param h ĳһ��
	 * @param v ĳһ��
	 * @return
	 */
	private double[][] getCofactor(double[][] frist, int h, int v){
		int H = frist.length;
        int V = frist[0].length;
        double[][] newdata = new double[H-1][V-1];
        for(int i=0; i<newdata.length; i++) {
            if(i < h-1) {
                for(int j=0; j<newdata[i].length; j++) {
                    if(j < v-1) {
                        newdata[i][j] = frist[i][j];
                    }else {
                        newdata[i][j] = frist[i][j+1];
                    }
                }
            }else {
                for(int j=0; j<newdata[i].length; j++) {
                    if(j < v-1) {
                        newdata[i][j] = frist[i+1][j];
                    }else {
                        newdata[i][j] = frist[i+1][j+1];
                    }
                }
            }
        }
        return newdata;
	}
	
	
	 /*
	  *	��������ʽ��ֵ
	  */
    public double getMartrixResult(double[][] data) {
  
        if(data.length == 2) {
            return data[0][0]*data[1][1] - data[0][1]*data[1][0];
        }
        double result = 0;
        int num = data.length;
        double[] nums = new double[num];
        for(int i=0; i<data.length; i++) {
            if(i%2 == 0) {
                nums[i] = data[0][i] * getMartrixResult(getCofactor(data, 1, i+1));
            }else {
                nums[i] = -data[0][i] * getMartrixResult(getCofactor(data, 1, i+1));
            }
        }
        for(int i=0; i<data.length; i++) {
            result += nums[i];
        }
        return result;
    }
    
    /**
     * 	�������
     * @param data
     * @return
     */
    public double[][] getReverseMartrix(double[][] data) {
        double[][] newdata = new double[data.length][data[0].length];
        double A = getMartrixResult(data);
        for(int i=0; i<data.length; i++) {
            for(int j=0; j<data[0].length; j++) {
                if((i+j)%2 == 0) {
                    newdata[i][j] = getMartrixResult(getCofactor(data, i+1, j+1)) / A;
                }else {
                    newdata[i][j] = -getMartrixResult(getCofactor(data, i+1, j+1)) / A;
                }

            }
        }
        newdata = transform(newdata);
        return newdata;
    }

    private double[][] transform(double[][] newdata) {
        // TODO Auto-generated method stub
        double[][] newdata2 = new double[newdata[0].length][newdata.length];
        for(int i=0; i<newdata.length; i++) 
            for(int j=0; j<newdata[0].length; j++) {
                newdata2[j][i] = newdata[i][j];
            }
        return newdata2;
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
	
	
	public static void main(String[] args) {
		double[][] frist = {{1,2, 3}, {2, 1, 3}};
		double[][] second = {{1, 2}, {1, 2}, {1, 2}};
		double[][] TestMatrix = {
	   			   {1, 22, 34,22}, 
	   			   {1, 11,5,21} ,
	   			   {0,1,5,11},
	   			   {7,2,13,19}};
		double[][] test = {{1,2,3 },  
		        {2,2,1 },  
		        {3,4,3 }};
//		double[][] result = new MatrixService().multiply(frist, second);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																		
//		PrintUtils.printTwoDimensionalArray(result);
//		new MatrixService().getRank(TestMatrix);
		PrintUtils.printTwoDimensionalArray(new MatrixService().getReverseMartrix(test));
	}
}