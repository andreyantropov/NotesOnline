package notesonline.backend;

import java.io.Serializable;

public class Note implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String header;
	private String note;
	
	public Note(){}
	
	public Note(String header, String note)
	{
		this.header = header;
		this.note = note;
	}
	
	public Note(int id, String header, String note)
	{
		this.id = id;
		this.header = header;
		this.note = note;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setHeader(String header)
	{
		this.header = header;
	}
	
	public String getHeader()
	{
		return this.header;
	}
	
	public void setNote(String note)
	{
		this.note = note;
	}
	
	public String getNote()
	{
		return this.note;
	}
}
