package co.com.ces4.paspagentities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author cristian
 */
@MappedSuperclass
public class Persona implements Serializable {
    @EmbeddedId
    private PersonaPK personaPK;
    @Column(name = "DSNOMBRE")
    private String nombre;
    @Column(name = "DSCONTACTO")
    private String contacto;
    @Column(name = "DSCORREO")
    private String correo;
    @Column(name = "DSDIRECCION")
    private String direccion;
    @Column(name = "DSUSUARIO")
    private String usuario;
    @Column(name = "DSCONTRASENA")
    private String contrasena;
    
    public Persona() {
    }

    public Persona(String documento, TipoDocumento tipoDocumento, String nombre, String contacto, String correo, String direccion, String usuario, String contrasena) {
        this.personaPK = new PersonaPK(documento, tipoDocumento);
        this.nombre = nombre;
        this.contacto = contacto;
        this.correo = correo;
        this.direccion = direccion; 
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public PersonaPK getPersonaPK() {
        return personaPK;
    }

    public void setPersonaPK(PersonaPK personaPK) {
        this.personaPK = personaPK;
    }

     
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.personaPK);
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
        final Persona other = (Persona) obj;
        return Objects.equals(this.personaPK, other.personaPK);
    }

    @Override
    public String toString() {
        return "Persona{" + "personaPK=" + personaPK + ", nombre=" + nombre + ", contacto=" + contacto + ", correo=" + correo + ", direccion=" + direccion + ", usuario=" + usuario + ", contrasena=" + contrasena + '}';
    }

   
    
}
