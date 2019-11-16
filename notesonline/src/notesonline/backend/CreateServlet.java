package notesonline.backend;

import java.io.IOException;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notesonline.backend.Note;
import notesonline.backend.NoteDB;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	/*Отправляем пользователя на страницу create.jsp*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
	}

	/*Сохраняем новую заметку в БД и перенаправляем пользователя в IndexServlet*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try
		{
			String header = request.getParameter("newHeader");
			String note = request.getParameter("newNote");
			Note newNote = new Note(header, note);
			NoteDB.insert(newNote);
			response.sendRedirect(request.getContextPath() + "/index");
		}
		catch(Exception ex) 
		{
            getServletContext().getRequestDispatcher("/create.jsp").forward(request, response); 
        }
	}
}
