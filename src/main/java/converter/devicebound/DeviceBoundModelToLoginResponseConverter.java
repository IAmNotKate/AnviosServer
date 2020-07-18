package converter.devicebound;

import model.DeviceBound;
import org.springframework.core.convert.converter.Converter;
import restmodel.response.LoginResponse;

public class DeviceBoundModelToLoginResponseConverter implements Converter<DeviceBound, LoginResponse> {
    @Override
    public LoginResponse convert(DeviceBound deviceBound) {
        LoginResponse response = new LoginResponse();
        response.setDeviceToken(deviceBound.getDeviceToken());
        return response;
    }
}
