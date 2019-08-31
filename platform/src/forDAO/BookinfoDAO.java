package forDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import vo.Bookinfo;
import vo.Userinfo;

public class BookinfoDAO {

	private SessionFactory sf;
	private Session session;
	
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


	  public boolean addBook(Bookinfo bookinfo)
	  {    String bookname=bookinfo.getBookname();    //ʹ��bookname������
	       openSession();
	       Bookinfo bs=(Bookinfo)session.get(Bookinfo.class, bookname);
	      if(bs==null)
	   	   {
	    	  session.save(bookinfo); 
	          closeSession(true);
	          return true;
	   	   }
	      else
	      {
	   	   closeSession(false);
	   	   return false;
	      }
		}
	  
}
