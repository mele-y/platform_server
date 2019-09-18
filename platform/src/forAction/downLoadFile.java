package forAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import vo.Bookinfo;

import forDAO.BookinfoDAO;

public class downLoadFile extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public downLoadFile() {
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
		String bookname=request.getParameter("bookname");
		BookinfoDAO bd=new BookinfoDAO();
		boolean flag=bd.isExisted(bookname);
		String url,temp;
		Bookinfo bookinfo=new Bookinfo();
		if(flag){
			bookinfo=bd.getBook(bookname);
			temp=bookinfo.getUri();
			url=temp.substring(temp.lastIndexOf("/")+1);
			url="http://47.94.250.130:8080/upfiles/"+url;
			JSONObject object=new JSONObject();
       	    object.put("msg","file existed");
       	    object.put("state","400");
       	    object.put("url", url);
	        out.print(object.toJSONString());
	        out.flush();
	        out.close();
		} else
		{
			JSONObject object=new JSONObject();
       	    object.put("msg","file  not existed");
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
