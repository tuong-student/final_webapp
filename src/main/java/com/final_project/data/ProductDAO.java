package com.final_project.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.final_project.model.Product;

public class ProductDAO {

    public static void insert(Product product) {
        EntityManager em = ProductSQL.getSessionFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(product);
            trans.commit();
            System.out.println("Add success!!");
        } catch (Exception e) {
            System.out.println("Add fail!!");
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static Product selectProduct(int id) {
        EntityManager em = ProductSQL.getSessionFactory().createEntityManager();
        String qString = "SELECT u FROM Product u " + "WHERE u.id = :id";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("id", id);
        try {
            Product product = q.getSingleResult();
            System.out.println("Select success!!");
            return product;
        } catch (NoResultException e) {
            System.out.println("SelectUser fail!!!");
            return null;
        } finally {
            em.close();
        }
    }

    public static List<Product> selectAllProduct() {
        EntityManager em = ProductSQL.getSessionFactory().createEntityManager();
        String qString = "FROM Product";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        try {
            List<Product> products = q.getResultList();
            System.out.println("Select success!!");
            return products;
        } catch (NoResultException e) {
            System.out.println("SelectAll fail!!!");
            return null;
        } finally {
            em.close();
        }
    }

    public static void update(Product product) {
        EntityManager em = ProductSQL.getSessionFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(product);
            trans.commit();
            System.out.println("Update success!!");
        } catch (Exception e) {
            System.out.println("Update fail!!");
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Product product) {
        EntityManager em = ProductSQL.getSessionFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(product));
            trans.commit();
            System.out.println("Delete success!!");
        } catch (Exception e) {
            System.out.println("Delete fail!!");
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

}