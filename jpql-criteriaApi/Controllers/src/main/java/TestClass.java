
import co.edu.polijic.proyectoejemplojpql.controllers.CustomerJpaController;
import co.edu.polijic.proyectoejemplojpql.controllers.ProductJpaController;
import co.edu.polijic.proyectoejemplojpql.dto.CustomerDiscount;
import co.edu.polijic.proyectoejemplojpql.dto.CustomerNameDiscount;
import co.edu.polijic.proyectoejemplojpql.dto.ProductSales;
import co.edu.polijic.proyectoejemplojpql.model.Customer;
import co.edu.polijic.proyectoejemplojpql.model.Product;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import javax.persistence.Tuple;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sala306
 */
public class TestClass {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectoEjemploJPQLPU");
        
        CustomerJpaController customerJpaController = new CustomerJpaController(emf);
        
        for (Object[] customer : customerJpaController.getCustomerPurchaseValueByNameAndOrProduct(null)) {
            System.out.println(Arrays.toString(customer));
        }
        
        /*for (Object[] customer : customerJpaController.getCustomerPurchaseByNameAndOrProduct("Poney Express", 980030)) {
            System.out.println(Arrays.toString(customer));
        }*/
        
        /*for (Object[] customer : customerJpaController.getCustomerPurchase()) {
            System.out.println(Arrays.toString(customer));
        }*/
        
        /*for (Object[] customer : customerJpaController.getCustomerDiscountRatePCE()) {
            System.out.println(Arrays.toString(customer));
        }*/
        
        /*for (CustomerDiscount customer : customerJpaController.getCustomerDiscountRateCECB()) {
            System.out.println(customer);
        }*/
        
        /*for (CustomerDiscount customer : customerJpaController.getCustomerDiscountRateCE()) {
            System.out.println(customer);
        }*/      
        
        
        /*for (CustomerNameDiscount customer : customerJpaController.getCustomerDiscountRateCES()) {
            System.out.println(customer);
        }*/        
        
        /*for (Tuple customer : customerJpaController.getCustomerDiscountRateTCN()) {
           System.out.println(customer.get("nombre"));
           System.out.println(customer.get("porcentaje"));
        }*/
        
        /*for (Tuple customer : customerJpaController.getCustomerDiscountRateT2()) {
           System.out.println(customer.get(0));
           System.out.println(customer.get(1));
        }*/
        
        /*for (Object[] customer : customerJpaController.getCustomerDiscountRateO()) {
           System.out.println(Arrays.toString(customer));
        }*/
        
        /*for (Tuple customer : customerJpaController.getCustomerDiscountRateT()) {
            //.get(1) obtiene el código de descuento 
            System.out.println(customer.get(0));
        }*/
        
        /*for (Tuple customer : customerJpaController.getCustomerDiscountRateT()) {
                //.get(1) obtiene el código de descuento 
            System.out.println(customer.get(0));
        }*/
        
        /*for (Customer customer : customerJpaController.getCustomersByName("Small Bill Company")) {
            System.out.println(customer);
        }*/
        
        /*for (String customer : customerJpaController.getCustomerNames()) {
            System.out.println(customer);
        }*/
        
        /*ProductJpaController productJpaController = new ProductJpaController(emf);
        
        for (Product product  : productJpaController.findProductByDescription("SERVER")) {
            System.out.println(product);
        }
        Product id  = productJpaController.findProductById(980001);
        System.out.println(id);*/
        
        /*for (ProductSales productSales : productJpaController.getTopTenProducts()) {
            System.out.println(productSales);
        }*/
        /*for (ProductSales productSales : productJpaController.getBottomTenProducts()) {
            System.out.println(productSales);
        }*/
       /* for (ProductSales productSales : productJpaController.getTopIncomes()) {
            System.out.println(productSales);
        }*/
        
        /*CustomerJpaController customerJpaController = new CustomerJpaController(emf);
        for (Customer customer : customerJpaController.getAllCustomers()) {
            System.out.println(customer);
        }
        System.out.println("Listar los primeros 5");
        for (Customer customer : customerJpaController.getFirstFiveCustomers()) {
            System.out.println(customer);
        }
        System.out.println("Listar los primeros 5 con codigos de descuento L o H");
        for (Customer customer : customerJpaController.getCustomersSpecialDicountCodes()) {
            System.out.println(customer);
        }
        
        System.out.println("Listar los primeros 5 con codigos de descuento L o H");
        for (Object[] customer : customerJpaController.getTopTen()) {
            System.out.println(Arrays.toString(customer));
        }*/
        
    }
}
