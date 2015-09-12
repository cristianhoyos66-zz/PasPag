package co.com.ces4.paspagentities;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cristian
 */
@Table
public class TipoCuenta {
    @Id
    private String id;
    private String descripcion;
    private Date valido_desde;
    private Date valido_hasta;

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
    
}
