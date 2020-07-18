package controller;

import converter.devicebound.DeviceBoundModelToLoginResponseConverter;
import converter.user.UserLoginRequestToModelConverter;
import converter.user.UserRegisterRequestToModelConverter;
import model.DeviceBound;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restmodel.request.LoginRequest;
import restmodel.request.RegisterRequest;
import restmodel.response.LoginResponse;
import service.implementation.DeviceBoundServiceImpl;
import service.implementation.UserServiceImpl;

@RestController
@RequestMapping("/validation")
public class UserController {

    private final UserServiceImpl userService;
    private final DeviceBoundServiceImpl deviceBoundService;
    private final UserRegisterRequestToModelConverter userRegisterRequestToModelConverter = new UserRegisterRequestToModelConverter();
    private final UserLoginRequestToModelConverter userLoginRequestToModelConverter = new UserLoginRequestToModelConverter();
    private final DeviceBoundModelToLoginResponseConverter deviceBoundModelToLoginResponseConvert = new DeviceBoundModelToLoginResponseConverter();

    @Autowired
    public UserController(UserServiceImpl userService,
                          DeviceBoundServiceImpl deviceBoundService) {
        this.userService = userService;
        this.deviceBoundService = deviceBoundService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        HttpStatus status = userService.create(userRegisterRequestToModelConverter.convert(request)) ?
                HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> validateUser(@RequestBody LoginRequest request) {
        User user = userLoginRequestToModelConverter.convert(request);
        if (!userService.validate(user.getUsername(), user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Integer id = userService.findUserIdByUsername(request.getUsername());
        DeviceBound deviceBound = deviceBoundService.registerNewDevice(id);
        LoginResponse response = deviceBoundModelToLoginResponseConvert.convert(deviceBound);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
