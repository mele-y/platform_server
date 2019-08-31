package vo;

/**
 * Bookinfo entity. @author MyEclipse Persistence Tools
 */

public class Bookinfo implements java.io.Serializable {

	// Fields

	private String bookname;
	private String uper;
	private String uptime;
	private String author;
	private String publication;
	private String uri;

	// Constructors

	/** default constructor */
	public Bookinfo() {
	}

	/** full constructor */
	public Bookinfo(String bookname, String uper, String uptime, String author,
			String publication, String uri) {
		this.bookname = bookname;
		this.uper = uper;
		this.uptime = uptime;
		this.author = author;
		this.publication = publication;
		this.uri = uri;
	}

	// Property accessors

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getUper() {
		return this.uper;
	}

	public void setUper(String uper) {
		this.uper = uper;
	}

	public String getUptime() {
		return this.uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return this.publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}