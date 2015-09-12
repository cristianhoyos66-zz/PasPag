package co.com.ces4.paspagentities;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cristian
 */
@Table
public class TipoDocumento {
    @Id
    private String id_tipo;
    private String descripcion;
    
    public String getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(String id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
