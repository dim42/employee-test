package test.employee;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class - для операций с департаментами.
 */
@Stateless(name = DepartmentBean.NAME)
public class DepartmentBean implements DepartmentBeanRemote, DepartmentBeanLocal {

    public static final String NAME = "department-bean";
    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(REQUIRED)
    public List<DepartmentDTO> findAll() {
        List<Department> resultList = em.createNamedQuery(Department.FIND_ALL_DEPARTMENTS, Department.class)
                .getResultList();
        return convertToDTO(resultList);
    }

    private List<DepartmentDTO> convertToDTO(List<Department> resultList) {
        List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>(resultList.size());
        for (Department each : resultList) {
            DepartmentDTO department = new DepartmentDTO(each);
            departments.add(department);
        }
        return departments;
    }
}
