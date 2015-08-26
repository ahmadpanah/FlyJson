package com.flyjson.test;

import com.flyjson.JsArr;
import com.flyjson.JsObj;

/**
 * Test Class
 *
 */
public class Test {

	public static void main(String[] args) {
		
	    String json = "{\"k1\":\"\\/v1\",\"k2\":false,\"k3\":1234,\"k4\":123.4,\"k5\":12.34}";
	    
	    JsObj jsObj = JsObj.parse(json);
	    System.out.println(jsObj);
	    
	    json = "[{\"k1\":\"v1\",\"k2\":\"v2\",\"k3\":\"v3\",\"k4\":\"v4\",\"k5\":\"v5\"}]";
	    
		JsArr jsArr = JsArr.parse(json);
		System.out.println(jsArr);
		
		jsObj = jsObj.clear().put("username", "hossein").put("password", "123456789");
		System.out.println(jsObj);
		
		jsArr = jsArr.clear().add(jsObj).add(jsObj);
		System.out.println(jsArr);
	}

}
