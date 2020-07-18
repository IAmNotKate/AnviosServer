package services.interfaces;

import models.DeviceBound;

public interface DeviceBoundService {
    DeviceBound registerNewDevice(int userId);

    DeviceBound validateToken(String token);
}
