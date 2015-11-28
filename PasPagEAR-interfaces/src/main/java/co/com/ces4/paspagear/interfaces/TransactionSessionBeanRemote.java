/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagear.interfaces;

import co.com.ces4.paspagentities.PersonaPK;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Remote;
import co.com.ces4.paspagentities.Estado;
import co.com.ces4.paspagentities.Transaccion;
import java.util.List;

/**
 *
 * @author cristian
 */
@Remote
public interface TransactionSessionBeanRemote {
    
    Estado create(PersonaPK vendedor, PersonaPK comprador, BigDecimal precio);
    
    List<Transaccion> listarTransaccionesVendedor(PersonaPK vendedor);
    
    List<Transaccion> listarTransaccionesComprador(PersonaPK comprador);

}
