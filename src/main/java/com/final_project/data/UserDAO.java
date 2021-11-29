package com.final_project.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.final_project.model.*;

public class UserDAO {
    public static void insert(User user) {
        EntityManager em = UserSQL.getSessionFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(user);
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

    public static void update(User user) {
        EntityManager em = UserSQL.getSessionFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(user);
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

    public static User selectUser(String email) {
        EntityManager em = UserSQL.getSessionFactory().createEntityManager();
        String qString = "SELECT u FROM User u " + "WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        try 
        {
            User user = q.getSingleResult();
            System.out.println("Select success!!");
            return user;
        } catch (NoResultException e) {
            System.out.println("SelectUser fail!!!");
            return null;
        } finally {
            em.close();
        }
    }

    public static User selectUser(int id) {
        EntityManager em = UserSQL.getSessionFactory().createEntityManager();
        String qString = "SELECT u FROM User u " + "WHERE u.id = :id";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("id", id);
        try {
            User user = q.getSingleResult();
            System.out.println("Select success!!");
            return user;
        } catch (NoResultException e) {
            System.out.println("SelectUser fail!!!");
            return null;
        } finally {
            em.close();
        }
    }

    public static List<User> selectAllUsers() {
        EntityManager em = UserSQL.getSessionFactory().createEntityManager();
        String qString = "FROM User";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        try {
            List<User> users = q.getResultList();
            System.out.println("Select success!!");
            return users;
        } catch (Exception e) {
            System.out.println("SelectUser fail!!!");
            return null;
        } finally {
            em.close();
        }
    }

    public static void delete(User user) {
        EntityManager em = UserSQL.getSessionFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(user));
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

    public static boolean emailExists(String email) {
        User u = selectUser(email);
        return u != null;
    }

    public static boolean passwordExists(String email, String password) {
        User u = selectUser(email);
        return u.getpassword().equals(password);
    }
}
