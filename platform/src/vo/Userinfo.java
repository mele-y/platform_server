package vo;



/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo  implements java.io.Serializable {


    // Fields    

     private String username;
     private String password;


    // Constructors

    /** default constructor */
    public Userinfo() {
    }

    
    /** full constructor */
    public Userinfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

   
    // Property accessors

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
   








}