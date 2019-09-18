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

public class getUserImage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getUserImage() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
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

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html; charset=utf-8");
		String username = request.getParameter("username");
		PrintWriter out=response.getWriter();
		UserinfoDAO ud=new UserinfoDAO();
		Userinfo userinfo=new Userinfo();
		String url,temp;
		if(ud.isExisted(username)){
			userinfo=ud.getUser(username);
		    temp=userinfo.getPhotoUrl();
		    url=temp.substring(temp.lastIndexOf("/")+1);
		    url="http://47.94.250.130:8080/images/"+url;
        	 JSONObject object=new JSONObject();
        	 object.put("msg","user existed");
        	 object.put("state","400");
        	 object.put("url", url);
 	         out.print(object.toJSONString());
	        out.flush();
	        out.close();
		}
		else
		{
       	 JSONObject object=new JSONObject();
       	 object.put("msg","user not existed");
       	 object.put("state","-1");
	         out.print(object.toJSONString());
	        out.flush();
	        out.close();
		}
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
