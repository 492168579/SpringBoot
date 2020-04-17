package com.yzy.could.utils;

import java.math.BigDecimal;

/**
 * 
 * @author Administrator
 *
 */
public class DecimalCalculate {
	// 默认除法运算精度
	public static final int DEF_DIV_SCALE = 2;

	// 这个类不能实例化
	private DecimalCalculate() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * 多个相加
	 * @param v
	 * @return
	 */
	public static BigDecimal add(String... v) {
		if(v==null||v.length==0){
			return new BigDecimal(0);
		}
		BigDecimal sum=new BigDecimal(0);
		for(String  s:v){
			sum=sum.add(new BigDecimal(s));
		}
		return sum;
	}
	
	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	public static double sub(String v1, String v2) {
		BigDecimal b1 = new BigDecimal( v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}
	public static BigDecimal sub2(String v1, String v2) {
		BigDecimal b1 = new BigDecimal( v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	public static double mul(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1  被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static BigDecimal div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}
	public static BigDecimal div(String v1, String v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static BigDecimal div(double v1, double v2, int scale) {
		v2=v2==0?1:v2;
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_DOWN);
	}
	public static BigDecimal div(double v1, double v2, int scale,int round) {
		v2=v2==0?1:v2;
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, round);
	}
	public static BigDecimal divRoundDown(String v1, String v2, int scale) {
		v2="0".equals(v2)?"1":v2;
		if (scale < 0) {
			throw new IllegalArgumentException( "The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		//return 
		BigDecimal result = b1.divide(b2, scale, BigDecimal.ROUND_DOWN);
		return result;
	}
	public static BigDecimal div(String v1, String v2, int scale) {
		v2="0".equals(v2)?"1":v2;
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		
		
		
		//return 
		BigDecimal result = b1.divide(b2, scale, BigDecimal.ROUND_CEILING);
		if((result.doubleValue()==1.00)&&(b1.compareTo(b2)!=0)){
			BigDecimal b3 = new BigDecimal("0");
			return b3;
		}else	
		return result;
	}
	/**
	public static void main(String[] args) {
		BigDecimal chen =	div("12340","12345",4);
		double chen1 =chen.doubleValue();
		System.out.println(chen1);
		BigDecimal ch =new BigDecimal("0.99");
		double chen2 =ch.doubleValue();
		System.out.println(chen2);
	}
**/
	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 提供精确的类型转换(Float)
	 * 
	 * @param v
	 *            需要被转换的数字
	 * @return 返回转换结果
	 */
	public static float convertsToFloat(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.floatValue();
	}
	

	/**
	 * 提供精确的类型转换(Int)不进行四舍五入
	 * 
	 * @param v
	 *            需要被转换的数字
	 * @return 返回转换结果
	 */
	public static int convertsToInt(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.intValue();
	}

	/**
	 * 提供精确的类型转换(Long)
	 * 
	 * @param v
	 *            需要被转换的数字
	 * @return 返回转换结果
	 */
	public static long convertsToLong(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.longValue();
	}

	/**
	 * 返回两个数中大的一个值
	 * 
	 * @param v1
	 *            需要被对比的第一个数
	 * @param v2
	 *            需要被对比的第二个数
	 * @return 返回两个数中大的一个值
	 */
	public static double returnMax(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.max(b2).doubleValue();
	}

	/**
	 * 返回两个数中小的一个值
	 * 
	 * @param v1
	 *            需要被对比的第一个数
	 * @param v2
	 *            需要被对比的第二个数
	 * @return 返回两个数中小的一个值
	 */
	public static double returnMin(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.min(b2).doubleValue();
	}

	/**
	 * 精确对比两个数字
	 * 
	 * @param v1
	 *            需要被对比的第一个数
	 * @param v2
	 *            需要被对比的第二个数
	 * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
	 */
	public static int compareTo(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.compareTo(b2);
	}
}
