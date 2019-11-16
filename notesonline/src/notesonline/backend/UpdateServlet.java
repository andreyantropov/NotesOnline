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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	/*��������� ���� ��� ����� ���������/������� �� �������� update.jsp
	 * � �������������� ���� ������������
	 * ���� �� ������� ������� ������ ������� �� �� - �������������� ������������
	 * �� �������� 404*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try 
		{
            int id = Integer.parseInt(request.getParameter("id"));
            Note note = NoteDB.selectOne(id);
            if(note != null) {
                request.setAttribute("note", note);
                getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
            }
            else 
            {
                getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
            }
        }
        catch(Exception ex) 
		{
            getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
        }
	}

	/*��������� ������� � �������������� ������������ � IndexServlet*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try 
		{
			int id = Integer.parseInt(request.getParameter("id"));
            String header = request.getParameter("updatedHeader");
            String note = request.getParameter("updatedNote");
            Note updatedNote = new Note(id, header, note);
            NoteDB.update(updatedNote);
            response.sendRedirect(request.getContextPath() + "/index");
        }
        catch(Exception ex) 
		{
            getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);   
        }
	}

}
