package test.employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CdiTest {
    @Inject
    Int1 int1;

    public CdiTest() {
        System.out.println("1!:" + getClass().getName());
    }
}
