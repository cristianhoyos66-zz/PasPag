/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4;

import co.com.ces4.paspagcontrollers.CuentaJpaController;
import javax.ejb.Stateless;
import co.com.ces4.paspagear.interfaces.TransactionSessionBeanRemote;
import co.com.ces4.paspagentities.PersonaPK;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import co.com.ces4.paspagcontrollers.TransaccionJpaController;
import co.com.ces4.paspagentities.Cuenta;
import co.com.ces4.paspagentities.Estado;
import co.com.ces4.paspagentities.TipoTransaccion;
import co.com.ces4.paspagentities.PersonaPK;
import co.com.ces4.paspagentities.TipoDocumento;
import co.com.ces4.paspagentities.Transaccion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cristian
 */
@EJB(mappedName = "ejb/TransactionBean")
@Stateless
public class TransactionSessionBean implements TransactionSessionBeanRemote {

    @PersistenceContext(unitName = "co.com.ces4_PasPagControllers_jar_1PU")
    private EntityManagerFactory emf;
    
    @Override
    public Estado create(PersonaPK vendedor, PersonaPK comprador, BigDecimal precio) {
        TransaccionJpaController controller = new TransaccionJpaController(emf);
        CuentaJpaController cuentaController = new CuentaJpaController(emf);
        
        Cuenta cuentaDestino = cuentaController.encontrarCuentaPorPersonaJuridica(vendedor);
        if (cuentaDestino.getNumero_cuenta().equals("")){
            cuentaDestino = cuentaController.encontrarCuentaPorPersonaNatural(vendedor);   
        }
        Cuenta cuentaOrigen = cuentaController.encontrarCuentaPorPersonaJuridica(comprador);
        if (cuentaOrigen.getNumero_cuenta().equals("")){
            cuentaOrigen = cuentaController.encontrarCuentaPorPersonaNatural(comprador);
        }
        
        if (cuentaOrigen.getSaldo().compareTo(cuentaDestino.getSaldo()) == -1){
            Estado estado = Estado.values()[1];
            return estado;
        }else {
            try {
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(precio));
                cuentaController.edit(cuentaOrigen);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(precio));
                cuentaController.edit(cuentaDestino);
            } catch (Exception ex) {
                Logger.getLogger(TransactionSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            TipoTransaccion tipoTransaccion = TipoTransaccion.values()[1];
            Estado estado = Estado.values()[0];
            String descripcion = "La transacción se creó correctamente";
            Date currentDate = new Date();

            Transaccion transaccion = new Transaccion(precio, tipoTransaccion, estado, cuentaOrigen, cuentaDestino, descripcion, currentDate);

            controller.create(transaccion);
            return estado;
        }
    }

    @Override
    public String listarTransacciones(PersonaPK vendedor, PersonaPK comprador, Date fechaInicio, Date fechaFin) {
        return null;
    }

    
}
