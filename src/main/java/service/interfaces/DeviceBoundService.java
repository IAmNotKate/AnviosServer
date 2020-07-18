package service.interfaces;

import model.DeviceBound;

public interface DeviceBoundService {
    DeviceBound registerNewDevice(int userId);

    DeviceBound validateToken(String token);
}
