/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagearclientexamples;

import co.com.ces4.paspagear.interfaces.PersonasSessionBeanRemote;
import co.com.ces4.paspagear.interfaces.TransactionSessionBeanRemote;
import co.com.ces4.paspagentities.PersonaNatural;
import co.com.ces4.paspagentities.PersonaPK;
import co.com.ces4.paspagentities.TipoDocumento;
import co.com.ces4.paspagentities.Transaccion;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Properties;
import javax.naming.NamingException;

/**
 *
 * @author cristian
 */                                                                         
public class Main {
     public static void main(String[] args) throws IOException, NamingException {
        Properties props = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ServiceLocator.properties");
        if (inputStream != null) {
            props.load(inputStream);
            ExampleServiceLocator esl = new ExampleServiceLocator(props);
            //Portable JNDI names for EJB TransactionSessionBean: [java:global/PasPagEAR-ear-1/PasPagEAR-ejb-1/TransactionSessionBean, java:global/PasPagEAR-ear-1/PasPagEAR-ejb-1/TransactionSessionBean!co.com.ces4.paspagear.interfaces.TransactionSessionBeanRemote]
            TransactionSessionBeanRemote transactionSessionBeanRemote = esl.<TransactionSessionBeanRemote>getEJBInstance("java:global/PasPagEAR-ear-1/PasPagEAR-ejb-1/TransactionSessionBean");
            PersonasSessionBeanRemote personasSessionBeanRemote = esl.<PersonasSessionBeanRemote>getEJBInstance("java:global/PasPagEAR-ear-1/PasPagEAR-ejb-1/PersonasSessionBean");
            
            PersonaPK comprador = new PersonaPK("123456789", TipoDocumento.CEDULA);
            
            PersonaNatural compradorPersonaNatural = personasSessionBeanRemote.EncontrarPersonaNatural(comprador);
            
            System.out.println(compradorPersonaNatural);
            
            /*for (Object persona  : personasSessionBeanRemote.listarPersonasNaturales()) {
                System.out.println(persona);
            }*/
            
            //System.out.println(transactionSessionBeanRemote.prueba());
            
            //System.out.println(personasSessionBeanRemote.pruebaPersonas());
            
            /*for (Transaccion transaccion  : transactionSessionBeanRemote.listarTransaccionesCompradorPersonaNatural(compradorPersonaNatural)) {
                System.out.println(transaccion);
            }*/
        }
    }
}
