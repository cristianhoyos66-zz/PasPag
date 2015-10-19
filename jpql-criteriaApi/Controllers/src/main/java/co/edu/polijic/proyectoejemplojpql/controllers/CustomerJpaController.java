/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.polijic.proyectoejemplojpql.controllers;

import co.edu.polijic.proyectoejemplojpql.controllers.exceptions.IllegalOrphanException;
import co.edu.polijic.proyectoejemplojpql.controllers.exceptions.NonexistentEntityException;
import co.edu.polijic.proyectoejemplojpql.controllers.exceptions.PreexistingEntityException;
import co.edu.polijic.proyectoejemplojpql.dto.CustomerDiscount;
import co.edu.polijic.proyectoejemplojpql.dto.CustomerNameDiscount;
import co.edu.polijic.proyectoejemplojpql.dto.CustomerTotalPurchase;
import co.edu.polijic.proyectoejemplojpql.model.Customer;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.polijic.proyectoejemplojpql.model.MicroMarket;
import co.edu.polijic.proyectoejemplojpql.model.DiscountCode;
import co.edu.polijic.proyectoejemplojpql.model.Product;
import co.edu.polijic.proyectoejemplojpql.model.PurchaseOrder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author sala306
 */
public class CustomerJpaController implements Serializable {

    public CustomerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) throws PreexistingEntityException, Exception {
        if (customer.getPurchaseOrderList() == null) {
            customer.setPurchaseOrderList(new ArrayList<PurchaseOrder>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MicroMarket zip = customer.getZip();
            if (zip != null) {
                zip = em.getReference(zip.getClass(), zip.getZipCode());
                customer.setZip(zip);
            }
            DiscountCode discountCode = customer.getDiscountCode();
            if (discountCode != null) {
                discountCode = em.getReference(discountCode.getClass(), discountCode.getDiscountCode());
                customer.setDiscountCode(discountCode);
            }
            List<PurchaseOrder> attachedPurchaseOrderList = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderListPurchaseOrderToAttach : customer.getPurchaseOrderList()) {
                purchaseOrderListPurchaseOrderToAttach = em.getReference(purchaseOrderListPurchaseOrderToAttach.getClass(), purchaseOrderListPurchaseOrderToAttach.getOrderNum());
                attachedPurchaseOrderList.add(purchaseOrderListPurchaseOrderToAttach);
            }
            customer.setPurchaseOrderList(attachedPurchaseOrderList);
            em.persist(customer);
            if (zip != null) {
                zip.getCustomerList().add(customer);
                zip = em.merge(zip);
            }
            if (discountCode != null) {
                discountCode.getCustomerList().add(customer);
                discountCode = em.merge(discountCode);
            }
            for (PurchaseOrder purchaseOrderListPurchaseOrder : customer.getPurchaseOrderList()) {
                Customer oldCustomerIdOfPurchaseOrderListPurchaseOrder = purchaseOrderListPurchaseOrder.getCustomerId();
                purchaseOrderListPurchaseOrder.setCustomerId(customer);
                purchaseOrderListPurchaseOrder = em.merge(purchaseOrderListPurchaseOrder);
                if (oldCustomerIdOfPurchaseOrderListPurchaseOrder != null) {
                    oldCustomerIdOfPurchaseOrderListPurchaseOrder.getPurchaseOrderList().remove(purchaseOrderListPurchaseOrder);
                    oldCustomerIdOfPurchaseOrderListPurchaseOrder = em.merge(oldCustomerIdOfPurchaseOrderListPurchaseOrder);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCustomer(customer.getCustomerId()) != null) {
                throw new PreexistingEntityException("Customer " + customer + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer persistentCustomer = em.find(Customer.class, customer.getCustomerId());
            MicroMarket zipOld = persistentCustomer.getZip();
            MicroMarket zipNew = customer.getZip();
            DiscountCode discountCodeOld = persistentCustomer.getDiscountCode();
            DiscountCode discountCodeNew = customer.getDiscountCode();
            List<PurchaseOrder> purchaseOrderListOld = persistentCustomer.getPurchaseOrderList();
            List<PurchaseOrder> purchaseOrderListNew = customer.getPurchaseOrderList();
            List<String> illegalOrphanMessages = null;
            for (PurchaseOrder purchaseOrderListOldPurchaseOrder : purchaseOrderListOld) {
                if (!purchaseOrderListNew.contains(purchaseOrderListOldPurchaseOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PurchaseOrder " + purchaseOrderListOldPurchaseOrder + " since its customerId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (zipNew != null) {
                zipNew = em.getReference(zipNew.getClass(), zipNew.getZipCode());
                customer.setZip(zipNew);
            }
            if (discountCodeNew != null) {
                discountCodeNew = em.getReference(discountCodeNew.getClass(), discountCodeNew.getDiscountCode());
                customer.setDiscountCode(discountCodeNew);
            }
            List<PurchaseOrder> attachedPurchaseOrderListNew = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderListNewPurchaseOrderToAttach : purchaseOrderListNew) {
                purchaseOrderListNewPurchaseOrderToAttach = em.getReference(purchaseOrderListNewPurchaseOrderToAttach.getClass(), purchaseOrderListNewPurchaseOrderToAttach.getOrderNum());
                attachedPurchaseOrderListNew.add(purchaseOrderListNewPurchaseOrderToAttach);
            }
            purchaseOrderListNew = attachedPurchaseOrderListNew;
            customer.setPurchaseOrderList(purchaseOrderListNew);
            customer = em.merge(customer);
            if (zipOld != null && !zipOld.equals(zipNew)) {
                zipOld.getCustomerList().remove(customer);
                zipOld = em.merge(zipOld);
            }
            if (zipNew != null && !zipNew.equals(zipOld)) {
                zipNew.getCustomerList().add(customer);
                zipNew = em.merge(zipNew);
            }
            if (discountCodeOld != null && !discountCodeOld.equals(discountCodeNew)) {
                discountCodeOld.getCustomerList().remove(customer);
                discountCodeOld = em.merge(discountCodeOld);
            }
            if (discountCodeNew != null && !discountCodeNew.equals(discountCodeOld)) {
                discountCodeNew.getCustomerList().add(customer);
                discountCodeNew = em.merge(discountCodeNew);
            }
            for (PurchaseOrder purchaseOrderListNewPurchaseOrder : purchaseOrderListNew) {
                if (!purchaseOrderListOld.contains(purchaseOrderListNewPurchaseOrder)) {
                    Customer oldCustomerIdOfPurchaseOrderListNewPurchaseOrder = purchaseOrderListNewPurchaseOrder.getCustomerId();
                    purchaseOrderListNewPurchaseOrder.setCustomerId(customer);
                    purchaseOrderListNewPurchaseOrder = em.merge(purchaseOrderListNewPurchaseOrder);
                    if (oldCustomerIdOfPurchaseOrderListNewPurchaseOrder != null && !oldCustomerIdOfPurchaseOrderListNewPurchaseOrder.equals(customer)) {
                        oldCustomerIdOfPurchaseOrderListNewPurchaseOrder.getPurchaseOrderList().remove(purchaseOrderListNewPurchaseOrder);
                        oldCustomerIdOfPurchaseOrderListNewPurchaseOrder = em.merge(oldCustomerIdOfPurchaseOrderListNewPurchaseOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customer.getCustomerId();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
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
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getCustomerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PurchaseOrder> purchaseOrderListOrphanCheck = customer.getPurchaseOrderList();
            for (PurchaseOrder purchaseOrderListOrphanCheckPurchaseOrder : purchaseOrderListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the PurchaseOrder " + purchaseOrderListOrphanCheckPurchaseOrder + " in its purchaseOrderList field has a non-nullable customerId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            MicroMarket zip = customer.getZip();
            if (zip != null) {
                zip.getCustomerList().remove(customer);
                zip = em.merge(zip);
            }
            DiscountCode discountCode = customer.getDiscountCode();
            if (discountCode != null) {
                discountCode.getCustomerList().remove(customer);
                discountCode = em.merge(discountCode);
            }
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Customer> getAllCustomers() throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery("select c from Customer c");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Customer> getFirstFiveCustomers() throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery("select c from Customer c join fetch c.purchaseOrderList");
            query.setMaxResults(5);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Object[]> getTopTen(){
        EntityManager em = getEntityManager();
        Query topTen = em.createQuery("SELECT c, SUM(po.quantity*pr.purchaseCost) FROM Customer c JOIN c.purchaseOrderList po JOIN po.productId pr GROUP BY c ORDER BY SUM(po.quantity*pr.purchaseCost) DESC");
        topTen.setMaxResults(10);
        return topTen.getResultList();
    }
    
    public List<Customer> getCustomersSpecialDicountCodes() throws Exception {
        List<String> specialDiscountCodes = new ArrayList<String>();
        specialDiscountCodes.add("L");
        specialDiscountCodes.add("H");
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery("select c from Customer c join fetch c.purchaseOrderList WHERE c.discountCode.discountCode IN :discountCodes");
            query.setParameter("discountCodes", specialDiscountCodes);
            query.setMaxResults(5);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Customer> getSpecialCustomerObject(){
        List<DiscountCode> specialDiscountCodes = new ArrayList<DiscountCode>();
        specialDiscountCodes.add(new DiscountCode("H"));
        specialDiscountCodes.add(new DiscountCode("M"));
        specialDiscountCodes.add(new DiscountCode("L"));
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.discountCode IN :dicountCodes", Customer.class);
        q.setParameter("dicountCodes", specialDiscountCodes);
        return q.getResultList();
    }
    
    public List<Object[]> getTotalPurchaseObjects(){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT c, SUM(po.quantity*pr.purchaseCost) FROM Customer c JOIN c.purchaseOrderList po JOIN po.productId pr GROUP BY c ORDER BY SUM(po.quantity*pr.purchaseCost) DESC");
        return q.getResultList();
    }
    
    public List<Object[]> getTotalPurchaseObjectsAll(){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT c, (CASE WHEN SUM(po.quantity*pr.purchaseCost) IS NULL THEN 0 ELSE SUM(po.quantity*pr.purchaseCost) END) FROM Customer c LEFT JOIN c.purchaseOrderList po LEFT JOIN po.productId pr GROUP BY c ORDER BY SUM(po.quantity*pr.purchaseCost) DESC");
        return q.getResultList();
    }
    
    public List<CustomerTotalPurchase> getTotalPurchase(){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT NEW testjpa.dto.CustomerTotalPurchase(c, SUM(po.quantity*pr.purchaseCost)) FROM Customer c JOIN c.purchaseOrderList po JOIN po.productId pr GROUP BY c ORDER BY SUM(po.quantity*pr.purchaseCost) DESC", CustomerTotalPurchase.class);
        return q.getResultList();
    }
    
    public List<Customer> getCustomersByName(String name) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.select(emp).where(cb.equal(emp.get("name"), name));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> getCustomerNames() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<String> cq = cb.createQuery(String.class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.select(emp.<String>get("name"));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Tuple> getCustomerDiscountRateT() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.select(cb.tuple(emp.get("name"), emp.get("discountCode").get("rate")));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getCustomerDiscountRateO() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.multiselect(emp.get("name"), emp.get("discountCode").get("rate"));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Tuple> getCustomerDiscountRateT2() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.multiselect(emp.get("name"), emp.get("discountCode").get("rate"));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Tuple> getCustomerDiscountRateTCN() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.multiselect(emp.get("name").alias("nombre"), emp.get("discountCode").get("rate").alias("porcentaje"));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<CustomerNameDiscount> getCustomerDiscountRateCES() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CustomerNameDiscount> cq = cb.createQuery(CustomerNameDiscount.class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.multiselect(emp.get("name"), emp.get("discountCode").<BigDecimal>get("rate"));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<CustomerDiscount> getCustomerDiscountRateCE() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CustomerDiscount> cq = cb.createQuery(CustomerDiscount.class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.multiselect(emp, emp.get("discountCode").<BigDecimal>get("rate"));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<CustomerDiscount> getCustomerDiscountRateCECB() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CustomerDiscount> cq = cb.createQuery(CustomerDiscount.class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.select(cb.construct(CustomerDiscount.class, emp, emp.get("discountCode").<BigDecimal>get("rate")));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getCustomerDiscountRatePCE() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Customer> emp = cq.from(Customer.class);
            cq.multiselect(emp.get("customerId"), cb.construct(CustomerDiscount.class, emp, emp.get("discountCode").<BigDecimal>get("rate")));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getCustomerPurchase() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Customer> emp = cq.from(Customer.class);
            Join<Customer, PurchaseOrder> orders = emp.join("purchaseOrderList");
            Join<PurchaseOrder, Product> products = orders.join("productId");
            cq.multiselect(emp, orders, products);
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getCustomerPurchaseByNameAndOrProduct(String name, Integer productId) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Customer> emp = cq.from(Customer.class);
            Join<Customer, PurchaseOrder> orders = emp.join("purchaseOrderList");
            Join<PurchaseOrder, Product> products = orders.join("productId");
            cq.multiselect(emp, orders, products);
            Predicate criteria = cb.conjunction();
            if (name != null) {
                ParameterExpression<String> nameParameter = cb.parameter(String.class, "name");
                criteria = cb.and(criteria, cb.equal(emp.get("name"), nameParameter));
            }
            if (productId != null) {
                ParameterExpression<Integer> productIdParameter = cb.parameter(Integer.class, "productId");
                criteria = cb.and(criteria, cb.equal(products.get("productId"), productIdParameter));
            }
            if (!criteria.getExpressions().isEmpty()) {
                cq.where(cb.and(criteria));
            }
            Query q = em.createQuery(cq);
            if (name != null) {
                q.setParameter("name", name);
            }
            if (productId != null) {
                q.setParameter("productId", productId);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Object[]> getCustomerPurchaseValueByNameAndOrProduct(String name) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Customer> emp = cq.from(Customer.class);
            Join<Customer, PurchaseOrder> orders = emp.join("purchaseOrderList");
            Join<PurchaseOrder, Product> products = orders.join("productId");
            Expression<Short> quantity = orders.get("quantity");
            Expression<BigDecimal> productValue = products.get("purchaseCost");
            Expression sum = cb.sum(cb.prod(quantity, productValue));
            cq.multiselect(emp, sum).groupBy(emp).orderBy(cb.desc(sum));
            if (name != null) {
                cq.where(cb.and(cb.equal(emp.get("name"), name)));
            }
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
