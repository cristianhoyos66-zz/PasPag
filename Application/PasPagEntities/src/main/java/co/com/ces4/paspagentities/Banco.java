package co.com.ces4.paspagentities;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author cristian
 */
@Table
public class Banco {
    
    @Id
    private String nit;
    private String nombre;
    private String correo;
    
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
