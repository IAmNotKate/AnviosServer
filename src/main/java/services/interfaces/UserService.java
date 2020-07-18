package services.interfaces;

import models.User;

public interface UserService {

    boolean create(User user);

    boolean validate(String username, String password);

    Integer findUserIdByUsername(String username);

    User findUserById(Integer userId);
}
