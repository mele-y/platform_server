package forAction;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import vo.Bookinfo;
import vo.Userinfo;

import com.alibaba.fastjson.JSONObject;

import forDAO.BookinfoDAO;
import forDAO.UserinfoDAO;

public class photoRegister extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public photoRegister() {
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

		   request.setCharacterEncoding("UTF-8");		
           response.setContentType("text/html;charset=utf-8");
           response.setCharacterEncoding("UTF-8");
		 PrintWriter outprint = response.getWriter();
		 String savePath="/www/server/tomcat/image";
		//String savePath = this.getServletContext().getRealPath("/WEB-INF/photo");
		File file = new File(savePath);
		if (!file.exists()) {
			System.out.println(savePath+"目录不存在，需要创建");
			 file.mkdir();
		}
		
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); 
			String username = null,password=null,photourl=null,filename=null;
			 if(!ServletFileUpload.isMultipartContent(request)){
				 return;
			 }
			 List<FileItem> list = upload.parseRequest(request);
			 for(FileItem item : list){
				 if(item.isFormField()){
					 String name = item.getFieldName();
					 String value1 = item.getString("UTF-8");
					 if(name.equals("username")){username=value1;}
					 else if(name.equals("password")){password=value1;}
					//outprint.println(username+password);
			     }else{
				    filename = item.getName();
				    filename=URLDecoder.decode(filename,"UTF-8");
				     //filename = new String(filename.getBytes("iso-8859-1"),"UTF-8");
				    if(filename==null || filename.trim().equals("")){
					   continue;
				     }
				     filename = username+"."+filename.substring(filename.lastIndexOf(".")+1);
				     //outprint.println(filename);
				     photourl=savePath+"/"+filename;
				     //outprint.println(photourl);
				     UserinfoDAO ud=new UserinfoDAO();
				     //outprint.print(ud.isExisted(username));
				     if(ud.isExisted(username))
				     {continue;}
				     InputStream in = item.getInputStream();
				     FileOutputStream out = new FileOutputStream(savePath + "/" + filename);
				     byte buffer[] = new byte[1024];
				     int len = 0;
				     while((len=in.read(buffer))>0){
				    	 out.write(buffer, 0, len);
				        }
				     in.close();
				     out.close();
				     item.delete();
			       } 
		   }
			 UserinfoDAO ud=new UserinfoDAO();
             boolean flag=ud.addUser(new Userinfo(username,password,photourl));
             //outprint.print(flag);
             if(flag)
             {
            	 JSONObject object=new JSONObject();
            	 object.put("msg","register success");
            	 object.put("state","400");
     	         outprint.print(object.toJSONString());
    	        outprint.flush();
    	        outprint.close();
             }
             else
             {
            	 JSONObject object=new JSONObject();
            	 object.put("msg","user existed");
            	 object.put("state","-1");
     	         outprint.print(object.toJSONString());
    	        outprint.flush();
    	        outprint.close();
             }
	
		}catch(Exception e)
		{     
				e.printStackTrace();
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
