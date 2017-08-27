package xyz.kots.service;

import xyz.kots.entity.User;
import java.util.List;


public interface IUserService {
    void addUser(User user);
    List<User> getAllUsers();
}
