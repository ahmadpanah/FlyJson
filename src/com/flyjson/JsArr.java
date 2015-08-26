
package com.flyjson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.flyjson.syntax.Values;

/**
 * JSON Array.
 *
 */
public class JsArr {

	private List<Object> list = new ArrayList<Object>();
	
	public JsArr(){}
	
	/**
	 * add element
	 * @param e
	 * @return JsArr
	 */
	public JsArr add(Object e) {
		list.add(e);
		return this;
	}
	
	/**
	 * add elements
	 * @param list
	 * @return JsArr
	 */
	public JsArr addAll(List<Object> list) {
		Collections.addAll(list, list.toArray());
		return this;
	}
	
	/**
	 * add element in the specific index
	 * @param i
	 * @param e
	 * @return JsArr
	 */
	public JsArr add(int i,Object e) {
		list.add(i, e);
		return this;
	}
	
	/**
	 * remove element
	 * @param e
	 * @return JsArr
	 */
	public JsArr remove(Object e) {
		list.remove(e);
		return this;
	}
	
	/**
	 * remove element
	 * @param i
	 * @return JsArr
	 */
	public JsArr remove(int i) {
		list.remove(i);
		return this;
	}
	
	/**
	 * clear array
	 * @return
	 */
	public JsArr clear() {
		list.clear();
		return this;
	}
	
	/**
	 * get element by index
	 * @param i
	 * @return Object
	 */
	public Object get(int i) {
		return list.get(i);
	}
	
	/**
	 * list element
	 * @return a list of Objects
	 */
	public List<Object> list() {
		return list;
	}
	
	/**
	 * if the JSON array is empty
	 * @return true if the JSON array is empty.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * to JSON String
	 */
	@Override
	public String toString() {
		if(list==null||list.isEmpty()){
			return "null";
		}else {
			Values vals = new Values().setQuote("").setSeperator(",");
			for(Object e:list){
				if(JsTypeUtil.needQuote(e)){
					vals.add("\""+e+"\"");
				}else {
					vals.add(e);
				}
			}
			return "["+vals+"]";
		}
	}

	/**
	 * Parse JsArr from JSON array String.
	 * @param jsArr
	 * @return JsArr
	 */
	public static JsArr parse(String jsArr) {
		return JsParser.parseJsArr(jsArr);
	}
	
}
