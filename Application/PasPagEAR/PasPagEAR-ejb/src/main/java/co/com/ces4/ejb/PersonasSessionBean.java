/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.ejb;

import co.com.ces4.paspagcontrollers.PersonaJuridicaJpaController;
import co.com.ces4.paspagcontrollers.PersonaNaturalJpaController;
import co.com.ces4.paspagear.interfaces.PersonasSessionBeanRemote;
import co.com.ces4.paspagentities.PersonaJuridica;
import co.com.ces4.paspagentities.PersonaNatural;
import co.com.ces4.paspagentities.PersonaPK;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cristian
 */
@EJB(mappedName = "ejb/PersonasBean", name = "ejb/PersonasBean", beanInterface = co.com.ces4.paspagear.interfaces.PersonasSessionBeanRemote.class)
@Stateless
public class PersonasSessionBean implements PersonasSessionBeanRemote{

    private EntityManagerFactory emf;

    public PersonasSessionBean() {
        try {
            emf = Persistence.createEntityManagerFactory("co.com.ces4_PasPagControllers_jar_1PU");
        } catch (Exception e) {
            Logger.getLogger(TransactionSessionBean.class.getName()).log(Level.SEVERE, "Error generando instancia", e);
        }
    }

    @Override
    public PersonaNatural EncontrarPersonaNatural(PersonaPK persona) {
        PersonaNaturalJpaController controller = new PersonaNaturalJpaController(emf);
        return controller.findPersonaNatural(persona);
    }

    @Override
    public PersonaJuridica EncontrarPersonaJuridica(PersonaPK persona) {
        PersonaJuridicaJpaController controller = new PersonaJuridicaJpaController(emf);
        return controller.findPersonaJuridica(persona);
    }

    @Override
    public List<PersonaNatural> listarPersonasNaturales() {
       PersonaNaturalJpaController controller = new PersonaNaturalJpaController(emf);
       List listaPersonas = controller.findPersonaNaturalEntities();
       return listaPersonas;
    }
    
    @Override
    public List<PersonaJuridica> listarPersonasJuridicas() {
       PersonaJuridicaJpaController controller = new PersonaJuridicaJpaController(emf);
       return controller.findPersonaJuridicaEntities();
    }

    @Override
    public String pruebaPersonas() {
        return "saludos desde personas";
    }

    
}
