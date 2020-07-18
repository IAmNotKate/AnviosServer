package model;

import java.util.UUID;

public class DeviceBound {
    private String deviceToken;
    private int userId;

    public static DeviceBound generateById(int userId) {
        DeviceBound deviceBound = new DeviceBound();
        deviceBound.setUserId(userId);
        deviceBound.setDeviceToken(UUID.randomUUID().toString());
        return deviceBound;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
