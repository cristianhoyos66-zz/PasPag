/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.polijic.proyectoejemplojpql.dto;

import co.edu.polijic.proyectoejemplojpql.model.Product;

/**
 *
 * @author AddaxT
 */
public class ProductSales {
    private Product product;
    private Long quantity;

    public ProductSales() {
    }

    public ProductSales(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity == null ? 0:quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductSales{" + "product=" + product + ", quantity=" + quantity + '}';
    }
}
