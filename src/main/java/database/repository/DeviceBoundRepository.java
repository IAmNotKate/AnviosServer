package database.repository;

import database.entity.DeviceBoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceBoundRepository extends JpaRepository<DeviceBoundEntity, String> {
    DeviceBoundEntity findDeviceBoundEntityByDeviceToken(String token);
}
