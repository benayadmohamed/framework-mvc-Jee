package framework.services;

import framework.models.User;

import java.util.List;

public interface ServiceUser {
    User save(User user);

    User findById(int id);


    List<User> findAll();

    void delete(int id);

    public String getMotCle();

    public void setMotCle(String motCle);
}
