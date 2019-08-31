package forDAO;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


import vo.Userinfo;
public class UserinfoDAO {
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


  public boolean addUser(Userinfo userinfo)
  {    String userid=userinfo.getUsername();
       openSession();
       Userinfo us=(Userinfo)session.get(Userinfo.class, userid);
      if(us==null)
   	   {session.save(userinfo);
          
          closeSession(true);
          return true;
   	   }
      else
      {
   	   closeSession(false);
   	   return false;
      }
	}
  
  public int findUserByUserName(Userinfo userinfo){
	   String username = userinfo.getUsername();
	   String password = userinfo.getPassword();
	   openSession();
	   Userinfo us=(Userinfo)session.get(Userinfo.class, username);
	   if(us==null)
	   {
		   closeSession(false);
		   return 300;
	   }
	   else
	   {
		   if(us.getPassword().equals(password))
		   {
			   closeSession(false);
			   return 400;
			   
		   }
		   else
		   {
			   closeSession(false);
			   return 200;
		   }
	   }
	
 }
}
