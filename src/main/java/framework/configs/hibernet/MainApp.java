package framework.configs.hibernet;

import framework.models.Ana;

import javax.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(new Ana("sallam"));
        // Check database version
        String sql = "select version()";

        String result = (String) entityManager.createNativeQuery(sql).getSingleResult();
        System.out.println(result);

        entityManager.getTransaction().commit();
        entityManager.close();

        JPAUtil.shutdown();
    }
}
