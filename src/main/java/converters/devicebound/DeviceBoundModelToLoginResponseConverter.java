package converters.devicebound;

import models.DeviceBound;
import org.springframework.core.convert.converter.Converter;
import restmodels.responses.LoginResponse;

public class DeviceBoundModelToLoginResponseConverter implements Converter<DeviceBound, LoginResponse> {
    @Override
    public LoginResponse convert(DeviceBound deviceBound) {
        LoginResponse response = new LoginResponse();
        response.setDeviceToken(deviceBound.getDeviceToken());
        return response;
    }
}
