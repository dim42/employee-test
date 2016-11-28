package test.employee;

import static java.util.logging.Level.INFO;
import static test.employee.util.LogHelper.log;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Сущность для таблицы сотрудников (employee).
 */
@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@NamedQueries({
        @NamedQuery(name = Employee.FIND_EMPLOYEES_BY_PART_OF_FIO, query = "SELECT e FROM Employee e WHERE e.fio LIKE :employeeFio "
                + "OR ('%%' LIKE :employeeFio and e.fio IS NULL)"),
        @NamedQuery(name = Employee.FIND_ALL_EMPLOYEES, query = "SELECT e FROM Employee e ORDER BY e.id") })
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_EMPLOYEES_BY_PART_OF_FIO = "findEmployeesByPartOfFio";
    public static final String FIND_ALL_EMPLOYEES = "findAllEmployees";
    private final transient Logger logger = Logger.getLogger(Employee.class.getName());

    @Id
    private Long id;
    private String fio;

//    @Basic(optional = false)
//    private Account account;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String phone;
    private Integer salary;

    @Id
    public Long getId() {
        log(logger, INFO, "emp.id: {0}", id);
        return this.id;
    }

    @Id
    public void setId(Long id) {
        log(logger, INFO, "set emp.id: {0}", id);
        this.id = id;
    }

    public String getFio() {
        return this.fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    // public Long getDepartmentId() {
    // return this.departmentId;
    // }
    //
    // public void setDepartmentId(Long departmentId) {
    // this.departmentId = departmentId;
    // }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

}