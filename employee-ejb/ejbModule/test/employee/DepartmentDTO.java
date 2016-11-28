package test.employee;

/**
 * Класс для хранения состояния объектов {@code Department}, передачи на view.
 */
public class DepartmentDTO {

    private Long id;
    private String name;
    private String room;
    private String chief;

    public DepartmentDTO(Department department) {
        setId(department.getId());
        setName(department.getName());
        setRoom(department.getRoom());
        setChief(department.getChief());
    }

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
