package pl.edu.wszib.services;

import pl.edu.wszib.model.User;
import pl.edu.wszib.model.UserRole;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User updateUser(Long userId, User user);

    User addUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    List<User> findUsersByRole(UserRole userRole);

    User getUserByLogin(String login);

}
