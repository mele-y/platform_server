package forAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import forDAO.UserinfoDAO;

import vo.Userinfo;

public class login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public login() {
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

                 doPost(request,response);
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
		PrintWriter out = response.getWriter();
		/*从客户端接收用户名和密码，在数据库中进行检索， 成功：返回400+success
		 *                                            失败：返回300+账户不存在
		 *                                                  返回200+密码错误
		 *                                                
		 */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Userinfo u = new Userinfo();
		u.setUsername(username);
		u.setPassword(password);
		
		UserinfoDAO p=new UserinfoDAO();
		int status=p.findUserByUserName(u);
		
		if(status==400){
			JSONObject object=new JSONObject();
			object.put("msg","login success");
			object.put("state","400");
			out.print(object.toJSONString());
			out.flush();
			out.close();
		}else if(status==300){
			JSONObject object=new JSONObject();
			object.put("msg","user not exist");
			object.put("state","300");
			out.print(object.toJSONString());
			out.flush();
			out.close();
		}else if(status==200){
			JSONObject object=new JSONObject();
			object.put("msg","pwderror");
			object.put("state","200");
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