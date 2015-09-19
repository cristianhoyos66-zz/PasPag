/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagentities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "TPP_CUENTAS")
public class Cuenta implements Serializable {
    @Id
    @Column(name = "DNINUMERO_CUENTA")
    private String numero_cuenta;
    @ManyToOne
    @JoinColumn(name = "DNITIPO_CUENTA")
    private TipoCuenta tipo_cuenta;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "DNIDOCUMENTO_PERSONA_JURIDICA"),
        @JoinColumn(name = "OPTIPO_DOCUMENTO_PERSONA_JURIDICA")
    })
    private PersonaJuridica personaJuridica;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "DNIDOCUMENTO_PERSONA_NATURAL"),
        @JoinColumn(name = "OPTIPO_DOCUMENTO_PERSONA_NATURAL")
    })
    private PersonaNatural personaNatural; 
    
    public Cuenta() {
    }

    public Cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipo_cuenta;
    }

    public void setTipoCuenta(TipoCuenta tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }
    
     public PersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }      
    
     @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.numero_cuenta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        return Objects.equals(this.numero_cuenta, other.numero_cuenta);
    }
    
}
