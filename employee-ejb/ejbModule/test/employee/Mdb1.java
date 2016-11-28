package test.employee;

import static test.employee.util.LogHelper.info;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: Mdb1
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "dest1") }, mappedName = "dest1")
public class Mdb1 implements MessageListener {

    private final Logger logger = Logger.getLogger(Mdb1.class.getName());

    /**
     * @see MessageListener#onMessage(Message)
     */
    @Override
    public void onMessage(Message message) {
        info(logger, "starting");
    }
}
