package xyz.kots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kots.dao.IUserDAO;
import xyz.kots.entity.User;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserDAO userDAO;

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
}
