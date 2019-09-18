package forAction;

public class StringTest {
  public static void main(String[] args)
  {
	  String s="internal://tmp/e7844e36-8624-447b-a3be-38605b029cd9/Img_20190903_153241.jpg";
	  s=s.substring(s.lastIndexOf(".")+1);
	  System.out.print(s);
  }
}
