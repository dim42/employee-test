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
 * Сервлет для поиска всех сотрудников и обновления списка сотрудников.
 */
@WebServlet("/" + EmployeesServlet.EMPLOYEES_SERVLET)
public class EmployeesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final String EMPLOYEES_SERVLET = "EmployeesServlet";

    /**
     * {@code beanName} - для связи с бином по имени, {@code name} - для возможной перезаписи через xml.
     */
    @EJB(beanName = EmployeeBean.NAME)//, name = EmployeeBean.NAME)
    private EmployeeBeanLocal employee;

    @EJB(beanName = DepartmentBean.NAME)
    private DepartmentBeanLocal department;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeDTO> employees = employee.findAll();
        request.setAttribute("employees", employees);

        List<DepartmentDTO> departments = department.findAll();
        request.setAttribute("departments", departments);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/employees.jsp");
        dispatcher.forward(request, response);
    }

}
