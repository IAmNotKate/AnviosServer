package converter.user;

import database.entity.UserEntity;
import model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class UserModelToEntityConverter implements Converter<User, UserEntity> {
    @Override
    @NotNull
    public UserEntity convert(@NotNull User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setSurname(entity.getSurname());
        entity.setEmail(user.getEmail());
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
        return entity;
    }
}
