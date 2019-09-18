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

import com.alibaba.fastjson.JSONObject;

import vo.Bookinfo;

import forDAO.BookinfoDAO;


public class upLoadFile extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public upLoadFile() {
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

		   request.setCharacterEncoding("UTF-8");		
           response.setContentType("text/html;charset=utf-8");
           response.setCharacterEncoding("UTF-8");
		 PrintWriter outprint = response.getWriter();
		 String savePath="/www/server/tomcat/upload";
		 //String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);
		if (!file.exists()) {
			System.out.println(savePath+"目录不存在，需要创建");
			 file.mkdir();
		}

		
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); 
			String uper = null,author=null,publication=null,uptime=null,uri=null,filename=null,bookname=null;
			 if(!ServletFileUpload.isMultipartContent(request)){
				 return;
			 }

			 List<FileItem> list = upload.parseRequest(request);
			 for(FileItem item : list){
				 if(item.isFormField()){
					 String name = item.getFieldName();
					 String value1 = item.getString("UTF-8");
					 if(name.equals("uper")){uper=value1;}
					 else if(name.equals("author")){author=value1;}
					 else if(name.equals("publication")){publication=value1;}
					 else if(name.equals("uptime")){uptime=value1;}
					 else if(name.equals("bookname")){bookname=value1;}
			     }else{
				    filename = item.getName();
				    filename=URLDecoder.decode(filename,"UTF-8");
				    //outprint.println(filename);
				    //filename = new String(filename.getBytes("iso-8859-1"),"UTF-8");
				    if(filename==null || filename.trim().equals("")){
					   continue;
				     }
				     filename = bookname+"."+filename.substring(filename.lastIndexOf(".")+1);
				     //filename=filename.substring(filename.lastIndexOf(".")+1);
				     //outprint.println(filename);
				     BookinfoDAO bd=new BookinfoDAO();
				     if(bd.isExisted(filename))
				     {continue;}
				     InputStream in = item.getInputStream();
				     uri=savePath+"/"+filename;
				     //outprint.println(uri);
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
			 BookinfoDAO bd=new BookinfoDAO();
             boolean flag=bd.addNewBook(new Bookinfo(filename,uper,uptime,author,publication,uri));
             if(flag)
             {
            	 JSONObject object=new JSONObject();
            	 object.put("msg","upload success");
            	 object.put("state","400");
     	         outprint.print(object.toJSONString());
    	        outprint.flush();
    	        outprint.close();
             }
             else
             {
            	 JSONObject object=new JSONObject();
            	 object.put("msg","file existed");
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
