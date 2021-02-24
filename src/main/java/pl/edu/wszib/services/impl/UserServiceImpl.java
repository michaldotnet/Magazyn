package pl.edu.wszib.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.dao.UserDAO;
import pl.edu.wszib.model.User;
import pl.edu.wszib.model.UserRole;
import pl.edu.wszib.services.UserService;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserByLogin(String login){
        return userDAO.getUserByLogin(login);
    }


    @Override
    public List<User> findUsersByRole(UserRole userRole) {
        return userDAO.findUsersByRole(userRole);
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User updateUser(Long userId, User user)  {
        user.setId(userId);
        return userDAO.updateUser(user);
    }

    @Override
    public User addUser(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return userDAO.addUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
