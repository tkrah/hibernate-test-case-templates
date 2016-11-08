package org.hibernate.bugs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {
  
  private EntityManagerFactory entityManagerFactory;
  
  @Before
  public void init() {
    entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
  }
  
  @After
  public void destroy() {
    entityManagerFactory.close();
  }
  
  // Entities are auto-discovered, so just add them anywhere on class-path
  // Add your tests, using standard JUnit.
  @Test
  public void hhh6709Test() throws Exception {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    Parent p = new Parent();
    Child c = new Child(p);
    
    entityManager.persist(p);
    entityManager.persist(c);
    entityManager.getTransaction().commit();
    entityManager.close();
    
    
    entityManager = entityManagerFactory.createEntityManager();
    
    entityManager.getTransaction().begin();
    List<Parent> resultList = entityManager.createQuery("from Parent", Parent.class).getResultList();
    Assert.assertEquals(1, resultList.size());
    Assert.assertEquals(1, resultList.get(0).getChilds().size());
    
    resultList.get(0).getChilds().clear();
    entityManager.getTransaction().commit();
    entityManager.close();
    
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    resultList = entityManager.createQuery("from Parent", Parent.class).getResultList();
    Assert.assertEquals(1, resultList.size());
    
    Assert.assertTrue("child should be gone", resultList.get(0).getChilds().isEmpty());
    entityManager.close();
  }
}
