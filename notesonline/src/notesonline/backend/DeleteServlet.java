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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	/*������� ��������� ������� �� �� � �������������� ������������ � IndexServlet
	 * ���� ������� ������� �� ������� - �������������� ������������ �� �������� 404*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try
		{
			int id = Integer.parseInt(request.getParameter("id"));
			NoteDB.delete(id);
			response.sendRedirect(request.getContextPath() + "/index");
		}
		catch(Exception ex) 
		{
            getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
        }
	}
}
