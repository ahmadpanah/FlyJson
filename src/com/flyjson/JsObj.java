package com.flyjson;

import java.util.LinkedHashMap;
import java.util.Map;

import com.flyjson.syntax.KeyValuePairs;

/**
 * JSON Object.
 *
 */
public class JsObj {

	Map<String, Object> map = new LinkedHashMap<String, Object>();
	
	public JsObj() {}

	public JsObj put(String k,Object v) {
		map.put(k, v);
		return this;
	}
	
	public JsObj putAll(Map<String, Object> map) {
		map.putAll(map);
		return this;
	}
	
	public JsObj remove(String k) {
		map.remove(k);
		return this;
	}
	
	public JsObj remove(String k,Object v) {
		map.remove(k, v);
		return this;
	}
	
	public JsObj clear() {
		map.clear();
		return this;
	}
	
	public Object get(String k) {
		return map.get(k);
	}
	
	public Map<String, Object> map() {
		return map;
	}
	
	@Override
	public String toString() {
		if(map==null||map.isEmpty()){
			return "null";
		}
		KeyValuePairs kvps = new KeyValuePairs().setKeyQuote("\"").setEqualSymbol(":").setSeperator(",");
		for(Map.Entry<String, Object> e:map.entrySet()){
			Object v = e.getValue();
			if(JsTypeUtil.needQuote(v)){
				kvps.add(e.getKey(), "\""+v+"\"");
			}else {
				kvps.add(e.getKey(), v);
			}
		}
		return "{"+kvps+"}";
	}
	
	public static JsObj parse(String jsObj) {
		return JsParser.parseJsObj(jsObj);
	}
	
}
