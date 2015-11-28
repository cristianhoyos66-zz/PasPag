/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author cristian
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "jms/TransaccionesQueueConnectionFactory")
})
public class TransaccionesMDB implements MessageListener {
    
    public TransaccionesMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
    
}
