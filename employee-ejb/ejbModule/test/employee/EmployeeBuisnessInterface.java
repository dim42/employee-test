package test.employee;

import java.util.List;

public interface EmployeeBuisnessInterface {

    /**
     * Создаёт в БД нового сотрудника с полями по-умолчанию.
     */
    void create();

    /**
     * Обновляет в БД значение поля для сотрудника.
     * 
     * @param id
     *            идентификатор сотрудника
     * @param field
     *            название поля
     * @param value
     *            значение поля
     */
    void update(Long id, String field, String value);

    /**
     * Удаляет из БД сотрудника по его идентификатору.
     * 
     * @param id
     *            идентификатор сотрудника
     */
    void delete(Long id);

    /**
     * Возвращает всех сотрудников из БД.
     * 
     * @return список всех сотрудников
     */
    List<EmployeeDTO> findAll();

    /**
     * Возвращает всех сотрудников из БД, ФИО которых соответствует шаблону.
     * 
     * @param employeeFio
     *            шаблон ФИО сотрудников
     * @return список всех сотрудников по шаблону ФИО
     */
    List<EmployeeDTO> findAllByFio(String employeeFio);
}
