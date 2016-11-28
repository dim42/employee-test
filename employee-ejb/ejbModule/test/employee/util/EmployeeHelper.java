package test.employee.util;

import static test.employee.util.Encoder.encode;

import java.util.HashMap;
import java.util.Map;

import test.employee.Department;
import test.employee.Employee;

/**
 * Вспомогательный класс для установки значений полей (по их названию) сотрудников.
 */
public class EmployeeHelper {

    private static final Map<String, FieldSetter> fieldMap = new HashMap<String, EmployeeHelper.FieldSetter>();

    static {
        fieldMap.put("fio", new FioSetter());
        fieldMap.put("departmentId", new DepartmentIdSetter());
        fieldMap.put("phone", new PhoneSetter());
        fieldMap.put("salary", new SalarySetter());
    }

    /**
     * Устанавливает значение {@code value} для поля {@code field}, принадлежащее сотруднику {@code employee}.
     * 
     * @param employee
     *            сотрудник, владелец поля
     * @param field
     *            название поля
     * @param value
     *            значение поля
     */
    public static void set(Employee employee, String field, Object value) {
        fieldMap.get(field).set(employee, value);
    }

    public static interface FieldSetter {

        void set(Employee employee, Object value);
    }

    private static class FioSetter implements FieldSetter {
        @Override
        public void set(Employee employee, Object value) {
            employee.setFio(encode((String) value));
        }
    }

    private static class DepartmentIdSetter implements FieldSetter {
        @Override
        public void set(Employee employee, Object value) {
//            employee.setDepartmentId("null".equals(value) ? null : Long.valueOf(value));
            employee.setDepartment((Department) value);
        }
    }

    private static class PhoneSetter implements FieldSetter {
        @Override
        public void set(Employee employee, Object value) {
            employee.setPhone(encode((String) value));
        }
    }

    private static class SalarySetter implements FieldSetter {
        @Override
        public void set(Employee employee, Object value) {
            String stringValue = (String) value;
            employee.setSalary(stringValue.isEmpty() ? null : Integer.valueOf(stringValue));
        }
    }
}