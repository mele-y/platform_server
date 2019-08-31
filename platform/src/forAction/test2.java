package forAction;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import vo.Userinfo;
import forDAO.UserinfoDAO;

public class test2 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public test2() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");		
        response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject object=new JSONObject();
		object.put("msg","fail");
		object.put("state","400");
		out.print(object.toJSONString());
		out.close();
       
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");		
        response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject object=new JSONObject();
		object.put("msg","fail");
		object.put("state","400");
		out.print(object.toJSONString());

		out.close();

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}