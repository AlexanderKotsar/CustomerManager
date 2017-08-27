package xyz.kots.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.kots.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        if (String.valueOf(user.getUsername().charAt(0)).equals("A"))
            user.setRole("ROLE_ADMIN");
        else
            user.setRole("ROLE_USER");
        entityManager.persist(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) entityManager.createQuery("FROM User").getResultList();
    }
}
