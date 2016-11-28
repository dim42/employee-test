package test.employee;

/**
 * Класс для хранения состояния объектов {@code Employee}, передачи на view.
 */
public class EmployeeDTO {

    private Long id;
    private String fio;
    private Long departmentId;
    private String phone;
    private Integer salary;

    public EmployeeDTO(Employee employee) {
        setId(employee.getId());
        setFio(employee.getFio());
        setDepartmentId(employee.getDepartment() == null ? null : employee.getDepartment().getId());
        setPhone(employee.getPhone());
        setSalary(employee.getSalary());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return this.fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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