package forAction;
import vo.Bookinfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import forDAO.BookinfoDAO;
public class getBook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getBook() {
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

		   request.setCharacterEncoding("UTF-8");		
           response.setContentType("text/html;charset=utf-8");
           response.setCharacterEncoding("UTF-8");
           PrintWriter out = response.getWriter();
		   String item=request.getParameter("item");
		   String item2=item;
		   byte source [] = item.getBytes("iso8859-1");
		   item=new String(source,"UTF-8");
		   int index=Integer.parseInt(request.getParameter("index"));
		   int pageSize=10,pageCount;
			StringBuilder sql=new StringBuilder("from Bookinfo where 1=1");
	        sql.append(" and ( uper like '%"+item2+"%'");
	        sql.append(" or bookname like '%"+item2+"%'");
	        sql.append(" or author like '%"+item2+"%'");
	        sql.append(" or publication like '%"+item2+"%' )");
	        BookinfoDAO bd=new BookinfoDAO();
	        List<Bookinfo> list=bd.queryPage(sql, index, pageSize);
	        JSONObject object=new JSONObject();
	        if(list.size()!=0)
	        {
	        pageCount=bd.PageCount(sql, pageSize)+1;
	        object.put("pageCount", pageCount);
	        object.put("pageSize", pageSize);
	        object.put("item", item2);
	        object.put("pageNow", index);
	        object.put("bookData", list);
	        object.put("state","400");
	        out.print(object.toJSONString());
	        out.flush();
	        out.close();
	        }
	        else
	        {
	        	object.put("state","500");
	        	object.put("item", item);
	        	object.put("item2", item2);
	        	object.put("index", index);
	        	out.print(object.toJSONString());
		        out.flush();
		        out.close();
	        }
	        	
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
        doGet(request,response);
	        	
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
