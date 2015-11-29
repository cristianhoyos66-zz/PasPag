/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.ejb;

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
import co.com.ces4.paspagentities.PersonaJuridica;
import co.com.ces4.paspagentities.PersonaNatural;
import co.com.ces4.paspagentities.TipoTransaccion;
import co.com.ces4.paspagentities.PersonaPK;
import co.com.ces4.paspagentities.TipoDocumento;
import co.com.ces4.paspagentities.Transaccion;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cristian
 */
@EJB(mappedName = "ejb/TransactionBean", name = "ejb/TransactionBean", beanInterface = co.com.ces4.paspagear.interfaces.TransactionSessionBeanRemote.class)
@Stateless
public class TransactionSessionBean implements TransactionSessionBeanRemote{
    
    //Este se usa cuando la unidad de persistencia es jta
    //@PersistenceContext(unitName = "co.com.ces4_PasPagControllers_jar_1PU")
    private EntityManagerFactory emf;

    public TransactionSessionBean() {
        try {
            emf = Persistence.createEntityManagerFactory("co.com.ces4_PasPagControllers_jar_1PU");
        } catch (Exception e) {
            Logger.getLogger(TransactionSessionBean.class.getName()).log(Level.SEVERE, "Error generando instancia", e);
        }
    }
    
    /*@PersistenceContext(unitName = "co.com.ces4_PasPagControllers_jar_1PU")
    private EntityManagerFactory emf;*/
    
    @Override
    public List<Transaccion> listarTransaccionesVendedorPersonaJuridica(PersonaJuridica vendedor) {
        TransaccionJpaController controller = new TransaccionJpaController(emf);
        CuentaJpaController cuentaController = new CuentaJpaController(emf);
        Cuenta cuentaDestino = cuentaController.encontrarCuentaPorPersonaJuridica(vendedor);
        return controller.obtenerTransaccionesPorVendedor(cuentaDestino);
    }
    
    @Override
    public List<Transaccion> listarTransaccionesVendedorPersonaNatural(PersonaNatural vendedor) {
        TransaccionJpaController controller = new TransaccionJpaController(emf);
        CuentaJpaController cuentaController = new CuentaJpaController(emf);
        Cuenta cuentaDestino = cuentaController.encontrarCuentaPorPersonaNatural(vendedor);
        return controller.obtenerTransaccionesPorVendedor(cuentaDestino);
    }

    @Override
    public List<Transaccion> listarTransaccionesCompradorPersonaNatural(PersonaNatural comprador) {
        TransaccionJpaController controller = new TransaccionJpaController(emf);
        CuentaJpaController cuentaController = new CuentaJpaController(emf);
        Cuenta cuentaOrigen = cuentaController.encontrarCuentaPorPersonaNatural(comprador);
        return controller.obtenerTransaccionesPorComprador(cuentaOrigen);
    }
    
    @Override
    public List<Transaccion> listarTransaccionesCompradorPersonaJuridica(PersonaJuridica comprador) {
        TransaccionJpaController controller = new TransaccionJpaController(emf);
        CuentaJpaController cuentaController = new CuentaJpaController(emf);
        Cuenta cuentaOrigen = cuentaController.encontrarCuentaPorPersonaJuridica(comprador);
        return controller.obtenerTransaccionesPorComprador(cuentaOrigen);
    }

    @Override
    public List<Transaccion> listarTransaccionesPrecio(BigDecimal precio) {
        TransaccionJpaController controller = new TransaccionJpaController(emf);
        return controller.listarTransaccionesPrecio(precio);
    }

    @Override
    public String prueba() {
        return "hola mundo";
    }

    
}
