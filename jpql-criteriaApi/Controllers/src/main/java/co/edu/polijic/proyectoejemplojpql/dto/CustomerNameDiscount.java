/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.polijic.proyectoejemplojpql.dto;

import java.math.BigDecimal;

/**
 *
 * @author IASADM
 */
public class CustomerNameDiscount {

    private String name;
    private BigDecimal discountRate;

    public CustomerNameDiscount(String name, BigDecimal discountRate) {
        this.name = name;
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "CustomerDiscount{" + "customer=" + name + ", discountRate=" + discountRate + '}';
    }
}
