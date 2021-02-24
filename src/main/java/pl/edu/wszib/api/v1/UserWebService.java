package pl.edu.wszib.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.model.User;
import pl.edu.wszib.model.UserRole;
import pl.edu.wszib.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserWebService {

    private final UserService userService;

    @Autowired
    public UserWebService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @GetMapping("/getUsers/{userRole}")
    public ResponseEntity<?> getUsersByRole(@PathVariable UserRole userRole) {
        return new ResponseEntity<List<User>>(userService.findUsersByRole(userRole),HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNewUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long userId) {
        return new ResponseEntity<User>(userService.updateUser(userId, user),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User with id: " + userId + " was deleted successfully",
                HttpStatus.OK);
    }
}
