package SchoolManagment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateTeacher")
public class TeacherUpdate extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ajay");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String salary = req.getParameter("salary");
		String subject = req.getParameter("subject");

		Teacher t = new Teacher();
		t.setId(Integer.parseInt(id));
		t.setName(name);
		t.setSalary(Double.parseDouble(salary));
		t.setSubject(subject);

		et.begin();
		em.merge(t);
		et.commit();

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.write("Teacher Updated Successfully");
		RequestDispatcher rd = req.getRequestDispatcher("Div3.html");
		rd.include(req, resp);
	}
}
