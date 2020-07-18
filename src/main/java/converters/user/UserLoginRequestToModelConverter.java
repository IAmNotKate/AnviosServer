package converters.user;

import models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import restmodels.requests.LoginRequest;


public class UserLoginRequestToModelConverter implements Converter<LoginRequest, User> {
    @Override
    @NotNull
    public User convert(@NotNull LoginRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }
}
