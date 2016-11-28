package test.employee;

import static test.employee.EmployeesServlet.EMPLOYEES_SERVLET;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет для удаления сотрудника и обновления списка сотрудников.
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB(beanName = EmployeeBean.NAME)
    private EmployeeBeanLocal employee;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employee.delete(Long.valueOf(request.getParameter("id")));
        response.sendRedirect(EMPLOYEES_SERVLET);
    }

}
