package test.employee;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Account
 * 
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private BigDecimal amount;
//    private Employee employee;

    @Id
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

//    public Employee getEmployee() {
//        return this.employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

}
