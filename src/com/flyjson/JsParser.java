package com.flyjson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JsParser parses JsObj or JsArr from JSON String.
 *
 */
public class JsParser {

	/**
	 * remove the blanks in the head or tail
	 * @param s
	 * @return
	 */
	static String trim(String s) {
		if(s==null||s.equals("")){
			return s;
		}else {
			if(s.charAt(0)==' '){
				int i;
				for(i=0;i<s.length();i++){
					if(s.charAt(i)!=' ')
						break;
				}
				s = s.substring(i).trim();
			}else {
				s = s.trim();
			}
			return s;
		}
		
	}
	
	/**
	 * unquote String("s"->s)
	 * @param s
	 * @return
	 */
	static String unquote(String s) {
		if(s==null||s.trim().equals("")){
			return s;
		}else {
			s = trim(s);
			if(s.startsWith("\"")&&s.endsWith("\"")){
				s = s.substring(1, s.lastIndexOf("\""));
			}
			return s;
		}
	}
	
	/**
	 * parse JsObj from the given String
	 */
	static JsObj parseJsObj(String json) {
		
		//remove the blanks in the head or tail
		json = trim(json);
		
		//assert
		if(!JsTypeUtil.isJsObj(json))
			return null;
		
        JsObj jsObj = new JsObj();
        
        //String type(quoted),which represents a String variable.
        Pattern p = Pattern.compile("(\"\\w+\"):(\"[^\"]+\")");
        Matcher m = p.matcher(json);
        while(m.find()) {
        	String[] kv = m.group().split(":");
        	if(kv.length==2){
        		//unquote key
            	String k = unquote(kv[0]);
            	//parse value
            	Object v = parseStrType(kv[1]);
            	//put it to jsObj
        		jsObj.put(k, v);
        	}
        }
        
        //Non-String type(unquoted).It may be Boolean,Integer,Long,Float,Double,JsObj or JsArr.
        p = Pattern.compile("(\"\\w+\"):(true|false)|(\"\\w+\"):([0-9]+)|(\"\\w+\"):([0-9]+\\.?+[0-9])");
        m = p.matcher(json);
        while(m.find()) {
        	String[] kv = m.group().split(":");
        	if(kv.length==2){
        		//unquote key
            	String k = unquote(kv[0]);
            	//parse value
            	Object v = parseNonStrType(kv[1]);
            	//put it to jsObj
        		jsObj.put(k, v);
        	}
        }  
		return jsObj;
	}
	
	/**
	 * parse JsArr from the given String s
	 */
	static JsArr parseJsArr(String json) {
		
		//remove the blanks in the head or tail
		json = trim(json);
		
		//assert
		if(!JsTypeUtil.isJsArr(json))
			return null;
        
        JsArr jsArr = new JsArr();
        
        //JsObj
        Pattern p = Pattern.compile("\\{.+\\}");
        Matcher m = p.matcher(json);
        while (m.find()) {
        	String s = m.group();
			JsObj js = parseJsObj(s);
			jsArr.add(js);
		}
        
        if(!jsArr.isEmpty()){
        	return jsArr;
        }
        
        //JsArr
        String items = json.substring(json.indexOf("["),json.lastIndexOf("]"));
		p = Pattern.compile("\\[.+\\]");
        m = p.matcher(items);
        while(m.find()){
        	String s = m.group();
        	JsArr js = parseJsArr(s);
        	jsArr.add(js);
        }
        
        if(!jsArr.isEmpty()){
        	return jsArr;
        }
        
        //String type(quoted),which represents a String variable.
		p = Pattern.compile("\".+\"");
        m = p.matcher(items);
        while(m.find()){
        	String s = m.group();
        	Object o = parseStrType(s);
        	jsArr.add(o);
        }
        
        if(!jsArr.isEmpty()){
        	return jsArr;
        }
        
        //Non-String type(Boolean,Integer,Long,Float,Double)
		p = Pattern.compile("(true|false)|([0-9]+)|([0-9]+\\.?+[0-9])");
		m = p.matcher(items);
		while(m.find()){
		  	String s = m.group();
		  	Object o = parseNonStrType(s);
		  	jsArr.add(o);
		}
		
		return jsArr;
	}

	//String type(quoted),which represents a String variable.
	protected static Object parseStrType(String s) {
		
		if(s==null){
			return null;
		}
		
		Object o = unquote(s);
		
		return o;
	}
	
	//Non-String type(unquoted).It may be Boolean,Integer,Long,Float,Double,JsObj or JsArr.
	protected static Object parseNonStrType(String s) {
		if(s==null||s.trim().equals("")){
			return null;
		}
		Object o = null;
		if (JsTypeUtil.isBool(s)) {
			o = JsTypeUtil.convertType(Boolean.class, s);
		}else if (JsTypeUtil.isInt(s)) {
			o = JsTypeUtil.convertType(Integer.class, s);
		}else if (JsTypeUtil.isLong(s)) {
			o = JsTypeUtil.convertType(Long.class, s);
		}else if (JsTypeUtil.isFloat(s)) {
			o = JsTypeUtil.convertType(Float.class, s);
		}else if (JsTypeUtil.isDouble(s)) {
			o = JsTypeUtil.convertType(Double.class, s);
		}else if (JsTypeUtil.isJsObj(s)){
			o = parseJsObj(s);
		}else if (JsTypeUtil.isJsArr(s)) {
			o = parseJsArr(s);
		}else {
			o = s;
		}
		return o;
	}
		
}
