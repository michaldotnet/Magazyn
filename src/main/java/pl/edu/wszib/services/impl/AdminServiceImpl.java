package pl.edu.wszib.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.dao.impl.UserDAOImpl;
import pl.edu.wszib.model.User;
import pl.edu.wszib.model.UserRole;
import pl.edu.wszib.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private UserDAOImpl userDAO;

    @Autowired
    public AdminServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean verifyAccount(User user) {
        User userFromDB = userDAO.getUserByLogin(user.getLogin());
        return userFromDB.getRoles().contains(UserRole.ADMIN) &&
                userFromDB.getPassword().equals(DigestUtils.md5Hex(user.getPassword()));
    }
}
