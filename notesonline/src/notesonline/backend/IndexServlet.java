package notesonline.backend;

import java.io.IOException;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notesonline.backend.Note;
import notesonline.backend.NoteDB;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	/*¬ыводим все хран€щиес€ в Ѕƒ заметки на страницу index.jsp и
	 * перенаправл€ем туда пользовател€*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		ArrayList<Note> notes = NoteDB.selectAll();
        request.setAttribute("notes", notes);
        
        HttpSession session = request.getSession();
        String insertSearch = (String)session.getAttribute("search");
        request.setAttribute("search", insertSearch);
          
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	/*¬ыводим на страницу index.jsp все заметки, удовлетвор€ющие поисковому запросу
	 * и перенаправл€ем туда пользовател€*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		ArrayList<Note> notes = NoteDB.search(request.getParameter("search"));
        request.setAttribute("notes", notes);
        
        HttpSession session = request.getSession();
        session.setAttribute("search", request.getParameter("search"));
          
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
