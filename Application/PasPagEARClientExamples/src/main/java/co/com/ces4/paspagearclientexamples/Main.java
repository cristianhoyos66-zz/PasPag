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
        System.out.println(inputStream);
        if (inputStream != null) {
            props.load(inputStream);
            ExampleServiceLocator esl = new ExampleServiceLocator(props);
            TransactionSessionBeanRemote transactionSessionBeanRemote = esl.<TransactionSessionBeanRemote>getEJBInstance("ejb/TransactionBean");
            PersonaPK vendedor = new PersonaPK("987654321", TipoDocumento.NIT);
            PersonaPK comprador = new PersonaPK("123456789", TipoDocumento.CEDULA);
            BigDecimal precio = new BigDecimal(500);
            System.out.println(transactionSessionBeanRemote.create(vendedor, comprador, precio).toString());
        }
    }
}
