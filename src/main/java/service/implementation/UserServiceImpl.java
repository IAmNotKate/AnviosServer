package service.implementation;

import converter.user.UserEntityToModelConverter;
import converter.user.UserModelToEntityConverter;
import database.entity.UserEntity;
import database.repository.UserRepository;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserModelToEntityConverter userModelToEntityConverter = new UserModelToEntityConverter();
    private final UserEntityToModelConverter userEntityToModelConverter = new UserEntityToModelConverter();

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(User user) {
        if (repository.findUserEntityByUsername(user.getUsername()) != null) {
            return false;
        }
        repository.save(userModelToEntityConverter.convert(user));
        return true;
    }

    @Override
    public boolean validate(String username, String password) {
        return repository.existsUserEntityByUsernameAndPassword(username, password);
    }

    @Override
    public Integer findUserIdByUsername(String username) {
        UserEntity entity = repository.findUserEntityByUsername(username);
        return entity != null ? entity.getId() : null;
    }

    @Override
    public User findUserById(Integer userId) {
        return userEntityToModelConverter.convert(repository.findUserEntityById(userId));
    }
}
