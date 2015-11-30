/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageFormatException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author cristian
 */
@MessageDriven(mappedName = "jms/TransaccionesQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "jms/TransaccionesQueueConnectionFactory")
})
public class TransaccionesMDB implements MessageListener {
    
    @Resource
    private MessageDrivenContext mdc;
    
    public TransaccionesMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            try {
                System.out.println("Mensaje: " + tm.getText());
            } catch (JMSException ex) {
                Logger.getLogger(TransaccionesMDB.class.getName()).log(Level.SEVERE, "Error leyendo mensaje", ex);
                mdc.setRollbackOnly();
            }
        } else if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            try {
                System.out.println("Mensaje: " + om.getObject());
            } catch (MessageFormatException ex) {
                Logger.getLogger(TransaccionesMDB.class.getName()).log(Level.SEVERE, null, ex);
                mdc.setRollbackOnly();
            } catch (JMSException ex) {
                Logger.getLogger(TransaccionesMDB.class.getName()).log(Level.SEVERE, null, ex);
                mdc.setRollbackOnly();
            }
        } else {
            Logger.getLogger(TransaccionesMDB.class.getName()).log(Level.SEVERE, "Tipo de mensaje no soportado");
            mdc.setRollbackOnly();
        }
    }
    
}
