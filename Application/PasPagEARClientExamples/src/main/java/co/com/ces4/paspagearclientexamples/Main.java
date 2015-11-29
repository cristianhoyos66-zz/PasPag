/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagearclientexamples;

import co.com.ces4.paspagear.interfaces.TransactionSessionBeanRemote;
import co.com.ces4.paspagentities.PersonaPK;
import co.com.ces4.paspagentities.TipoDocumento;
import java.io.IOException;
import java.io.InputStream;
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
            PersonaPK vendedor = new PersonaPK("987654321", TipoDocumento.NIT);
            System.out.println(transactionSessionBeanRemote.listarTransaccionesVendedor(vendedor));
        }
    }
}
