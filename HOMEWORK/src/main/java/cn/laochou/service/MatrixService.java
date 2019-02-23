package cn.laochou.service;

import cn.laochou.utils.PrintUtils;

/**
 * 	矩阵服务
 * @author Administrator
 *
 */
public class MatrixService {
	
	
	/**
	 * 	矩阵的加法(以及验证过矩阵的合法性)
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
	 * 	矩阵的乘法
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
	 * 	矩阵的转置
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
	 * 	求矩阵的秩
	 * @param frist
	 * @return
	 */
	public double getRank(double[][] frist) {
		for(int i = 0; i < frist.length; i++) {
			// 对角线为0
			if(frist[i][i] == 0) {
				frist = changeDeterminantNoZero(frist, i, i);
			}
			//让开始处理的行的首位为0处理为三角形式
			for (int j = 0; j <=i; j++) {
				//如果要处理的列为0则和自己调换一下位置，这样就省去了计算
				if (frist[i][j] == 0) {
					continue;
				}
				// 如果jj的位置为0 的话, 那么下面一行的jj位置不一定为0, 进行替换
				if (frist[j][j]==0) {
					double[] temp = frist[i];
					frist[i] = frist[i-1];
					frist[i-1] = temp;
					continue;
				}
				// 这里主要是求出比例, 使我们需要的位置变为0
				double  ratio = -(frist[i][j]/frist[j][j]);
				frist[i] = addValue(frist[i],frist[j],ratio);
			}
		}
		PrintUtils.printTwoDimensionalArray(frist);
		return 0;
	}
	
	/**
	 * 	获取指定位置的代数余子式
	 * @param frist
	 * @param h 某一行
	 * @param v 某一列
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
	  *	计算行列式的值
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
     * 	求逆矩阵
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