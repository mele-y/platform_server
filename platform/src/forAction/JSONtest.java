package forAction;

import com.alibaba.fastjson.JSONObject;

public class JSONtest {
     public static void main(String[] args)
     {
 		JSONObject object=new JSONObject();
 		object.put("msg","fail");
 		object.put("state","400");
    	 System.out.println(object.toJSONString());
     }
}
