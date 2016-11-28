package test.employee;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class EmployeeBean2
 */
@Stateless(name = EmployeeBean2.NAME, mappedName = EmployeeBean2.NAME)
@Remote(EmployeeBeanRemote.class)
public class EmployeeBean2 implements EmployeeBeanRemote, EmployeeBeanLocal {

    public static final String NAME = "employee-bean2";

    @Override
    public void create() {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Long id, String field, String value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<EmployeeDTO> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EmployeeDTO> findAllByFio(String employeeFio) {
        // TODO Auto-generated method stub
        return null;
    }

}
