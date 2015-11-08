/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspag.listener;

import co.com.ces4.paspagcontrollers.CuentaJpaController;
import co.com.ces4.paspagcontrollers.EntidadFinancieraJpaController;
import co.com.ces4.paspagcontrollers.PersonaJuridicaJpaController;
import co.com.ces4.paspagcontrollers.PersonaNaturalJpaController;
import co.com.ces4.paspagcontrollers.TipoCuentaJpaController;
import co.com.ces4.paspagcontrollers.TransaccionJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author cristian
 */
public class BootstrapListener implements ServletContextListener {

   @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PasPagPU");
        
        CuentaJpaController cuentajpa = new CuentaJpaController(emf);
        EntidadFinancieraJpaController entidadFinancierJpa = new EntidadFinancieraJpaController(emf);
        PersonaJuridicaJpaController personaJuridicajpa = new PersonaJuridicaJpaController(emf);
        PersonaNaturalJpaController personaNaturaljpa = new PersonaNaturalJpaController(emf);
        TipoCuentaJpaController tipoCuentajpa = new TipoCuentaJpaController(emf);
        TransaccionJpaController transaccionjpa = new TransaccionJpaController(emf);
        
        sce.getServletContext().setAttribute("entityManagerFactory", emf);
        sce.getServletContext().setAttribute("cuentajpa", cuentajpa);
        sce.getServletContext().setAttribute("entidadFinancierJpa", entidadFinancierJpa);
        sce.getServletContext().setAttribute("personaJuridicajpa", personaJuridicajpa);
        sce.getServletContext().setAttribute("personaNaturaljpa", personaNaturaljpa);
        sce.getServletContext().setAttribute("tipoCuentajpa", tipoCuentajpa);
        sce.getServletContext().setAttribute("transaccionjpa", transaccionjpa);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
