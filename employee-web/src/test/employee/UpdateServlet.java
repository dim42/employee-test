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
 * Сервлет для обновления поля у сотрудника и обновления списка сотрудников.
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB(beanName = EmployeeBean.NAME)
    private EmployeeBeanLocal employee;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employee.update(Long.valueOf(request.getParameter("id")), request.getParameter("name"),
                request.getParameter("value"));
        response.sendRedirect(EMPLOYEES_SERVLET);
    }

}
