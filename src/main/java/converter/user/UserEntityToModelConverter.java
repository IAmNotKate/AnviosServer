package converter.user;

import database.entity.UserEntity;
import model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class UserEntityToModelConverter implements Converter<UserEntity, User> {
    @Override
    @NotNull
    public User convert(@NotNull UserEntity userEntity) {
        User user = new User();
        user.setPassword(userEntity.getPassword());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setEmail(userEntity.getEmail());
        return user;
    }
}
