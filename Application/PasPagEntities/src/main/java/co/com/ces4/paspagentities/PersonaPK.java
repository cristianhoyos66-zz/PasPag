/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagentities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author cristian
 */
@Embeddable
public class PersonaPK implements Serializable{
    
    @Column(name = "DNIDOCUMENTO")
    private String documento;
    @Column(name = "OPTIPO_DOCUMENTO")
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    public PersonaPK() {
    }

    public PersonaPK(String documento, TipoDocumento tipoDocumento) {
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.documento);
        hash = 71 * hash + Objects.hashCode(this.tipoDocumento);
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
        final PersonaPK other = (PersonaPK) obj;
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        return this.tipoDocumento == other.tipoDocumento;
    }

    @Override
    public String toString() {
        return "PersonaPK{" + "documento=" + documento + ", tipoDocumento=" + tipoDocumento + '}';
    }

}
