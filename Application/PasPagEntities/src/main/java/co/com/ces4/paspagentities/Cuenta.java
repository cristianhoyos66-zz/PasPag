/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspagentities;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cristian
 */
@Table
public class Cuenta {
    @Id
    private String numero_cuenta;
    private Banco banco;
    private TipoCuenta tipo_cuenta;
    private Persona persona; 

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public TipoCuenta getTipoCuenta() {
        return tipo_cuenta;
    }

    public void setTipoCuenta(TipoCuenta tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }   
    
}
