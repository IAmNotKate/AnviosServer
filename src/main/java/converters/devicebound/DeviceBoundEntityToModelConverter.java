package converters.devicebound;

import database.entities.DeviceBoundEntity;
import models.DeviceBound;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class DeviceBoundEntityToModelConverter implements Converter<DeviceBoundEntity, DeviceBound> {
    @Override
    @NotNull
    public DeviceBound convert(@NotNull DeviceBoundEntity deviceBoundEntity) {
        DeviceBound model = new DeviceBound();
        model.setDeviceToken(deviceBoundEntity.getDeviceToken());
        model.setUserId(deviceBoundEntity.getUserId());
        return model;
    }
}
