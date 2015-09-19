package co.com.ces4.paspagentities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author cristian
 */

@Entity
@Table(name = "TPP_PERSONAS_JURIDICAS")
public class PersonaJuridica extends Persona {
    
    @Column(name = "DSCIUDAD_CONSTITUCION")
    private String ciudadConstitucion;
    @Column(name = "DSPAIS_CONSTITUCION")
    private String PaisConstitucion;
    @Column(name = "FECONSTITUCION")
    @Temporal(TemporalType.DATE)
    private Date fechaConstitucion;
    
    public PersonaJuridica() {
    }

    public PersonaJuridica(String ciudadConstitucion, String PaisConstitucion, Date fechaConstitucion, String documento, TipoDocumento tipoDocumento, String nombre, String contacto, String correo, String direccion, String usuario, String contrasena, Estado estado) {
        super(documento, tipoDocumento, nombre, contacto, correo, direccion, usuario, contrasena, estado);
        this.ciudadConstitucion = ciudadConstitucion;
        this.PaisConstitucion = PaisConstitucion;
        this.fechaConstitucion = fechaConstitucion;
    }
   

    public String getCiudadConstitucion() {
        return ciudadConstitucion;
    }

    public void setCiudadConstitucion(String ciudadConstitucion) {
        this.ciudadConstitucion = ciudadConstitucion;
    }

    public String getPaisConstitucion() {
        return PaisConstitucion;
    }

    public void setPaisConstitucion(String PaisConstitucion) {
        this.PaisConstitucion = PaisConstitucion;
    }

    public Date getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(Date fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }
    
    
}
