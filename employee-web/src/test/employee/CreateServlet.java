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
 * Сервлет для создания нового сотрудника с полями по-умолчанию и обновления списка сотрудников.
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB(beanName = EmployeeBean.NAME)
    private EmployeeBeanLocal employee;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employee.create();
        response.sendRedirect(EMPLOYEES_SERVLET);
    }

}
