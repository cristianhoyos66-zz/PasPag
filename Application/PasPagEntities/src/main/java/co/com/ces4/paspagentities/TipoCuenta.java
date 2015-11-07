package co.com.ces4.paspagentities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "TPP_TIPO_CUENTAS")
public class TipoCuenta implements Serializable{
    
    @TableGenerator(name = "TipCueGen",
            table = "TPP_SEQ",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "PED_CON",
            initialValue = 1,
            allocationSize = 1
    )
    
    @Id
    @Column(name = "DNITIPO_CUENTA")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TipCueGen")
    private Integer id;
    @Column(name = "DSDESCRIPCION")
    private String descripcion;

    public TipoCuenta() {
    }

    public TipoCuenta(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final TipoCuenta other = (TipoCuenta) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "TipoCuenta{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
