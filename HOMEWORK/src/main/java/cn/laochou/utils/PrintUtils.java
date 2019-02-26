package cn.laochou.utils;

/**
 * 	打印工具类
 * @author Administrator
 *
 */
public class PrintUtils {
	
	public static void printTwoDimensionalArray(double[][] data) {
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				System.out.print(data[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	public static String arrayToString(double[][] data) {
		StringBuffer stringBuffer = new StringBuffer();
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j< data[0].length; j++) {
				if((i+j) != (data.length+data[0].length - 2)) {
					stringBuffer.append(data[i][j]+",");
				}else {
					stringBuffer.append(data[i][j]);
				}
			}
		}
		return stringBuffer.toString();
	}

}
