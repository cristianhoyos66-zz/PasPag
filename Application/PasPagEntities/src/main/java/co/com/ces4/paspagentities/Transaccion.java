/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagentities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cristian
 */
@Table
public class Transaccion {
    @Id
    private String idTrans;
    private BigDecimal montoTransaccion;
    private TipoTransaccion tipoTransaccion;
    private Estado estado;
    private Cuenta cuenta_origen;
    private Cuenta cuenta_destino;
    private String descripcion;
    private Date fecha_transaccion;

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
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
            
}
