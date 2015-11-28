/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagentities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "TPP_TRANSACCIONES")
public class Transaccion implements Serializable {
    
     @TableGenerator(name = "TransGen",
            table = "TPP_SEQ",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "PED_CON",
            initialValue = 1,
            allocationSize = 1
    )
    
    @Id
    @Column(name = "DNIID_TRANS")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TransGen")
    private Integer idTrans;
    @Column(name = "NMMONTO_TRANSACCION")
    private BigDecimal montoTransaccion;
    @Column(name = "OPTIPO_TRANSACCION")
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipoTransaccion;
    @Column(name = "OPESTADO")
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    @JoinColumn(name = "DNINUMERO_CUENTA_ORIGEN")
    private Cuenta cuenta_origen;
    @ManyToOne
    @JoinColumn(name = "DNINUMERO_CUENTA_DESTINO")
    private Cuenta cuenta_destino;
    @Column(name = "DSDESCRIPCION")
    private String descripcion;
    @Column(name = "FETRANSACCION")
    @Temporal(TemporalType.DATE)
    private Date fecha_transaccion;
    
    public Transaccion() {
    }

    public Transaccion(BigDecimal montoTransaccion, TipoTransaccion tipoTransaccion, Estado estado, Cuenta cuenta_origen, Cuenta cuenta_destino, String descripcion, Date fecha_transaccion) {
        this.montoTransaccion = montoTransaccion;
        this.tipoTransaccion = tipoTransaccion;
        this.estado = estado;
        this.cuenta_origen = cuenta_origen;
        this.cuenta_destino = cuenta_destino;
        this.descripcion = descripcion;
        this.fecha_transaccion = fecha_transaccion;
    }
    
    public Integer getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Integer idTrans) {
        this.idTrans = idTrans;
    }

    public BigDecimal getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(BigDecimal montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cuenta getCuenta_origen() {
        return cuenta_origen;
    }

    public void setCuenta_origen(Cuenta cuenta_origen) {
        this.cuenta_origen = cuenta_origen;
    }

    public Cuenta getCuenta_destino() {
        return cuenta_destino;
    }

    public void setCuenta_destino(Cuenta cuenta_destino) {
        this.cuenta_destino = cuenta_destino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_transaccion() {
        return fecha_transaccion;
    }

    public void setFecha_transaccion(Date fecha_transaccion) {
        this.fecha_transaccion = fecha_transaccion;
    }
    
     @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.idTrans);
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
        final Transaccion other = (Transaccion) obj;
        return Objects.equals(this.idTrans, other.idTrans);
    }

    @Override
    public String toString() {
        return "Transaccion{" + "idTrans=" + idTrans + ", montoTransaccion=" + montoTransaccion + ", tipoTransaccion=" + tipoTransaccion + ", estado=" + estado + ", cuenta_origen=" + cuenta_origen + "cuenta_destino=" + cuenta_destino + "descripcion=" + descripcion + "fecha_transaccion=" + fecha_transaccion +'}';
    }
            
}
