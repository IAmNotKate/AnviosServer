package controllers;

import converters.devicebound.DeviceBoundModelToLoginResponseConverter;
import converters.user.UserLoginRequestToModelConverter;
import converters.user.UserRegisterRequestToModelConverter;
import models.DeviceBound;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restmodels.requests.LoginRequest;
import restmodels.requests.RegisterRequest;
import restmodels.responses.LoginResponse;
import services.implementation.DeviceBoundServiceImpl;
import services.implementation.UserServiceImpl;

@RestController
@RequestMapping("/validation")
public class UserController {

    private final UserServiceImpl userService;
    private final DeviceBoundServiceImpl deviceBoundService;
    private final UserRegisterRequestToModelConverter userRegisterRequestToModelConverter;
    private final UserLoginRequestToModelConverter userLoginRequestToModelConverter;
    private final DeviceBoundModelToLoginResponseConverter deviceBoundModelToLoginResponseConverter;

    @Autowired
    public UserController(UserServiceImpl userService,
                          DeviceBoundServiceImpl deviceBoundService,
                          UserRegisterRequestToModelConverter userRegisterRequestToModelConverter,
                          UserLoginRequestToModelConverter userLoginRequestToModelConverter,
                          DeviceBoundModelToLoginResponseConverter deviceBoundModelToLoginResponseConverter) {
        this.userService = userService;
        this.deviceBoundService = deviceBoundService;
        this.userRegisterRequestToModelConverter = userRegisterRequestToModelConverter;
        this.userLoginRequestToModelConverter = userLoginRequestToModelConverter;
        this.deviceBoundModelToLoginResponseConverter = deviceBoundModelToLoginResponseConverter;
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
        LoginResponse response = deviceBoundModelToLoginResponseConverter.convert(deviceBound);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
