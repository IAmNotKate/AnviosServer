package restmodels.responses;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LoginResponse {
    private String deviceToken;

    @JsonGetter("device_token")
    public String getDeviceToken() {
        return deviceToken;
    }

    @JsonSetter("device_token")
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
