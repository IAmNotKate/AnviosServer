package services.implementation;

import converters.devicebound.DeviceBoundEntityToModelConverter;
import converters.devicebound.DeviceBoundModelToEntityConverter;
import database.entities.DeviceBoundEntity;
import database.repositories.DeviceBoundRepository;
import models.DeviceBound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.interfaces.DeviceBoundService;


@Service
public class DeviceBoundServiceImpl implements DeviceBoundService {
    private final DeviceBoundRepository deviceBoundRepository;
    private final DeviceBoundModelToEntityConverter modelToEntityConverter;
    private final DeviceBoundEntityToModelConverter entityToModelConverter;

    @Autowired
    public DeviceBoundServiceImpl(DeviceBoundRepository deviceBoundRepository,
                                  DeviceBoundModelToEntityConverter modelToEntityConverter,
                                  DeviceBoundEntityToModelConverter entityToModelConverter) {
        this.deviceBoundRepository = deviceBoundRepository;
        this.modelToEntityConverter = modelToEntityConverter;
        this.entityToModelConverter = entityToModelConverter;
    }

    @Override
    public DeviceBound registerNewDevice(int userId) {
        DeviceBound model = DeviceBound.generateById(userId);
        DeviceBoundEntity entity = modelToEntityConverter.convert(DeviceBound.generateById(userId));
        deviceBoundRepository.save(entity);
        return model;
    }

    @Override
    public DeviceBound validateToken(String token) {
        DeviceBoundEntity entity = deviceBoundRepository.findDeviceBoundEntityByDeviceToken(token);
        return entityToModelConverter.convert(entity);
    }
}
