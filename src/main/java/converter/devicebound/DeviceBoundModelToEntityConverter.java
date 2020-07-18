package converter.devicebound;

import database.entity.DeviceBoundEntity;
import model.DeviceBound;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class DeviceBoundModelToEntityConverter implements Converter<DeviceBound, DeviceBoundEntity> {
    @Override
    @NotNull
    public DeviceBoundEntity convert(@NotNull DeviceBound deviceBound) {
        DeviceBoundEntity entity = new DeviceBoundEntity();
        entity.setDeviceToken(deviceBound.getDeviceToken());
        entity.setUserId(deviceBound.getUserId());
        return entity;
    }
}
