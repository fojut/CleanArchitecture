package org.fojut.sample.presentation.base.util;

/**
 * 正则表达式工具类，提供一些常用的正则表达式
 */
public class RegexUtils {

	public RegexUtils() {
		throw new AssertionError();
	}

	/**
	 * 匹配全网IP的正则表达式
	 */
	public static final String IP_REGEX = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$";
	
	/**
	 * 匹配手机号码的正则表达式
	 * <br>支持130——139、150——153、155——159、180、183、185、186、188、189号段
	 */
	public static final String PHONE_NUMBER_REGEX = "^1{1}(3{1}\\d{1}|5{1}[012356789]{1}|8{1}[035689]{1})\\d{8}$";
	
	/**
	 * 匹配邮箱的正则表达式
	 * <br>"www."可省略不写
	 */
	public static final String EMAIL_REGEX = "^(www\\.)?\\w+@\\w+(\\.\\w+)+$";
	
	/**
	 * 匹配汉子的正则表达式，个数限制为一个或多个
	 */
	public static final String CHINESE_REGEX = "^[\u4e00-\u9f5a]+$";
	
	/**
	 * 匹配正整数的正则表达式，个数限制为一个或多个
	 */
	public static final String POSITIVE_INTEGER_REGEX = "^\\d+$";
	
	/**
	 * 匹配身份证号的正则表达式
	 */
	public static final String ID_CARD = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";
	
	/**
	 * 匹配邮编的正则表达式
	 */
	public static final String ZIP_CODE = "^\\d{6}$";
	
	/**
	 * 匹配URL的正则表达式
	 */
	public static final String URL = "^(([hH][tT]{2}[pP][sS]?)|([fF][tT][pP]))\\:\\/\\/[wW]{3}\\.[\\w-]+\\.\\w{2,4}(\\/.*)?$";
	
	/**
	 * 匹配给定的字符串是否是一个邮箱账号，"www."可省略不写
	 * @param string 给定的字符串
	 * @return true：是
	 */
	public static boolean isEmail(String string){
		return string.matches(EMAIL_REGEX);
	}
	
	/**
	 * 匹配给定的字符串是否是一个手机号码，支持130——139、150——153、155——159、180、183、185、186、188、189号段
	 * @param string 给定的字符串
	 * @return true：是
	 */
	public static boolean isMobilePhoneNumber(String string){
		return string.matches(PHONE_NUMBER_REGEX);
	}
	
	/**
	 * 匹配给定的字符串是否是一个全网IP
	 * @param string 给定的字符串
	 * @return true：是
	 */
	public static boolean isIp(String string){
		return string.matches(IP_REGEX);
	}
	
	/**
	 * 匹配给定的字符串是否全部由汉子组成
	 * @param string 给定的字符串
	 * @return true：是
	 */
	public static boolean isChinese(String string){
		return string.matches(CHINESE_REGEX);
	}
	
	/**
	 * 验证给定的字符串是否全部由正整数组成
	 * @param string 给定的字符串
	 * @return true：是
	 */
	public static boolean isPositiveInteger(String string){
		return string.matches(POSITIVE_INTEGER_REGEX);
	}
	
	/**
	 * 验证给定的字符串是否是身份证号
	 * <br>
	 * <br>身份证15位编码规则：dddddd yymmdd xx p 
	 * <br>dddddd：6位地区编码 
	 * <br>yymmdd：出生年(两位年)月日，如：910215 
	 * <br>xx：顺序编码，系统产生，无法确定 
	 * <br>p：性别，奇数为男，偶数为女
	 * <br>
	 * <br>
	 * <br>身份证18位编码规则：dddddd yyyymmdd xxx y 
	 * <br>dddddd：6位地区编码 
	 * <br>yyyymmdd：出生年(四位年)月日，如：19910215 
	 * <br>xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女 
	 * <br>y：校验码，该位数值可通过前17位计算获得
	 * <br>前17位号码加权因子为 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
	 * <br>验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
	 * <br>如果验证码恰好是10，为了保证身份证是十八位，那么第十八位将用X来代替 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
	 * <br>i为身份证号码1...17 位; Y_P为校验码Y所在校验码数组位置
	 * @param string
	 * @return
	 */
	public static boolean isIdCard(String string){
		return string.matches(ID_CARD);
	}
	
	/**
	 * 验证给定的字符串是否是邮编
	 * @param string
	 * @return
	 */
	public static boolean isZipCode(String string){
		return string.matches(ZIP_CODE);
	}
	
	/**
	 * 验证给定的字符串是否是URL，仅支持http、https、ftp
	 * @param string
	 * @return
	 */
	public static boolean isURL(String string){
		return string.matches(URL);
	}
}
