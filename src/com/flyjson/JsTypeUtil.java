package com.flyjson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JsTypeUtil.
 *
 */
public class JsTypeUtil {

	/**
	 * if the Object represents a Boolean value
	 * @param e
	 * @return
	 */
	static boolean isBool(Object e) {
		if(e instanceof String){
			return Boolean.valueOf(e.toString());
		}
		return (e instanceof Boolean);
	}
	
	/**
	 * if the Object represents an Integer value
	 * @param e
	 * @return
	 */
	static boolean isInt(Object e) {
		if(e instanceof Integer){
			return true;
		}
		if(e instanceof String){
			try {
				Integer.parseInt(e.toString());
				return true;
			} catch (Exception ex) {}
		}
		return false;
	}
	
	/**
	 * if the Object represents a Long value
	 * @param e
	 * @return
	 */
	static boolean isLong(Object e) {
		if(e instanceof Long){
			return true;
		}
		if(e instanceof String){
			try {
				Long.parseLong(e.toString());
				return true;
			} catch (Exception ex) {}
		}
		return false;
	}
	
	/**
	 * if the Object represents a Float value
	 * @param e
	 * @return
	 */
	static boolean isFloat(Object e) {
		if(e instanceof Float){
			return true;
		}
		if(e instanceof String){
			try {
				Float.parseFloat(e.toString());
				return true;
			} catch (Exception ex) {}
		}
		return false;
	}
	
	/**
	 * if the Object represents a Double value
	 * @param e
	 * @return
	 */
	static boolean isDouble(Object e) {
		if(e instanceof Double){
			return true;
		}
		if(e instanceof String){
			try {
				Double.parseDouble(e.toString());
				return true;
			} catch (Exception ex) {}
		}
		return false;
	}
	
	/**
	 * if the Object represents a JsObj
	 * @param e
	 * @return
	 */
	static boolean isJsObj(Object e) {
		if(e instanceof JsObj)
			return true;
		if(e instanceof String){
			String s = JsParser.trim(e.toString());
			Pattern p = Pattern.compile("^\\{.+\\}$");
	        Matcher m = p.matcher(s);
	        if(m.matches())
	        	return true;
	        else
	        	return false;
		}
		return false;
	}
	
	/**
	 * if the Object represents a JsArr
	 * @param e
	 * @return
	 */
	static boolean isJsArr(Object e) {
		if(e instanceof JsArr)
			return true;
		if(e instanceof String){
			String s = JsParser.trim(e.toString());
			Pattern p = Pattern.compile("^\\[.+\\]$");
	        Matcher m = p.matcher(s);
	        if(m.matches())
	        	return true;
	        else
	        	return false;
		}
		return false;
	}

	/**
	 * if the Object represents a String value,that is to say,it needs quotes.
	 * @param e
	 * @return
	 */
	static boolean needQuote(Object e) {
		if(e==null)
			return false;
		if(isBool(e)||isInt(e)||isLong(e)||isFloat(e)||isDouble(e)||isJsObj(e)||isJsArr(e))
			return false;
		return true;
	}
	
	/**
	 * convert String s to the given type.
	 * @param type
	 * @param s
	 * @return
	 */
	static Object convertType(Class<?> type,String s) {
		if(s==null){
			return null;
		}
			
		if (type==int.class||type==Integer.class){
			return Integer.parseInt(s);
		}else if (type==long.class||type==Long.class) {
			return Long.parseLong(s);
		}else if (type==float.class||type==Float.class) {
			return Float.parseFloat(s);
		}else if (type==double.class||type==Double.class) {
			return Double.parseDouble(s);
		}else if (type==boolean.class||type==Boolean.class) {
			return Boolean.parseBoolean(s);
		}else if (type==JsObj.class) {
			return JsParser.parseJsObj(s);
		}else if (type==JsArr.class) {
			return JsParser.parseJsArr(s);
		}
		
		return s;

	}
	
}
