package converter.user;

import model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import restmodel.request.RegisterRequest;

public class UserRegisterRequestToModelConverter implements Converter<RegisterRequest, User> {
    @Override
    @NotNull
    public User convert(@NotNull RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(user.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}
