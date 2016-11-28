package test.employee;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Сущность для таблицы департаментов (department).
 */
@Entity
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@NamedQueries({
        @NamedQuery(name = "findDepartmentsByPartOfName", query = "SELECT d FROM Department d WHERE d.name LIKE :departmentName"),
        @NamedQuery(name = Department.FIND_ALL_DEPARTMENTS, query = "SELECT d FROM Department d ORDER BY d.id") })
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_DEPARTMENTS = "findAllDepartments";

    @Id
    private Long id;
    private String name;
    private String room;
    private String chief;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

}