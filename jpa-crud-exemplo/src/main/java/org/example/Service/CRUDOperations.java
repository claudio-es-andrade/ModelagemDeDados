package org.example.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import org.example.Entity.Student;
import org.example.Util.JPAUtil;

/**
 *  JPA CRUD Operations
 * @author Claudio ES Andrade
 *
 */
public class CRUDOperations {
    public void insertEntity() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student = new Student("Ana", "Silva", "anaSilva@claudioandrade.com.br");
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(" Student information CREATED with success");
    }

    public void findEntity() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, 1);
        System.out.println("student id :: " + student.getId());
        System.out.println("student firstname :: " + student.getFirstName());
        System.out.println("student lastname :: " + student.getLastName());
        System.out.println("student email :: " + student.getEmail());
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(" Student information LISTED with success");
    }

    public void updateEntity() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, 1);
        System.out.println("student id :: " + student.getId());
        System.out.println("student firstname :: " + student.getFirstName());
        System.out.println("student lastname :: " + student.getLastName());
        System.out.println("student email :: " + student.getEmail());

        // The entity object is physically updated in the database when the transaction
        // is committed
        student.setFirstName("Fernanda");
        student.setLastName("Souto");
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(" Student information UPDATED with success");
    }

    public void removeEntity() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, 1);
        System.out.println("student id :: " + student.getId());
        System.out.println("student firstname :: " + student.getFirstName());
        System.out.println("student lastname :: " + student.getLastName());
        System.out.println("student email :: " + student.getEmail());
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(" Student information REMOVED with success");
    }
}