/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagentities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "TPP_PERSONAS_NATURALES")
public class PersonaNatural extends Persona implements Serializable{
    
    @Column(name = "DSCIUDAD_NACIMIENTO")
    private String ciudadNacimiento;
    @Column(name = "DSPAIS_NACIMIENTO")
    private String paisNacimiento;
     @Column(name = "FENACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
  
    public PersonaNatural() {
    }

    public PersonaNatural(String ciudadNacimiento, String paisNacimiento, Date fechaNacimiento, String documento, TipoDocumento tipoDocumento, String nombre, String contacto, String correo, String direccion, String usuario, String contrasena) {
        super(documento, tipoDocumento, nombre, contacto, correo, direccion, usuario, contrasena);
        this.ciudadNacimiento = ciudadNacimiento;
        this.paisNacimiento = paisNacimiento;
        this.fechaNacimiento = fechaNacimiento;
    }
   

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "PersonaNatural{" + "ciudadNacimiento=" + ciudadNacimiento + ", paisNacimiento=" + paisNacimiento + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
    
    
       
}
