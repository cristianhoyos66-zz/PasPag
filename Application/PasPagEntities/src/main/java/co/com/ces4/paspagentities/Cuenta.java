/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagentities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    
    @Column(name = "FEVALIDO_DESDE")
    @Temporal(TemporalType.DATE)
    private Date valido_desde;
    
    @Column(name = "FEVALIDO_HASTA")
    @Temporal(TemporalType.DATE)
    private Date valido_hasta;
    
    @ManyToOne
    @JoinColumn(name = "DNITIPO_CUENTA")
    private TipoCuenta tipo_cuenta;
    @ManyToOne(optional = true)
    @JoinColumns({
        @JoinColumn(name = "DNIDOCUMENTO_PERSONA_JURIDICA", referencedColumnName="DNIDOCUMENTO"),
        @JoinColumn(name = "OPTIPO_DOCUMENTO_CLIENTE", referencedColumnName="OPTIPO_DOCUMENTO")
    })
    private PersonaJuridica personaJuridica;
    @ManyToOne(optional = true)
    @JoinColumns({
        @JoinColumn(name = "DNIDOCUMENTO_PERSONA_NATURAL", referencedColumnName="DNIDOCUMENTO"),
        @JoinColumn(name = "OPTIPO_DOCUMENTO_PERSONA_NATURAL", referencedColumnName="OPTIPO_DOCUMENTO")
    })
    private PersonaNatural personaNatural; 
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "DNIDOCUMENTO_ENTIDAD_FINANCIERA", referencedColumnName="DNIDOCUMENTO"),
        @JoinColumn(name = "OPTIPO_DOCUMENTO_BANCO", referencedColumnName="OPTIPO_DOCUMENTO")
    })
    private EntidadFinanciera entidadFinanciera;

    
    
    public Cuenta() {
    }

    public Cuenta(String numero_cuenta, Date valido_desde, Date valido_hasta) {
        this.numero_cuenta = numero_cuenta;
        this.valido_desde = valido_desde;
        this.valido_hasta = valido_hasta;
    }

    public Date getValido_desde() {
        return valido_desde;
    }

    public void setValido_desde(Date valido_desde) {
        this.valido_desde = valido_desde;
    }

    public Date getValido_hasta() {
        return valido_hasta;
    }

    public void setValido_hasta(Date valido_hasta) {
        this.valido_hasta = valido_hasta;
    }

    public TipoCuenta getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(TipoCuenta tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
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
    
    
    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
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
