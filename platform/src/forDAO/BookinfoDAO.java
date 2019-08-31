package forDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import vo.Bookinfo;

public class BookinfoDAO {
	private SessionFactory sf;
	private  Session session;

   public void openSession(){
	   Configuration config = new Configuration();
       config.configure();
       ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
       SessionFactory sf = config.buildSessionFactory(serviceRegistry);
	   session=sf.openSession();
	   session.beginTransaction();
	   // session=HibernateSessionFactory.getSession();
	  // ts=session.beginTransaction();
	  // System.out.println("session is opened");
   } 
   public void closeSession(boolean modify){
		if(modify==true) session.getTransaction().commit();
		else session.getTransaction().rollback();
	   session.close();
	   //sf.close();
   }
   public boolean addNewBook(Bookinfo bookinfo)
   {   String bookname=bookinfo.getBookname();
       openSession();
       Bookinfo p=(Bookinfo)session.get(Bookinfo.class,bookname);
       if(p==null)
  	   {session.save(bookinfo);
         
         closeSession(true);
         return true;
  	   }
     else
     {
  	   closeSession(false);
  	   return false;
     }
	  
   }
   public List<Bookinfo> queryPage(StringBuilder sql,int pageNow,int pageSize)
   {
   	openSession();
   	Query query=session.createQuery(sql.toString());//sql为查询语句
       
       query.setFirstResult((pageNow-1)*pageSize);
       query.setMaxResults(pageSize);
       List<Bookinfo> list=query.list();
       closeSession(false);
       return list;
   }
   public int PageCount(StringBuilder sql,int pageSize)
   {
	   openSession();
	   Query query=session.createQuery(sql.toString());
	   	List<Bookinfo> list=query.list();
	   	closeSession(false);
	   	return list.size()/pageSize;
   }
   public List<Bookinfo> getAllBook()
   {
  	 openSession();
  	 Query query=session.createQuery("from Bookinfo");
  	 List<Bookinfo> list=query.list();
  	 closeSession(false);
  	 return list;	 
   }
   public static void main(String[] args)
   {
	   BookinfoDAO bd=new BookinfoDAO();
	   String item="人民出版社";
		StringBuilder sql=new StringBuilder("from Bookinfo where 1=1");
        sql.append(" and ( uper like '%"+item+"%'");
        sql.append(" or bookname like '%"+item+"%'");
        sql.append(" or author like '%"+item+"%'");
        sql.append(" or publication like '%"+item+"%' )");
	   List<Bookinfo> list=bd.queryPage(sql,1,5);
	   for(int i=0;i<list.size();i++)
	   {
		   System.out.println(list.get(i).getBookname()+" "+list.get(i).getAuthor());
	   }
   }
}
