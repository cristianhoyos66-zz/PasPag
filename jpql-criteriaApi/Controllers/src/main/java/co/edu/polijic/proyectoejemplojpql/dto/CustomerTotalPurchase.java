/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.polijic.proyectoejemplojpql.dto;

import co.edu.polijic.proyectoejemplojpql.model.Customer;
import java.math.BigDecimal;

/**
 *
 * @author AddaxT
 */
public class CustomerTotalPurchase {
    private Customer customer;
    private BigDecimal totalPurchase;

    public CustomerTotalPurchase(Customer customer, BigDecimal totalPurchase) {
        this.customer = customer;
        this.totalPurchase = totalPurchase;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(BigDecimal totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    @Override
    public String toString() {
        return "CustomerTotalPurchase{" + "customer=" + customer + ", totalPurchase=" + totalPurchase + '}';
    }
}
