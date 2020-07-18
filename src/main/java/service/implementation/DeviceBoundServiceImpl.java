package service.implementation;

import converter.devicebound.DeviceBoundEntityToModelConverter;
import converter.devicebound.DeviceBoundModelToEntityConverter;
import database.entity.DeviceBoundEntity;
import database.repository.DeviceBoundRepository;
import model.DeviceBound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.DeviceBoundService;


@Service
public class DeviceBoundServiceImpl implements DeviceBoundService {
    private final DeviceBoundRepository deviceBoundRepository;
    private final DeviceBoundModelToEntityConverter modelToEntityConverter = new DeviceBoundModelToEntityConverter();
    private final DeviceBoundEntityToModelConverter entityToModelConverter = new DeviceBoundEntityToModelConverter();

    @Autowired
    public DeviceBoundServiceImpl(DeviceBoundRepository deviceBoundRepository) {
        this.deviceBoundRepository = deviceBoundRepository;
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
