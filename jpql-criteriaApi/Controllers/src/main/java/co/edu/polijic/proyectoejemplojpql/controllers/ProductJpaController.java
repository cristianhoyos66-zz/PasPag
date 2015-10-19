/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.polijic.proyectoejemplojpql.controllers;

import co.edu.polijic.proyectoejemplojpql.controllers.exceptions.IllegalOrphanException;
import co.edu.polijic.proyectoejemplojpql.controllers.exceptions.NonexistentEntityException;
import co.edu.polijic.proyectoejemplojpql.controllers.exceptions.PreexistingEntityException;
import co.edu.polijic.proyectoejemplojpql.dto.ProductSales;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.polijic.proyectoejemplojpql.model.ProductCode;
import co.edu.polijic.proyectoejemplojpql.model.Manufacturer;
import co.edu.polijic.proyectoejemplojpql.model.Product;
import co.edu.polijic.proyectoejemplojpql.model.PurchaseOrder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author sala306
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) throws PreexistingEntityException, Exception {
        if (product.getPurchaseOrderList() == null) {
            product.setPurchaseOrderList(new ArrayList<PurchaseOrder>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductCode productCode = product.getProductCode();
            if (productCode != null) {
                productCode = em.getReference(productCode.getClass(), productCode.getProdCode());
                product.setProductCode(productCode);
            }
            Manufacturer manufacturerId = product.getManufacturerId();
            if (manufacturerId != null) {
                manufacturerId = em.getReference(manufacturerId.getClass(), manufacturerId.getManufacturerId());
                product.setManufacturerId(manufacturerId);
            }
            List<PurchaseOrder> attachedPurchaseOrderList = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderListPurchaseOrderToAttach : product.getPurchaseOrderList()) {
                purchaseOrderListPurchaseOrderToAttach = em.getReference(purchaseOrderListPurchaseOrderToAttach.getClass(), purchaseOrderListPurchaseOrderToAttach.getOrderNum());
                attachedPurchaseOrderList.add(purchaseOrderListPurchaseOrderToAttach);
            }
            product.setPurchaseOrderList(attachedPurchaseOrderList);
            em.persist(product);
            if (productCode != null) {
                productCode.getProductList().add(product);
                productCode = em.merge(productCode);
            }
            if (manufacturerId != null) {
                manufacturerId.getProductList().add(product);
                manufacturerId = em.merge(manufacturerId);
            }
            for (PurchaseOrder purchaseOrderListPurchaseOrder : product.getPurchaseOrderList()) {
                Product oldProductIdOfPurchaseOrderListPurchaseOrder = purchaseOrderListPurchaseOrder.getProductId();
                purchaseOrderListPurchaseOrder.setProductId(product);
                purchaseOrderListPurchaseOrder = em.merge(purchaseOrderListPurchaseOrder);
                if (oldProductIdOfPurchaseOrderListPurchaseOrder != null) {
                    oldProductIdOfPurchaseOrderListPurchaseOrder.getPurchaseOrderList().remove(purchaseOrderListPurchaseOrder);
                    oldProductIdOfPurchaseOrderListPurchaseOrder = em.merge(oldProductIdOfPurchaseOrderListPurchaseOrder);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProduct(product.getProductId()) != null) {
                throw new PreexistingEntityException("Product " + product + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getProductId());
            ProductCode productCodeOld = persistentProduct.getProductCode();
            ProductCode productCodeNew = product.getProductCode();
            Manufacturer manufacturerIdOld = persistentProduct.getManufacturerId();
            Manufacturer manufacturerIdNew = product.getManufacturerId();
            List<PurchaseOrder> purchaseOrderListOld = persistentProduct.getPurchaseOrderList();
            List<PurchaseOrder> purchaseOrderListNew = product.getPurchaseOrderList();
            List<String> illegalOrphanMessages = null;
            for (PurchaseOrder purchaseOrderListOldPurchaseOrder : purchaseOrderListOld) {
                if (!purchaseOrderListNew.contains(purchaseOrderListOldPurchaseOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PurchaseOrder " + purchaseOrderListOldPurchaseOrder + " since its productId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (productCodeNew != null) {
                productCodeNew = em.getReference(productCodeNew.getClass(), productCodeNew.getProdCode());
                product.setProductCode(productCodeNew);
            }
            if (manufacturerIdNew != null) {
                manufacturerIdNew = em.getReference(manufacturerIdNew.getClass(), manufacturerIdNew.getManufacturerId());
                product.setManufacturerId(manufacturerIdNew);
            }
            List<PurchaseOrder> attachedPurchaseOrderListNew = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderListNewPurchaseOrderToAttach : purchaseOrderListNew) {
                purchaseOrderListNewPurchaseOrderToAttach = em.getReference(purchaseOrderListNewPurchaseOrderToAttach.getClass(), purchaseOrderListNewPurchaseOrderToAttach.getOrderNum());
                attachedPurchaseOrderListNew.add(purchaseOrderListNewPurchaseOrderToAttach);
            }
            purchaseOrderListNew = attachedPurchaseOrderListNew;
            product.setPurchaseOrderList(purchaseOrderListNew);
            product = em.merge(product);
            if (productCodeOld != null && !productCodeOld.equals(productCodeNew)) {
                productCodeOld.getProductList().remove(product);
                productCodeOld = em.merge(productCodeOld);
            }
            if (productCodeNew != null && !productCodeNew.equals(productCodeOld)) {
                productCodeNew.getProductList().add(product);
                productCodeNew = em.merge(productCodeNew);
            }
            if (manufacturerIdOld != null && !manufacturerIdOld.equals(manufacturerIdNew)) {
                manufacturerIdOld.getProductList().remove(product);
                manufacturerIdOld = em.merge(manufacturerIdOld);
            }
            if (manufacturerIdNew != null && !manufacturerIdNew.equals(manufacturerIdOld)) {
                manufacturerIdNew.getProductList().add(product);
                manufacturerIdNew = em.merge(manufacturerIdNew);
            }
            for (PurchaseOrder purchaseOrderListNewPurchaseOrder : purchaseOrderListNew) {
                if (!purchaseOrderListOld.contains(purchaseOrderListNewPurchaseOrder)) {
                    Product oldProductIdOfPurchaseOrderListNewPurchaseOrder = purchaseOrderListNewPurchaseOrder.getProductId();
                    purchaseOrderListNewPurchaseOrder.setProductId(product);
                    purchaseOrderListNewPurchaseOrder = em.merge(purchaseOrderListNewPurchaseOrder);
                    if (oldProductIdOfPurchaseOrderListNewPurchaseOrder != null && !oldProductIdOfPurchaseOrderListNewPurchaseOrder.equals(product)) {
                        oldProductIdOfPurchaseOrderListNewPurchaseOrder.getPurchaseOrderList().remove(purchaseOrderListNewPurchaseOrder);
                        oldProductIdOfPurchaseOrderListNewPurchaseOrder = em.merge(oldProductIdOfPurchaseOrderListNewPurchaseOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getProductId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getProductId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PurchaseOrder> purchaseOrderListOrphanCheck = product.getPurchaseOrderList();
            for (PurchaseOrder purchaseOrderListOrphanCheckPurchaseOrder : purchaseOrderListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the PurchaseOrder " + purchaseOrderListOrphanCheckPurchaseOrder + " in its purchaseOrderList field has a non-nullable productId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ProductCode productCode = product.getProductCode();
            if (productCode != null) {
                productCode.getProductList().remove(product);
                productCode = em.merge(productCode);
            }
            Manufacturer manufacturerId = product.getManufacturerId();
            if (manufacturerId != null) {
                manufacturerId.getProductList().remove(product);
                manufacturerId = em.merge(manufacturerId);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Product> findProductByDescription(String description) {
        EntityManager em = getEntityManager();
        //TypedQuery<Product> productList = em.createNamedQuery("Product.findAll", Product.class);
        TypedQuery<Product> productList = em.createQuery("SELECT p FROM Product p WHERE LOWER(P.description) LIKE LOWER(:description)", Product.class);
        //si la consulta necesita parametro, sino, se obvia
        productList.setParameter("description", "%" + description + "%");
        return productList.getResultList();
    }
    
    //método en clase
    public Product findProductById(Integer id) {
        EntityManager em = getEntityManager();
        TypedQuery<Product> productList = em.createQuery("SELECT p FROM Product p WHERE p.productId=:id", Product.class);
        productList.setParameter("id", id);
        return productList.getSingleResult();
    }
    
    public List<ProductSales> getTopTenProducts(){
        EntityManager em = getEntityManager();
        TypedQuery<ProductSales> topTen = em.createQuery("SELECT NEW co.edu.polijic.proyectoejemplojpql.dto.ProductSales(p, SUM(orders.quantity)) FROM Product p JOIN p.purchaseOrderList orders GROUP BY p ORDER BY SUM(orders.quantity) DESC", ProductSales.class);
        topTen.setMaxResults(10);
        return topTen.getResultList();
    }
    
    public List<ProductSales> getBottomTenProducts(){
        EntityManager em = getEntityManager();
        TypedQuery<ProductSales> bottomTen = em.createQuery("SELECT NEW co.edu.polijic.proyectoejemplojpql.dto.ProductSales(p, SUM(orders.quantity)) FROM Product p LEFT JOIN p.purchaseOrderList orders GROUP BY p ORDER BY SUM(orders.quantity) ASC", ProductSales.class);
        bottomTen.setMaxResults(10);
        return bottomTen.getResultList();
    }
    
    public List<ProductSales> getTopIncomes(){
        EntityManager em = getEntityManager();
        TypedQuery<ProductSales> topIncomes = em.createQuery("SELECT NEW co.edu.polijic.proyectoejemplojpql.dto.ProductSales(p, SUM(orders.quantity)) FROM Product p JOIN p.purchaseOrderList orders GROUP BY p HAVING SUM(p.purchaseCost*orders.quantity) > 50000d ORDER BY SUM(orders.quantity)", ProductSales.class);
        return topIncomes.getResultList();
    }
    
    public List<Product> getProductsByManufacturerName(String manufacturerName) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> prod = cq.from(Product.class);
            cq.select(prod).where(cb.equal(prod.get("manufacturerId").get("name"), manufacturerName));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
