package test.employee;

import java.util.List;

import javax.ejb.Local;

@Local
public interface DepartmentBeanLocal {

    /**
     * Возвращает все департаменты из БД.
     * 
     * @return список всех департаментов
     */
    List<DepartmentDTO> findAll();

}
