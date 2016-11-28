package test.employee;

public class Inj1 implements Int1 {
    public Inj1() {
        System.out.println("!:" + getClass().getName());
    }
}
