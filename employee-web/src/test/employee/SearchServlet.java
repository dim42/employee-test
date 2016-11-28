package test.employee;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет для поиска сотрудников по шаблону ФИО и обновления списка сотрудников.
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB(beanName = EmployeeBean.NAME)
    private EmployeeBeanLocal employee;

    @EJB
    private DepartmentBeanLocal department;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        List<EmployeeDTO> employees = employee.findAllByFio(request.getParameter("employeeFio"));
        request.setAttribute("employees", employees);

        List<DepartmentDTO> departments = department.findAll();
        request.setAttribute("departments", departments);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/employees.jsp");
        dispatcher.forward(request, response);
    }

}
