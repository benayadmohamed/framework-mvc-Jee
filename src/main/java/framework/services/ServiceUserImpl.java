package framework.services;

import framework.configs.hibernet.JPAUtil;
import framework.configurationFramwork.annotations.Service;
import framework.models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceUserImpl implements ServiceUser {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    private String motCle = "";

    @Override
    public User save(User user) {
        entityManager.getTransaction().begin();
        if (user.getId() > 0)
            entityManager.merge(user);
        else
            entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User findById(int id) {
        Query query = entityManager.createQuery("SELECT e FROM User e where e.id=" + id);
        User firstResult = (User) query.getSingleResult();
        return firstResult;
    }


    @Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM User e where e.nom like '%" + motCle + "%'");
        return (List<User>) query.getResultList();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("delete from User e where e.id=" + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }
}
