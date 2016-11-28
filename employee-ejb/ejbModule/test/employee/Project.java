package test.employee;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * Entity implementation class for Entity: Project
 * create table project (id int(10) primary key, name varchar(20));
 */
//@Entity
//@Table(name = "project")
public class Project implements Serializable, EntityBean {

    private static final long serialVersionUID = 1L;

//    @Id
    private Long id;
    private String name;
//    @Transient
    private List<Employee> employees;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void ejbLoad() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void ejbRemove() throws RemoveException, EJBException, RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void ejbStore() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setEntityContext(EntityContext ctx) throws EJBException, RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unsetEntityContext() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
        
    }
}
