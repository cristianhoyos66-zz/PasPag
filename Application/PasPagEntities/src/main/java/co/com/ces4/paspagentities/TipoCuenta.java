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
    @Column(name = "FEVALIDO_DESDE")
    @Temporal(TemporalType.DATE)
    private Date valido_desde;
    @Column(name = "FEVALIDO_HASTA")
    @Temporal(TemporalType.DATE)
    private Date valido_hasta;

    public TipoCuenta() {
    }

    public TipoCuenta(String descripcion, Date valido_desde, Date valido_hasta) {
        this.descripcion = descripcion;
        this.valido_desde = valido_desde;
        this.valido_hasta = valido_hasta;
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
    
}
