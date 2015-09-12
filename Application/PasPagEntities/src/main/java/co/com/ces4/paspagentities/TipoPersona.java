package co.com.ces4.paspagentities;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cristian
 */
@Table
public class TipoPersona {
    @Id
    private String id;
    private String descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
