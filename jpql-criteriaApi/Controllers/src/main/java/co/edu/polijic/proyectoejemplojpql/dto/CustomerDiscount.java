/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.polijic.proyectoejemplojpql.dto;
import co.edu.polijic.proyectoejemplojpql.model.Customer;
import java.math.BigDecimal;

/**
 *
 * @author IASADM
 */
public class CustomerDiscount {

    private Customer customer;
    private BigDecimal discountRate;

    public CustomerDiscount(Customer customer, BigDecimal discountRate) {
        this.customer = customer;
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "CustomerDiscount{" + "customer=" + customer + ", discountRate=" + discountRate + '}';
    }
}
