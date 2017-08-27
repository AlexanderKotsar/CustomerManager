package xyz.kots.dao;

import xyz.kots.entity.User;
import java.util.List;


public interface IUserDAO {
    void addUser(User user);
    List<User> getAllUsers();

}
