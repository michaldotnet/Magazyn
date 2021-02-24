package pl.edu.wszib.dao;

import pl.edu.wszib.model.User;
import pl.edu.wszib.model.UserRole;

import java.util.List;

public interface UserDAO {

    User getUserById(Long id);

    User updateUser(User user);

    User addUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    List<User> findUsersByRole(UserRole userRole);

    User getUserByLogin(String login);

}
