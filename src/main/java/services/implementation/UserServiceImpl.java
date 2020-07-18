package services.implementation;

import converters.user.UserEntityToModelConverter;
import converters.user.UserModelToEntityConverter;
import database.entities.UserEntity;
import database.repositories.UserRepository;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserModelToEntityConverter userModelToEntityConverter;
    private final UserEntityToModelConverter userEntityToModelConverter;

    @Autowired
    public UserServiceImpl(UserRepository repository,
                           UserModelToEntityConverter userModelToEntityConverter,
                           UserEntityToModelConverter userEntityToModelConverter) {
        this.repository = repository;
        this.userModelToEntityConverter = userModelToEntityConverter;
        this.userEntityToModelConverter = userEntityToModelConverter;
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
