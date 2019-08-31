package vo;

import java.sql.Timestamp;

/**
 * Bookinfo entity. @author MyEclipse Persistence Tools
 */

public class Bookinfo implements java.io.Serializable {

	// Fields

	private String uper;
	private Timestamp uptime;
	private String bookname;
	private String author;
	private String publication;

	// Constructors

	/** default constructor */
	public Bookinfo() {
	}

	/** full constructor */
	public Bookinfo(String uper, Timestamp uptime, String bookname,
			String author, String publication) {
		this.uper = uper;
		this.uptime = uptime;
		this.bookname = bookname;
		this.author = author;
		this.publication = publication;
	}

	// Property accessors

	public String getUper() {
		return this.uper;
	}

	public void setUper(String uper) {
		this.uper = uper;
	}

	public Timestamp getUptime() {
		return this.uptime;
	}

	public void setUptime(Timestamp uptime) {
		this.uptime = uptime;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
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

}