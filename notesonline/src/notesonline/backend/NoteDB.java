package notesonline.backend;

import java.sql.*;
import java.util.ArrayList;

/*NoteDB - определяет основные операции с БД*/
public class NoteDB 
{
	private static String url = "jdbc:mysql://localhost/notes?serverTimezone=Europe/Moscow&useSSL=false";
	private static String login = "root";
	private static String password = "root";
	
	/*Получаем все записи из БД*/
	public static ArrayList<Note> selectAll()
	{
		ArrayList<Note> notes = new ArrayList<Note>();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try(Connection conn = DriverManager.getConnection(url, login, password))
			{
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery("SELECT * FROM notes");
				while(result.next())
				{
					int id = result.getInt(1);
					String header = result.getString(2);
					String note = result.getString(3);
					if(header.length() == 0)
					{
						if(note.length() < 100)
							header = note;
						else
							header = note.substring(0, 99);
					}
					Note selectedNote = new Note(id, header, note);
					notes.add(selectedNote);
				}
			}
		}
		catch(Exception ex){
            System.out.println(ex);
        }
		return notes;
	}
	
	/*Получаем один элемент из таблицы по id
	 * Если объект в бд не найден, то возвращается null*/
	public static Note selectOne(int id)
	{
		Note selectedNote = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try(Connection conn = DriverManager.getConnection(url, login, password))
			{
				String query = "SELECT * FROM notes WHERE id = ?";
				try(PreparedStatement statement = conn.prepareStatement(query))
				{
					statement.setInt(1, id);
					ResultSet result = statement.executeQuery();
					if(result.next())
					{
						int noteId = result.getInt(1);
						String header = result.getString(2);
						String note = result.getString(3);
						selectedNote = new Note(noteId, header, note);
					}
				}
			}
		}
		catch(Exception ex){
            System.out.println(ex);
        }
		return selectedNote;
	}
	
	/*Получаем из БД объекты, содержащие query*/
	public static ArrayList<Note> search(String searchQuery)
	{
		ArrayList<Note> notes = new ArrayList<Note>();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try(Connection conn = DriverManager.getConnection(url, login, password))
			{
				String query = "SELECT * FROM notes WHERE (header LIKE ? OR note LIKE ?)";
				try(PreparedStatement statement = conn.prepareStatement(query))
				{
					statement.setString(1, "%" + searchQuery + "%");
					statement.setString(2, "%" + searchQuery + "%");
					ResultSet result = statement.executeQuery();
					while(result.next())
					{
						int id = result.getInt(1);
						String header = result.getString(2);
						String note = result.getString(3);
						if(header.length() == 0)
						{
							if(note.length() < 100)
								header = note;
							else
								header = note.substring(0, 99);
						}
						Note selectedNote = new Note(id, header, note);
						notes.add(selectedNote);
					}
				}
			}
		}
		catch(Exception ex){
            System.out.println(ex);
        }
		return notes;
	}
	
	/*Обновляем в таблице один объект Note и возвращаем количество обновленных строк*/
	public static int update(Note updatedNote)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try(Connection conn = DriverManager.getConnection(url, login, password))
			{
				String query = "UPDATE notes SET header = ?, note = ? WHERE id = ?";
				try(PreparedStatement statement = conn.prepareStatement(query))
				{
					statement.setString(1, updatedNote.getHeader());
					statement.setString(2, updatedNote.getNote());
					statement.setInt(3, updatedNote.getId());
					return statement.executeUpdate();
				}
			}
		}
		catch(Exception ex){
            System.out.println(ex);
        }
		return 0;
	}
	
	/*Добавляем в таблицу один объект Note и возвращаем количество добавленных строк*/
	public static int insert(Note newNote)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try(Connection conn = DriverManager.getConnection(url, login, password))
			{
				String query = "INSERT INTO notes (header, note) VALUES (?, ?)";
				try(PreparedStatement statement = conn.prepareStatement(query))
				{
					statement.setString(1, newNote.getHeader());
					statement.setString(2, newNote.getNote());
					return statement.executeUpdate();
				}
			}
		}
		catch(Exception ex){
            System.out.println(ex);
        }
		return 0;
	}
	
	/*Удаляем один объект Note и возвращаем количество удаленных строк*/
	public static int delete(int id)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try(Connection conn = DriverManager.getConnection(url, login, password))
			{
				String query = "DELETE FROM notes WHERE id = ?";
				try(PreparedStatement statement = conn.prepareStatement(query))
				{
					statement.setInt(1, id);
					return statement.executeUpdate();
				}
			}
		}
		catch(Exception ex){
            System.out.println(ex);
        }
		return 0;
	}
}
