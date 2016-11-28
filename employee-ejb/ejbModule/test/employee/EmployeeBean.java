package test.employee;

import static java.util.logging.Level.INFO;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static test.employee.util.Encoder.encode;
import static test.employee.util.LogHelper.info;
import static test.employee.util.LogHelper.log;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import test.employee.util.BeanInterceptor;
import test.employee.util.EmployeeHelper;

/**
 * Session Bean implementation class - для операций с сотрудниками.<br>
 * mappedName - если не указать, то ошибка NameAlreadyBoundException для Glassfish-specific (Non-portable) JNDI names
 * for...
 */
@Named
@Stateless(name = EmployeeBean.NAME)
// , mappedName = EmployeeBean.NAME)
@Remote(EmployeeBeanRemote.class)
public class EmployeeBean implements EmployeeBeanRemote, EmployeeBeanLocal {

    private final Logger logger = Logger.getLogger(EmployeeBean.class.getName());
    public static final String NAME = "employee-bean1";

    @PersistenceContext
    private EntityManager em;

    @RequestScoped
    @Inject
    CdiTest cdiTest;

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(REQUIRED)
    public void create() {
        Employee employee = new Employee();
        log(logger, INFO, "emp: {0}", employee);
        em.persist(employee);
    }

    // create report (jms)
    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(REQUIRED)
    public void update(Long id, String field, String value) {
        log(logger, INFO, "id: {0}, field: {1}, value: {2}", id, field, value);
        Employee employee = findById(id);
        if ("departmentId".equals(field)) {
            Department dept = findDeptById(Long.valueOf(value));
            log(logger, INFO, "dept.id: {0}", dept.getId());
            EmployeeHelper.set(employee, field, dept);
        } else {
            EmployeeHelper.set(employee, field, value);
        }
        em.merge(employee); // saves null property values
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(REQUIRED)
    public void delete(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        Metamodel metamodel = em.getMetamodel();
        // metamodel.
        // CriteriaQuery<Employee> createQuery = cb.create
        // Query(Employee.class);
        // createQuery.
        int deletedNumber = em.createNamedQuery("employee.delete-by-id").setParameter("id", id).executeUpdate();
        info(logger, "deletedNumber: {0}", deletedNumber);
    }

    /**
     * {@inheritDoc}
     */
    private Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    /**
     * {@inheritDoc}
     */
    private Department findDeptById(Long id) {
        return em.find(Department.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Interceptors(BeanInterceptor.class)
    @Override
    @TransactionAttribute(REQUIRED)
    public List<EmployeeDTO> findAll() {
        List<Employee> resultList = em.createNamedQuery(Employee.FIND_ALL_EMPLOYEES, Employee.class).getResultList();
        logger.log(Level.INFO, "employees.size: {0}", resultList.size());
        return convertToDTO(resultList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(REQUIRED)
    public List<EmployeeDTO> findAllByFio(String employeeFio) {
        List<Employee> resultList = em.createNamedQuery(Employee.FIND_EMPLOYEES_BY_PART_OF_FIO, Employee.class)
                .setParameter("employeeFio", "%" + encode(employeeFio) + "%").getResultList();
        return convertToDTO(resultList);
    }

    private List<EmployeeDTO> convertToDTO(List<Employee> resultList) {
        List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>(resultList.size());
        for (Employee each : resultList) {
            EmployeeDTO employee = new EmployeeDTO(each);
            employees.add(employee);
        }
        return employees;
    }
}
